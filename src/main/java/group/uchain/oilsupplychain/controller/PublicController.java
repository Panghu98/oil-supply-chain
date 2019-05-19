package group.uchain.oilsupplychain.controller;

import group.uchain.oilsupplychain.annotation.Pass;
import group.uchain.oilsupplychain.annotation.RoleRequired;
import group.uchain.oilsupplychain.dto.*;
import group.uchain.oilsupplychain.enums.RoleEnum;
import group.uchain.oilsupplychain.exception.OilException;
import group.uchain.oilsupplychain.service.impl.ActionService;
import group.uchain.oilsupplychain.service.impl.InfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author panghu
 * @title: PublicController
 * @projectName oil-supply-chain
 * @date 19-4-6 下午3:43
 */
@Slf4j
@CrossOrigin(origins = "*")
@Api(tags = "数据操作")
@RequestMapping("/oil/action")
@RestController
@Transactional(rollbackFor = OilException.class)
public class PublicController {

    private InfoService infoService;

    private ActionService actionService;

    @Autowired
    public PublicController(InfoService infoService,ActionService actionService) {
        this.infoService = infoService;
        this.actionService = actionService;
    }

    /*------------------------------------------------------------------------------------------------
    ---------------------------------------上传订单的相关接口----------------------------------------
    --------------------------------------------------------------------------------------------------*/

    @Pass
    @ApiOperation(value = "上传申油单")
    @RoleRequired(value = RoleEnum.STATION_AND_DEPOT)
    @PostMapping("/apply")
    public Object apply(ApplyDTO applyDTO){
        return actionService.uploadApplyOrder(applyDTO);
    }


    @ApiOperation(value = "上传发油单")
    @RoleRequired(value = RoleEnum.DEOPT_AND_REFINERY)
    @PostMapping("/send")
    public Object send(SendDTO sendDTO,String id){
        return actionService.uploadSendOrder(sendDTO,id);
    }


    @RoleRequired(value = RoleEnum.DEOPT_AND_REFINERY)
    @ApiOperation(value = "上传运输申请单")
    @PostMapping("/transApply")
    public Object transApply(TransportationApplyDTO transportationApplyDTO,String id){
        return actionService.uploadTransApplyOrder(transportationApplyDTO,id);
    }

    @ApiOperation(value = "上传运输单")
    @RoleRequired(value = RoleEnum.TRANSPORTATION_COMPANY)
    @PostMapping("/trans")
    public Object trans(TransportationDTO transportationDTO, String id){
        return actionService.uploadTransOrder(transportationDTO,id);
    }


    @ApiOperation(value = "上传收油单")
    @RoleRequired(value = RoleEnum.STATION_AND_DEPOT)
    @PostMapping("/receive")
    public Object receive(ReceiveDTO receiveDTO, String id){
        return actionService.uploadReceiveOrder(receiveDTO,id);
    }

    @ApiOperation(value = "上传销售单")
    @RoleRequired(value = RoleEnum.PETROL_STATION)
    @PostMapping("/sell")
    public Object sell(SellDTO sellDTO){
        return actionService.uploadSellOrder(sellDTO);
    }


    /*------------------------------------------------------------------------------------------------
    ---------------------------------------获取区块链核验结果 和 更改订单状态---------------------------
    --------------------------------------------------------------------------------------------------*/

    @Pass
    @ApiOperation(value = "获取系统油品申请订单审核详情")
    @GetMapping("/getApplyDetail")
    @RoleRequired(RoleEnum.DEOPT_AND_REFINERY)
    public Object checkRequestOrder(String batchNumber){
        return actionService.getApplyDetail(batchNumber);
    }

    @ApiOperation(value = "获取系统运输申请订单审核详情")
    @GetMapping("/getTransApplyDetail")
    @RoleRequired(RoleEnum.TRANSPORTATION_COMPANY)
    public Object getTransApplyDetail(String batchNumber){
        return actionService.getTransApplyDetail(batchNumber);
    }

    @ApiOperation(value = "更改待审核订单的状态")
    @RoleRequired(RoleEnum.DEPOT_REFINERY_TRANSPORTATION)
    @PostMapping("/updateStatus")
    public Object updateStatus(String id,String orderType,String status){
        return infoService.updateApplyFormStatus(id,orderType,status);
    }


}
