package group.uchain.oilsupplychain.controller;

import group.uchain.oilsupplychain.exception.OilException;
import group.uchain.oilsupplychain.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @author panghu
 * @Title: AnonController
 * @ProjectName oil-supply-chain
 * @Description: TODO
 * @date 19-3-11 下午2:52
 */
@RequestMapping("/oil/anon")
@Api(tags = "匿名接口")
@CrossOrigin
@RestController
public class AnonController {

    private LoginService loginService;


    @Autowired
    public AnonController(LoginService loginService) {
        this.loginService = loginService;
    }

    @ApiOperation(value = "用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true)
    })
    @PostMapping("/login")
    public Map login(String username,String password) {
        return loginService.login(username, password);
    }

    @ApiIgnore
    @GetMapping("/test")
    public void test(){
        throw new OilException("测试");
    }

}
