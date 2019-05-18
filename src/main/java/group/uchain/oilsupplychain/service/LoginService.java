package group.uchain.oilsupplychain.service;

import java.util.Map;

/**
 * @author panghu
 * @Title: LoginService
 * @ProjectName oil-supply-chain
 * @date 19-3-13 上午9:27
 */
public interface LoginService {
    /**
     * @description 用户登录接口
     * @param username 用户名
     * @param password 密码
     * @return 返回给前端的结果
     */
    Map login(String username, String password);
}
