package group.uchain.oilsupplychain.service.impl;

import com.alibaba.fastjson.JSONObject;
import group.uchain.oilsupplychain.dto.*;
import group.uchain.oilsupplychain.dto2.*;
import group.uchain.oilsupplychain.result.Result;
import group.uchain.oilsupplychain.service.UserService;
import group.uchain.oilsupplychain.utils.FabricMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author panghu
 * @title: ActionService
 * @projectName demo
 * @date 19-5-4 下午9:18
 */
@Slf4j
@Service
public class ActionService {

    private final static String YES = "YES";

    private UserService userService;

    private InfoService infoService;

    private TypeChangeService typeChangeService;

    private BatchNumberService batchNumberService;



    @Autowired
    public ActionService(InfoService infoService,TypeChangeService typeChangeService,
                         BatchNumberService batchNumberService,UserService userService) {
        this.infoService = infoService;
        this.typeChangeService = typeChangeService;
        this.batchNumberService = batchNumberService;
        this.userService = userService;
    }

    /**
     *   获取区块链审核订单的情况
     */

    public Object getApplyDetail(String batchNumber){
        JSONObject jsonObject = FabricMethod.checkRequestOrder(batchNumber);
        return getObject(jsonObject);

    }

    public Object getTransApplyDetail(String batchNumber){
        JSONObject jsonObject = FabricMethod.checkOilTransRequestOrder(batchNumber);
        return getObject(jsonObject);
    }

    private Object getObject(JSONObject jsonObject) {
        if (!getStatus(jsonObject)){
            JSONObject resultJsonObject = jsonObject.getJSONObject("data");
            Long acceptID = resultJsonObject.getLongValue("acceptid");
            Long sendID = resultJsonObject.getLongValue("sentid");
            String type = resultJsonObject.getString("typenumber");
            String typeName = typeChangeService.getNormalName(type);
            String acceptName = typeChangeService.getNameById(acceptID);
            String sendName = typeChangeService .getNameById(sendID);
            resultJsonObject.put("typeName",typeName);
            resultJsonObject.put("acceptName",acceptName);
            resultJsonObject.put("sendName",sendName);
            resultJsonObject.remove("typenumber");
            resultJsonObject.remove("acceptid");
            resultJsonObject.remove("sentid");
            return Result.successData(resultJsonObject);
        }else{
            return jsonObject;
        }
    }

    /*
    上传订单方法  上传到区块链  并且同时保存历史订单到数据库
    ------------------------------------------------------------------------------------------------------------------
     */

    public Object uploadApplyOrder(ApplyDTO applyDTO){
        ChainApplyDTO chainApplyDTO = typeChangeService.getChainApplyDTO(applyDTO);
        JSONObject jsonObject = FabricMethod.createRequestOilOrder(chainApplyDTO);
        //没有获取到状态说明成功
        if (!getStatus(jsonObject)){
            String id = jsonObject.getJSONObject("data").getString("oilrequestorderid");
            infoService.saveApplyForm(chainApplyDTO,id);
            return Result.successData(chainApplyDTO.getBatchNumber());
        }else{
            return jsonObject;
        }

    }

    public Object uploadSendOrder(SendDTO sendDTO, String id){
        ChainSendDTO chainSendDTO = typeChangeService.getChainSendDTO(sendDTO,id);
        JSONObject jsonObject = FabricMethod.createSendForm(chainSendDTO);
        if (!getStatus(jsonObject)){
            String formId = (String) jsonObject.getJSONObject("data").get("oilhairorderid");
            infoService.saveSendForm(chainSendDTO,formId);
            try {
                FabricMethod.checkOilRequestOrderAndOilHairOrder(batchNumberService.getBatchNumberById(id));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            return Result.successData(chainSendDTO.getBatchNumber());
        }else {
            return jsonObject;
        }
    }



    public Object uploadTransApplyOrder(TransportationApplyDTO transportationApplyDTO, String id){
        ChainTransportationApplyDTO applyDTO = typeChangeService.getChainTransportationApplyDTO(transportationApplyDTO,id);
        JSONObject jsonObject = FabricMethod.createOilTransRequestOrder(applyDTO);
        if (!getStatus(jsonObject)){
            String formId = (String) jsonObject.getJSONObject("data").get("oiltransrequestorderid");
            infoService.saveTransApplyForm(applyDTO,formId);
            return Result.successData(applyDTO.getBatchNumber());
        }else  {
            return jsonObject;
        }

    }

    public Object uploadTransOrder(TransportationDTO transportationDTO, String id){
        ChainTransportationDTO chainTransportationDTO = typeChangeService.getChainTransportationDTO(transportationDTO,id);
        JSONObject jsonObject = FabricMethod.creatTransportOrder(chainTransportationDTO);
        if (!getStatus(jsonObject)){
            String formId = String.valueOf(jsonObject.getJSONObject("data").get("transportorderid"));
            infoService.saveTransForm(chainTransportationDTO,formId);
            try {
                FabricMethod.checkOilHairOrderAndTransportOrder(batchNumberService.getBatchNumberById(formId));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            return Result.successData(chainTransportationDTO.getBatchNumber());
        }else {
            return jsonObject;
        }
    }

    public Object uploadReceiveOrder(ReceiveDTO receiveDTO, String id){
        ChainReceiveDTO chainReceiveDTO = typeChangeService.getChainReceiveDTO(receiveDTO,id);
        String userID = userService.getCurrentUser().getId();
        JSONObject jsonObject = FabricMethod.createOilAcceptOrder(chainReceiveDTO,userID);
        if (!getStatus(jsonObject)){
            JSONObject checkJson = null;
            String role = userService.getCurrentUser().getRole();
            if (role.equals("2")||role.equals("4")){
                try {
                    checkJson = FabricMethod.checkAcceptOrderAndOilHairOrder(batchNumberService.getBatchNumberById(id));
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
            else if (role.equals("5")){
                try {
                    checkJson = FabricMethod.checkAcceptOrderAndTransportOrder(batchNumberService.getBatchNumberById(id));
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
            String status = String.valueOf(Objects.requireNonNull(checkJson).getJSONObject("status"));
            if (!status.equals(YES)){
                return Result.error("信息不一致");
            }
            String formId = String.valueOf(jsonObject.getJSONObject("data").get("oilacceptorderid"));
            infoService.saveReceiveForm(chainReceiveDTO,formId,userID);
            return Result.success(chainReceiveDTO.getBatchNumber());
        }else{
            return jsonObject;
        }
    }

    public Object uploadSellOrder(SellDTO sellDTO){
        String sellerID = userService.getCurrentUser().getId();
        ChainSellDTO chainSellDTO = typeChangeService.getChainSellDTO(sellDTO,sellerID);
        JSONObject jsonObject = FabricMethod.createSellOrder(chainSellDTO);
        if (!getStatus(jsonObject)){
            String formId = String.valueOf(jsonObject.getJSONObject("data").get("sellorderid"));
            log.info("返回的订单ID为"+formId);
            infoService.saveSellForm(chainSellDTO,formId);
            return Result.success("售油订单上传成功");
        }else {
            return jsonObject;
        }
    }

    /**
     * 是否拥有状态码，有状态码则返回的是错误信息
     * 这里获得的json对象应该是code而不是status,因为在FabricMethod里面getJsonObject进行了json修改
     * @param jsonObject
     * @return
     */
    private static Boolean getStatus(JSONObject jsonObject){
        return jsonObject.containsKey("code");
    }
}
