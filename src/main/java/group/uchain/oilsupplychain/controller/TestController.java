package group.uchain.oilsupplychain.controller;

import group.uchain.oilsupplychain.annotation.Pass;
import group.uchain.oilsupplychain.dto2.ChainUserDTO;
import group.uchain.oilsupplychain.mapper.OrderFormMapper;
import group.uchain.oilsupplychain.utils.FabricMethod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author panghu
 * @title: TestController
 * @projectName oil-supply-chain
 * @date 19-4-11 下午1:00
 */
@ApiIgnore
@Api(tags = "测试接口")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private OrderFormMapper orderFormMapper;

    @Pass
    @ApiIgnore
    @ApiOperation(value = "普通用户注册---区块链角色注册")
    @PostMapping("/userInit")
    public Object userInit(ChainUserDTO chainUserDTO){
        Object res = FabricMethod.userInit(chainUserDTO);
        System.out.println(res);
        return res;
    }

    @Pass
    @ApiIgnore
    @ApiOperation(value="运输商注册---区块链角色注册")
    @PostMapping("/transUserInit")
    public Object transUserInit(String id,float railwayTrafficVolume,float pipeTrafficVolume,float seaTrafficVolume,
                                float highwayTrafficVolume){
        Object res = FabricMethod.transporterInit(id,railwayTrafficVolume,pipeTrafficVolume,seaTrafficVolume,highwayTrafficVolume);

        return res;
    }

    @GetMapping("/checkIdIsExist")
    public Integer checkIdIsExist(String id){
        return orderFormMapper.checkIdIsExist(id);
    }

    @GetMapping("/checkOilRequestOrderAndOilHairOrder")
    public Object checkOilRequestOrderAndOilHairOrder(String batchNumber){
        return FabricMethod.checkOilRequestOrderAndOilHairOrder(batchNumber);
    }



}
