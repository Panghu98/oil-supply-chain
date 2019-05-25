package group.uchain.oilsupplychain.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import group.uchain.oilsupplychain.dto2.*;
import group.uchain.oilsupplychain.form.OrderForm;
import group.uchain.oilsupplychain.mapper.OrderFormMapper;
import group.uchain.oilsupplychain.result.Result;
import group.uchain.oilsupplychain.service.UserService;
import group.uchain.oilsupplychain.utils.FabricMethod;
import group.uchain.oilsupplychain.utils.IDUtil;
import group.uchain.oilsupplychain.utils.StatusUtil;
import group.uchain.oilsupplychain.vo.ApplyOrdersVO;
import group.uchain.oilsupplychain.vo.OrdersVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


/**
 * @author panghu
 * @title: InfoService
 * @projectName oil-supply-chain
 * @date 19-4-6 上午11:14
 * @Description: 1.对于历史订单的查看,存储 2.对待审核订单的查看以及状态的修改
 */
@Slf4j
@Service
public class InfoService {

    private OrderFormMapper orderFormMapper;

    private UserService userService;

    private TypeChangeService typeChangeService;


    @Autowired
    public InfoService(OrderFormMapper orderFormMapper,
                       UserService userService,TypeChangeService typeChangeService)  {
        this.userService = userService;
        this.orderFormMapper = orderFormMapper;
        this.typeChangeService = typeChangeService;
    }

    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     *存储历史订单吗,这个历史订单的ID是区块链返回的  然后调用了这个方法
     * @param chainApplyDTO
     */
    public void saveApplyForm(ChainApplyDTO chainApplyDTO, String id){
        OrderForm form = new OrderForm();
        form.setId(id);
        form.setBatchNumber(chainApplyDTO.getBatchNumber());
        TimeZone time = TimeZone.getTimeZone("ETC/GMT-8");
        TimeZone.setDefault(time);
        form.setDate(format.format(new Date()));
        form.setType("1");
        form.setStatus("1");
        form.setFrom(chainApplyDTO.getReceiver());
        form.setTo(chainApplyDTO.getSender());
        orderFormMapper.insert(form);
    }

    public void saveSendForm(ChainSendDTO chainSendDTO, String id){
        OrderForm form = new OrderForm();
        form.setId(id);
        form.setDate(format.format(new Date()));
        form.setBatchNumber(chainSendDTO.getBatchNumber());
        form.setFrom(chainSendDTO.getSender());
        form.setTo(chainSendDTO.getReceiver());
        form.setType("2");
        form.setStatus("1");
        orderFormMapper.insert(form);
    }

    public void saveTransApplyForm(ChainTransportationApplyDTO chainTransportationApplyDTO, String id){
        OrderForm form = new OrderForm();
        form.setDate(format.format(new Date()));
        form.setId(id);
        form.setStatus("1");
        form.setBatchNumber(chainTransportationApplyDTO.getBatchNumber());
        form.setFrom(chainTransportationApplyDTO.getSender());
        form.setTo(chainTransportationApplyDTO.getCompany());
        form.setType("3");
        orderFormMapper.insert(form);
    }

    public void saveTransForm(ChainTransportationDTO chainTransportationDTO, String id){
        OrderForm form = new OrderForm();
        form.setDate(format.format(new Date()));
        form.setId(id);
        form.setStatus("1");
        form.setBatchNumber(chainTransportationDTO.getBatchNumber());
        form.setFrom(chainTransportationDTO.getCompany());
        form.setTo(chainTransportationDTO.getReceiver());
        form.setType("4");
        orderFormMapper.insert(form);
    }

    public void saveReceiveForm(ChainReceiveDTO chainReceiveDTO, String id,String userID){
        OrderForm form = new OrderForm();
        form.setDate(format.format(new Date()));
        form.setId(id);
        form.setStatus("1");
        form.setBatchNumber(chainReceiveDTO.getBatchNumber());
        form.setFrom(userID);
        form.setTo(userID);
        form.setType("5");
        orderFormMapper.insert(form);
    }

    public void saveSellForm(ChainSellDTO chainSellDTO,String id){
        OrderForm form = new OrderForm();
        form.setBatchNumber("");
        form.setDate(format.format(new Date()));
        form.setTo("终端用户"+ IDUtil.getRandom());
        form.setStatus("1");
        form.setId(id);
        form.setFrom(chainSellDTO.getSellerId());
        form.setType("6");
        orderFormMapper.insert(form);
    }


    /**
     * 获取历史订单
     * @return
     */
    public Result<List<OrdersVO>> selectSendForms() {
        List<OrdersVO> list = orderFormMapper.selectSendForms(userService.getCurrentUser().getId());
        return Result.successData(list);
    }

    public Result<List<OrdersVO>> selectTransApplyForms() {
        List<OrdersVO> list = orderFormMapper.selectTransApplyForms(userService.getCurrentUser().getId());
        return Result.successData(list);
    }

    public Result<List<OrdersVO>> selectReceiveForms() {
        List<OrdersVO> list =  orderFormMapper.selectReceiveForms(userService.getCurrentUser().getId());
        return Result.successData(list);
    }

    public Result<List<OrdersVO>> selectTransForms() {
        List<OrdersVO> list  = orderFormMapper.selectTransForms(userService.getCurrentUser().getId());
        return Result.successData(list);
    }

    public Result<List<OrdersVO>> selectSellForms() {
        List<OrdersVO> list = orderFormMapper.selectSellForms(userService.getCurrentUser().getId());
        return Result.successData(list);
    }

    public Result selectApplyForms() {
        List<OrdersVO> list =  orderFormMapper.selectApplyForms(userService.getCurrentUser().getId());
        return Result.successData(list);
    }


    /**
     * 获取待审核订单
     */
    public Result<List<ApplyOrdersVO>> getTransApplyForm(){
        List<ApplyOrdersVO> list = orderFormMapper.getTransApplyForm(userService.getCurrentUser().getId());
        return Result.successData(list);
    }

    public Result<List<ApplyOrdersVO>> getApplyForms(){
        List<ApplyOrdersVO> list = orderFormMapper.getApplyForm(userService.getCurrentUser().getId());
        return Result.successData(list);
    }

    /**
     * 更改待审核订单状态
     */
    public Object updateApplyFormStatus(String orderID,String orderType,String status){
        orderFormMapper.updateApplyFormStatus(StatusUtil.getStatus(status),orderID);
        FabricMethod.changeState(orderID,orderType,status);
        return Result.success();
    }

    public Object getTrance(String batchNumber){
        if (FabricMethod.queryOrderList(batchNumber).getClass()!= JSONArray.class){
            return FabricMethod.queryOrderList(batchNumber);
        }else{
            JSONArray jsonArray = (JSONArray) FabricMethod.queryOrderList(batchNumber);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Long acceptID = jsonObject.getLongValue("acceptid");
                Long sendID = jsonObject.getLongValue("sentid");
                String acceptName = typeChangeService.getNameById(acceptID);
                String sendName = typeChangeService .getNameById(sendID);
                jsonObject.put("acceptName",acceptName);
                jsonObject.put("sendName",sendName);
                jsonObject.remove("acceptid");
                jsonObject.remove("sentid");
            }
            return Result.successData(jsonArray);
        }

    }

}
