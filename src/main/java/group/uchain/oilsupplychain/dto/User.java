package group.uchain.oilsupplychain.dto;

import lombok.Data;

/**
 * @author panghu
 * @Title: User
 * @ProjectName oil-supply-chain
 * @Description: TODO
 * @date 19-3-13 下午1:01
 */
@Data
public class User {

    private String id;
    private String username;
    private String password;
    /**
     * 1 炼油厂  ，2 省级油库，3运输公司，4地级市油库，5加油站
     */
    private String role;

}
