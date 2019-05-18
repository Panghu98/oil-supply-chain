package group.uchain.oilsupplychain.controller;

import group.uchain.oilsupplychain.annotation.Pass;
import group.uchain.oilsupplychain.annotation.RoleRequired;
import group.uchain.oilsupplychain.enums.RoleEnum;
import group.uchain.oilsupplychain.mapper.InfoMapper;
import group.uchain.oilsupplychain.result.Result;
import group.uchain.oilsupplychain.service.impl.InfoService;
import group.uchain.oilsupplychain.utils.FabricMethod;
import group.uchain.oilsupplychain.vo.ApplyOrdersVO;
import group.uchain.oilsupplychain.vo.OrdersVO;
import group.uchain.oilsupplychain.vo.ViewUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author panghu
 * @Title: InfoController
 * @ProjectName oil-supply-chain
 * @date 19-4-4 下午6:30
 */
@Api(tags = "数据查询")
@RequestMapping("/info")
@CrossOrigin
@RestController
public class InfoController {

    private InfoMapper infoMapper;

    private InfoService infoService;

    @Autowired
    public InfoController(InfoMapper infoMapper,InfoService infoService) {
        this.infoMapper = infoMapper;
        this.infoService = infoService;
    }


    @ApiOperation(value = "获取所有的运输公司")
    @GetMapping("/getAllCompany")
    public List<ViewUser> getAllCompany(){
        return infoMapper.getAllCompany();
    }

    @ApiOperation(value = "获取炼油厂,加油站,油库")
    @GetMapping("/getAllUser")
    public List<ViewUser> getAllUser(){
        return infoMapper.getAllUser();
    }



     /*------------------------------------------------------------------------------------------------
    ---------------------------------------查询历史订单的相关接口----------------------------------------
    --------------------------------------------------------------------------------------------------*/

    @ApiOperation(value = "获取所有历史申请单")
    @RoleRequired(RoleEnum.STATION_AND_DEPOT)
    @GetMapping("/selectApplyForms")
    public List<OrdersVO> selectApplyForms(){
        return infoService.selectApplyForms();
    }

    @ApiOperation(value = "获取所有历史发油单")
    @RoleRequired(RoleEnum.DEOPT_AND_REFINERY)
    @GetMapping("/selectSendForms")
    public List<OrdersVO> selectSendForms(){
        return infoService.selectSendForms();
    }

    @ApiOperation(value = "获取所有历史运输申请单")
    @RoleRequired(RoleEnum.DEOPT_AND_REFINERY)
    @GetMapping("/selectTransApplyForms")
    public List<OrdersVO> selectTransApplyForms(){
        return infoService.selectTransApplyForms();
    }

    @ApiOperation(value = "获取所有历史运输单")
    @RoleRequired(RoleEnum.PETROL_STATION)
    @GetMapping("/selectTransForms")
    public List<OrdersVO> selectTransForms(){
        return infoService.selectTransForms();
    }

    @ApiOperation(value = "获取所有历史收油单")
    @RoleRequired(RoleEnum.STATION_AND_DEPOT)
    @GetMapping("/getReceiveForms")
    public List<OrdersVO> getReceiveForms(){
        return infoService.selectReceiveForms();
    }

    @ApiOperation(value = "获取所有历史销售单")
    @RoleRequired(RoleEnum.PETROL_STATION)
    @GetMapping("/selectSellForms")
    public List<OrdersVO> selectSellForms(){
        return infoService.selectSellForms();
    }


    /*------------------------------------------------------------------------------------------------
    ---------------------------------------获取审核订单的相关接口  数据库-------------------------------
    --------------------------------------------------------------------------------------------------*/

    @ApiOperation(value = "获取待审核油品申请订单")
    @RoleRequired(RoleEnum.DEOPT_AND_REFINERY)
    @GetMapping("/getApplyForms")
    public Result getApplyForms(){
        return infoService.getApplyForms();
    }

    @ApiOperation(value = "获取待审核运输申请订单")
    @RoleRequired(RoleEnum.TRANSPORTATION_COMPANY)
    @GetMapping("/getTransApplyForm")
    public Result<List<ApplyOrdersVO>> getTransApplyForm(){
        return infoService.getTransApplyForm();
    }

 /*------------------------------------------------------------------------------------------------
    ---------------------------------------获取审核订单详情的相关接口 区块链网络------------------------
    --------------------------------------------------------------------------------------------------*/


    /**
     * 查看运输申请订单详情
     * */
    @ApiIgnore
    @ApiOperation(value = "运输商查看待审核运输申请订单")
    @GetMapping("/orderTransApplyDetail")
    public Object queryWaitOilTransRequestOrder(String id){
        return FabricMethod.queryWaitOilTransRequestOrder(String.valueOf(id));
    }

    /**
     * 查看油品申请订单详情
     * @param id  运输商的ＩＤ
     * @return
     */
    @ApiIgnore
    @ApiOperation(value = "油库和炼油厂查看待审核订单")
    @GetMapping("/queryWaitOilRequestOrder")
    public Object queryWaitOilRequestOrder(String id){
        return FabricMethod.queryWaitOilRequestOrder(String.valueOf(id));
    }

    /*-----------------------------------------------------------------------------------------------------
        ------------------------------------获取  订单详情  所有订单 -------------------------------------
        --------------------------------------------------------------------------------------------------
     */

    /***
     * 查看订单详情  所有类型
     * @param id
     * @param orderType
     * @return
     */
    @ApiOperation(value = "查询用户拥有的某一类型的历史订单")
    @GetMapping("/queryUserOrder")
    public Object queryUserOrder(String id,String orderType){
        return FabricMethod.queryUserOrder(id,orderType);
    }

    /**
     * 获取这个批次油品的流程
     * @param batchNumber 批次号
     * @return
     */
    @ApiOperation(value = "通过批次号进行溯源")
    @GetMapping("/getTrance")
    public Object getTrance(String batchNumber){
        return infoService.getTrance(batchNumber);
    }

    @Pass
    @ApiOperation(value = "查询剩余储油量")
    @GetMapping("/queryOilReserve")
    public Object queryOilReserve(String id){
        return FabricMethod.queryOilReserve(id);
    }
}
