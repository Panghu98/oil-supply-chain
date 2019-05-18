package group.uchain.oilsupplychain.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import group.uchain.oilsupplychain.dto2.*;
import group.uchain.oilsupplychain.fabric.ChaincodeManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;


/**
 * @author panghu
 */
@Slf4j
public class FabricMethod {
    private static ChaincodeManager chaincodeManager;

    private final static String SUCCESS = "200";

    private final static String ERROR = "error";

    static {
        try {
            chaincodeManager = FabricManagerMethod.obtain().getManager(1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 用户注册并设置初始值
     * @return
     */
    public static JSONObject userInit(ChainUserDTO chainUserDTO){
        String[] args= {String.valueOf(chainUserDTO.getId()), String.valueOf(chainUserDTO.getRole()),
                        chainUserDTO.getOilReserve1(),chainUserDTO.getOilReserve2(),
                        chainUserDTO.getOilReserve3(),chainUserDTO.getOilReserve4()};
        JSONObject jsonObject = null;
        try {
            jsonObject = chaincodeManager.invoke("UserInit",args);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return jsonObject;
    }

    /**
     * 运输商的注册和初始化值
     * @param id 用户ID
     * @param railwayTrafficVolume 铁路运输量
     * @param pipeTrafficVolume 管道运输量
     * @param seaTrafficVolume  水运运输量
     * @return
     */
    public static JSONObject transporterInit(String id, float railwayTrafficVolume, float pipeTrafficVolume, float seaTrafficVolume,
                                             float highwayTrafficVolume){
        String[] args = {id, String.valueOf(railwayTrafficVolume), String.valueOf(pipeTrafficVolume), String.valueOf(seaTrafficVolume),
                String.valueOf(highwayTrafficVolume)};
        JSONObject jsonObject = null;
        try{
            jsonObject = chaincodeManager.invoke("TransporterInit",args);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return jsonObject;
    }

    /**
     * 查询油品剩余量
     * @param id 用户编号
     * @return
     */
    public static JSONObject queryOilReserve(String id){
        String[] args ={id} ;
        JSONObject jsonObject = null;
        try{
            jsonObject = chaincodeManager.invoke("QueryOilReserve",args);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return getJsonObject(jsonObject);
    }

    /**
     * 加油站生成申油单
     * @return
     */
    public static JSONObject createRequestOilOrder(ChainApplyDTO chainApplyDTO){
        String[] args ={chainApplyDTO.getBatchNumber(),chainApplyDTO.getVariety(), chainApplyDTO.getCount(),
                        chainApplyDTO.getSendTime(),chainApplyDTO.getSender(),chainApplyDTO.getReceiver()} ;
        JSONObject jsonObject = null;
        try{
            jsonObject = chaincodeManager.invoke("CreateRequestOilOrder",args);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return getJsonObject(jsonObject);
    }


    /**
     * 加油站查询待审核申请订单
     * @param id 接收者编号
     * @return
     */
    public static JSONObject queryWaitOilRequestOrder(String id){
        String[] args ={id} ;
        JSONObject jsonObject = null;
        try{
            jsonObject = chaincodeManager.invoke("QueryWaitOilRequestOrder",args);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return getJsonObject(jsonObject);
    }

    /**
     * 油库审核申请单-
     * @param batchNumber 统一编号
     * @return
     */
    public static JSONObject checkRequestOrder(String batchNumber){
        String[] args ={batchNumber} ;
        JSONObject jsonObject = null;
        try{
            jsonObject = chaincodeManager.invoke("CheckRequestOrder",args);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info(String.valueOf(getJsonObject(jsonObject)));
        return getJsonObject(jsonObject);


    }

    /**
     * 运输申请订单发起
     *  batchNumber 统一编号
     *  typeNumber 油品号
     *  volume 容量
     *  transportationfirmId 运输商编号
     *  sendId 发送者编号
     *  acceptId 接收者编号
     * @return
     */
    public static JSONObject createOilTransRequestOrder(ChainTransportationApplyDTO chainTransportationApplyDTO) {
        String[] args ={chainTransportationApplyDTO.getBatchNumber(),chainTransportationApplyDTO.getVariety(),
                        chainTransportationApplyDTO.getCount(),chainTransportationApplyDTO.getCompany(),
                        chainTransportationApplyDTO.getSender(),chainTransportationApplyDTO.getReceiver(),
                        chainTransportationApplyDTO.getSendTime()} ;
        JSONObject jsonObject = null;
        try{
            jsonObject = chaincodeManager.invoke("CreateOilTransRequestOrder",args);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return getJsonObject(jsonObject);
    }

    /**
     * 运输商查询待审核运输申请订单
     * @param acceptId 接收者的编号
     * @return
     */
    public static JSONObject queryWaitOilTransRequestOrder(String acceptId){
        String[] args ={acceptId} ;
        JSONObject jsonObject = null;
        try{
            jsonObject = chaincodeManager.invoke("QueryWaitOilTransRequestOrder",args);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return getJsonObject(jsonObject);
    }

    /**
     * 运输商审核运输申请订单
     * @param batchNumber 统一编号
     * @return
     */
    public static JSONObject checkOilTransRequestOrder(String batchNumber){
        String[] args = {batchNumber};
        JSONObject jsonObject = null;
        try{
            jsonObject = chaincodeManager.invoke("CheckOilTransRequestOrder",args);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return getJsonObject(jsonObject);

    }

    /**
     *上传发油单
     *  batchNumber 统一编号
     *  typeNumber 油品号
     *  volume 油量
     *  sendId 发送者ID
     *  acceptId 接收者ID
     *  samplingtest 抽样检测结果
     *  qualify 质量
     *  state 状态
     * @return 位置
     */
    public static JSONObject createSendForm(ChainSendDTO chainSendDTO){
        String[] args = {chainSendDTO.getBatchNumber(),chainSendDTO.getVariety(), chainSendDTO.getCount(),chainSendDTO.getSender(),
                        chainSendDTO.getSender(),chainSendDTO.getSampleStatus(),chainSendDTO.getCertification()};
        JSONObject jsonObject = null;
        try{
            jsonObject = chaincodeManager.invoke("CreateOilHairOrder",args);
        }catch (Exception e){

            log.error(e.getMessage());
        }
        return getJsonObject(jsonObject);

    }

    /**
     * 检测申请单和发油单信息
     * @param batchNumber 统一编号
     * @return
     */
    public static JSONObject checkOilRequestOrderAndOilHairOrder(String batchNumber) throws Exception {
        String[] args = {batchNumber};
        JSONObject jsonObject = null;
            jsonObject = chaincodeManager.invoke("CheckoilRequestOrderAndOilHairOrder",args);
        return getJsonObject(jsonObject);
    }

    /**
     *生成运输订单
     *  batchNumber 统一编号
     *  typeNumber 油品号
     *  oilTankNumber 油罐号
     *  sendId 发送者编号
     *  acceptId 接收者ID
     *  transporterId 运输商编号
     *  少了数量
     * @return
     */
    public static JSONObject creatTransportOrder(ChainTransportationDTO chainTransportationDTO){
        String[] args = {chainTransportationDTO.getBatchNumber(),chainTransportationDTO.getVariety(),
                        chainTransportationDTO.getEquipmentNumber(),chainTransportationDTO.getSender(),chainTransportationDTO.getReceiver(),
                        chainTransportationDTO.getCompany(),chainTransportationDTO.getCount()};
        JSONObject jsonObject = null;
        try{
            jsonObject = chaincodeManager.invoke("CreatTransportOrder",args);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return getJsonObject(jsonObject);
    }

    /**
     * 核验发发油单和运输单信息是否一致
     * @param batchNumber 批次号
     * @return
     */
    public static JSONObject checkOilHairOrderAndTransportOrder(String batchNumber) throws Exception {
        String[] args = {batchNumber};
        JSONObject jsonObject = null;
            jsonObject = chaincodeManager.invoke("CheckOilHairOrderAndTransportOrder",args);
        return getJsonObject(jsonObject);
    }

    /**
     * 生成收油单
     *  batchNumber 统一编号
     *  acceptId  收油者ID
     *  volume 油量
     *  samplingTest 抽样检测结果
     *  qualify 质量
     *  state 状态
     *              缺少油品品号
     * @return
     */
    public static JSONObject createOilAcceptOrder(ChainReceiveDTO chainReceiveDTO, String userID){
        String[] args = {chainReceiveDTO.getBatchNumber(),chainReceiveDTO.getVariety(),userID,
                        chainReceiveDTO.getCount(),chainReceiveDTO.getSampleStatus(),chainReceiveDTO.getCertification(),
                        chainReceiveDTO.getSenderID()};
        JSONObject jsonObject = null;
        try{
            jsonObject = chaincodeManager.invoke("CreateOilAcceptOrder",args);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return getJsonObject(jsonObject);
    }


    /**
     * 核验收油单和运输订单数据（油库，运输商，加油站）
     * @param batchNumber 统一编号
     * @return
     */
    public static JSONObject checkAcceptOrderAndTransportOrder(String batchNumber) throws Exception {
        String[] args = {batchNumber};
        JSONObject jsonObject = null;
            jsonObject = chaincodeManager.invoke("CheckAcceptOrderAndTransportOrder",args);
        return getJsonObject(jsonObject);
    }

    /**
     * 核验收油单和发油单数据 （油库，运输商，炼油厂）
     * @param batchNumber  统一编号
     * @return
     */
    public static JSONObject checkAcceptOrderAndOilHairOrder(String batchNumber) throws Exception {
        String[] args = {batchNumber};
        JSONObject jsonObject = null;
            jsonObject = chaincodeManager.invoke("CheckAcceptOrderAndOilHairOrder",args);
        return getJsonObject(jsonObject);

    }

    /**
     * 生成销售单
     *  typeNumber 油品号
     *  sellId 售油者ID
     *  volume 油量
     *  balance 金额
     *  price 单价
     *  payType 支付方式　　Ｃash,OilCard
     * @return
     */
    public static JSONObject createSellOrder(ChainSellDTO chainSellDTO){
        String[] args = {chainSellDTO.getVariety(),chainSellDTO.getSellerId(),
                        String.valueOf(chainSellDTO.getCapacity()), String.valueOf(chainSellDTO.getUnivalent()),
                        String.valueOf(chainSellDTO.getMoney()), String.valueOf(chainSellDTO.getPay())};
        JSONObject jsonObject = null;
        try{
            jsonObject = chaincodeManager.invoke("CreateSellOrder",args);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return getJsonObject(jsonObject);
    }

    /**
     *查询用户拥有的某一类型的历史订单
     * @param id  用户ID
     * @param orderType  SellOrder,OilAcceptOrder,TransportOrder,OilTransRequestOrder,RefineryOilTransRequestOrder,
     *                   OilHairOrder,OilRequestOrder
     * @return
     */
    public static JSONObject queryUserOrder(String id, String orderType){
        String[] args = {id,orderType};
        JSONObject jsonObject = null;
        try{
            jsonObject = chaincodeManager.invoke("QueryUserOrder",args);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return getJsonObject(jsonObject);
    }

    /**
     * 查询某一批次的所有单子
     * @param batchNumber 批次号
     * @return
     */
    public static Object queryOrderList(String batchNumber){
        String[] args = {batchNumber};
        JSONObject jsonObject = null;
        try{
            jsonObject = chaincodeManager.invoke("QueryOrderList",args);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        assert jsonObject != null;
        if (!jsonObject.containsKey(ERROR)){
            String result = jsonObject.getString("payload");
            StringBuffer stringBuffer = new StringBuffer(result);
            stringBuffer.insert(0,"[");
            stringBuffer.insert(stringBuffer.length(),"]");
            return JSONArray.parseArray(String.valueOf(stringBuffer));
        }else {
            String status = jsonObject.getString("status");
            String message = String.valueOf(jsonObject.getString("error"));
            JSONObject returnJsonObject = null;
            returnJsonObject.put("code",status);
            returnJsonObject.put("message",message);
            return returnJsonObject;
        }
    }


    /**
     *  更改表单的状态
     * @param orderId  订单ID
     * @param orderType  SellOrder,OilAcceptOrder,TransportOrder,OilTransRequestOrder,RefineryOilTransRequestOrder,
     *                   OilHairOrder,OilRequestOrder
     * @param state  状态码
     * @return
     */
    public static JSONObject changeState(String orderId, String orderType, String state){
        String[] args = {orderId,orderType,state};
        JSONObject jsonObject = null;
        try{
            jsonObject = chaincodeManager.invoke("ChangeState",args);
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return getJsonObject(jsonObject);
    }

    private static JSONObject getJsonObject(JSONObject jsonObject) {
        String status = Objects.requireNonNull(jsonObject).getString("status");
        JSONObject returnJsonObject = new JSONObject();
        if (status.equals(SUCCESS)){
            returnJsonObject.put("data",jsonObject.getJSONObject("payload"));
        }else {
            String message = String.valueOf(jsonObject.getString("error"));
            returnJsonObject.put("code",status);
            returnJsonObject.put("message",message);
        }
        return returnJsonObject;
    }

}
