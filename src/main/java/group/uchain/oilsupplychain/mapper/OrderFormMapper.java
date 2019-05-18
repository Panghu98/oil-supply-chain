package group.uchain.oilsupplychain.mapper;

import group.uchain.oilsupplychain.form.OrderForm;
import group.uchain.oilsupplychain.vo.ApplyOrdersVO;
import group.uchain.oilsupplychain.vo.OrdersVO;

import java.util.List;

/**
 * @author panghu
 */
public interface OrderFormMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(OrderForm record);

    OrderForm selectByPrimaryKey(Integer id);

    List<OrderForm> selectAll();

    int updateByPrimaryKey(OrderForm record);

    /*------------------通过id获取历史订单--------------------------*/

    List<OrdersVO> selectReceiveForms(String id);

    List<OrdersVO> selectSendForms(String id);

    List<OrdersVO> selectSellForms(String id);

    List<OrdersVO> selectTransForms(String id);

    List<OrdersVO> selectTransApplyForms(String id);

    List<OrdersVO> selectApplyForms(String id);

    /**
     * 通过ID获得批次号 ID是唯一的
     * @param id 上一级订单的ＩＤ号
     * @return 批次号
     */
    String getBatchNumberById(String id);

    /*-------------------获取待审核的订单---------------------------*/

    List<ApplyOrdersVO> getApplyForm(String id);

    List<ApplyOrdersVO> getTransApplyForm(String id);

    /*-------------------更改待审核订单状态-------------------------*/

    void updateApplyFormStatus(String status, String id);

}