package group.uchain.oilsupplychain.service.impl;

import com.alibaba.fastjson.JSONObject;
import group.uchain.oilsupplychain.dto.*;
import group.uchain.oilsupplychain.dto2.*;
import group.uchain.oilsupplychain.enums.CodeMsg;
import group.uchain.oilsupplychain.mapper.OrderFormMapper;
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


    private UserService userService;

    private InfoService infoService;

    private TypeChangeService typeChangeService;

    private BatchNumberService batchNumberService;

    private OrderFormMapper orderFormMapper;



    @Autowired
    public ActionService(InfoService infoService,TypeChangeService typeChangeService,
                         BatchNumberService batchNumberService,UserService userService,
                         OrderFormMapper orderFormMapper) {
        this.infoService = infoService;
        this.typeChangeService = typeChangeService;
        this.batchNumberService = batchNumberService;
        this.userService = userService;
        this.orderFormMapper = orderFormMapper;
    }

    /**
     *   获取区块链审核订单的情况
     */

    public Object getApplyDetail(String batchNumber){
        JSONObject jsonObject = FabricMethod.checkRequestOrder(batchNumber);
        if (getStatus(jsonObject)){
            JSONObject resultJsonObject = jsonObject.getJSONObject("data");
            String message = jsonObject.getString("message");
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
            return Result.dataAndMessage(resultJsonObject,message);
        }else{
            return jsonObject;
        }

    }

    public Object getTransApplyDetail(String batchNumber){
        JSONObject jsonObject = FabricMethod.checkOilTransRequestOrder(batchNumber);
        if (getStatus(jsonObject)){
            JSONObject resultJsonObject = jsonObject.getJSONObject("data");
            String message = jsonObject.getString("message");
            Long acceptID = resultJsonObject.getLongValue("acceptid");
            Long sendID = resultJsonObject.getLongValue("sentid");
            String type = resultJsonObject.getString("typenumber");
            Long company = resultJsonObject.getLongValue("transportationfirmid");
            String companyName = typeChangeService.getNameById(company);
            String typeName = typeChangeService.getNormalName(type);
            String acceptName = typeChangeService.getNameById(acceptID);
            String sendName = typeChangeService .getNameById(sendID);
            resultJsonObject.put("typeName",typeName);
            resultJsonObject.put("companyName",companyName);
            resultJsonObject.put("acceptName",acceptName);
            resultJsonObject.put("sendName",sendName);
            resultJsonObject.remove("transportationfirmid");
            resultJsonObject.remove("typenumber");
            resultJsonObject.remove("acceptid");
            resultJsonObject.remove("sentid");
            return Result.dataAndMessage(resultJsonObject,message);
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
        //如果获取到data则是成功
        if (getStatus(jsonObject)){
            String id = jsonObject.getJSONObject("data").getString("oilrequestorderid");
            infoService.saveApplyForm(chainApplyDTO,id);
            return Result.successData(id);
        }else{
            return jsonObject;
        }

    }

    public Object uploadSendOrder(SendDTO sendDTO, String id){
        if (orderFormMapper.checkIdIsExist(id)==0){
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }else {
            ChainSendDTO chainSendDTO = typeChangeService.getChainSendDTO(sendDTO,id);

            JSONObject jsonObject = FabricMethod.createSendForm(chainSendDTO);
            //是否上传失败
            if (getStatus(jsonObject)){
                String formId = (String) jsonObject.getJSONObject("data").get("oilhairorderid");
                //核验是否信息一致
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                JSONObject checkJsonObject =  FabricMethod.checkOilRequestOrderAndOilHairOrder(batchNumberService.getBatchNumberById(id));
                //审核成功  返回结果为真
                if (getStatus(checkJsonObject)){
                    infoService.saveSendForm(chainSendDTO,formId);
                    return Result.successData(formId);
                }
                else {
                    return Result.error(checkJsonObject.getString("message"));
                }
            }else {
                //上传失败所返回的相关信息
                return jsonObject;
            }
        }
    }



    public Object uploadTransApplyOrder(TransportationApplyDTO transportationApplyDTO, String id){
        if (orderFormMapper.checkIdIsExist(id)==0){
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }else {
            ChainTransportationApplyDTO applyDTO = typeChangeService.getChainTransportationApplyDTO(transportationApplyDTO,id);
            JSONObject jsonObject = FabricMethod.createOilTransRequestOrder(applyDTO);
            if (getStatus(jsonObject)){
                String formId = (String) jsonObject.getJSONObject("data").get("oiltransrequestorderid");
                infoService.saveTransApplyForm(applyDTO,formId);
                return Result.successData(formId);
            }else  {
                return jsonObject;
            }
        }

    }

    public Object uploadTransOrder(TransportationDTO transportationDTO, String id){
        if (orderFormMapper.checkIdIsExist(id)==0){
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }else {
            ChainTransportationDTO chainTransportationDTO = typeChangeService.getChainTransportationDTO(transportationDTO,id);
            JSONObject jsonObject = FabricMethod.creatTransportOrder(chainTransportationDTO);
            if (getStatus(jsonObject)){
                String formId = String.valueOf(jsonObject.getJSONObject("data").get("transportorderid"));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                JSONObject checkJsonObject  = FabricMethod.checkOilHairOrderAndTransportOrder(batchNumberService.getBatchNumberById(id));
                if (getStatus(checkJsonObject)){
                    infoService.saveTransForm(chainTransportationDTO,formId);
                    return Result.successData(formId);
                }else {
                    return Result.error(checkJsonObject.getString("message"));
                }
            }else {
                return jsonObject;
            }
        }
    }

    public Object uploadReceiveOrder(ReceiveDTO receiveDTO, String id){
       if (orderFormMapper.checkIdIsExist(id)==0){
           return orderFormMapper.checkIdIsExist(id);
       }else{
           ChainReceiveDTO chainReceiveDTO = typeChangeService.getChainReceiveDTO(receiveDTO,id);
           String userID = userService.getCurrentUser().getId();
           JSONObject jsonObject = FabricMethod.createOilAcceptOrder(chainReceiveDTO,userID);
           if (getStatus(jsonObject)){
               JSONObject checkJson = null;
               String role = userService.getCurrentUser().getRole();
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   log.error(e.getMessage());
               }
               //获取到的角色油库
               if ("2".equals(role)|| "4".equals(role)){
                       checkJson = FabricMethod.checkAcceptOrderAndOilHairOrder(batchNumberService.getBatchNumberById(id));
               }
               //获取到的角色是加油站
               else if ("5".equals(role)){
                       checkJson = FabricMethod.checkAcceptOrderAndTransportOrder(batchNumberService.getBatchNumberById(id));

               }
               assert checkJson != null;
               if (getStatus(checkJson)){
                   return Result.error(checkJson.getString("message"));
               }
               String formId = String.valueOf(jsonObject.getJSONObject("data").get("oilacceptorderid"));
               infoService.saveReceiveForm(chainReceiveDTO,formId,userID);
               return Result.successData(formId);
           }else{
               return jsonObject;
           }
       }
    }

    public Object uploadSellOrder(SellDTO sellDTO){
        String sellerID = userService.getCurrentUser().getId();
        ChainSellDTO chainSellDTO = typeChangeService.getChainSellDTO(sellDTO,sellerID);
        JSONObject jsonObject = FabricMethod.createSellOrder(chainSellDTO);
        if (getStatus(jsonObject)){
            String formId = String.valueOf(jsonObject.getJSONObject("data").get("sellorderid"));
            log.info("返回的订单ID为"+formId);
            infoService.saveSellForm(chainSellDTO,formId);
            return Result.success();
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
        return jsonObject.containsKey("data");
    }
}
