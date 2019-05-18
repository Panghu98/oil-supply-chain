package group.uchain.oilsupplychain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author panghu
 */

@Getter
@AllArgsConstructor
public enum CodeMsg {
    /***/
    AUTHENTICATION_ERROR(401, "用户认证失败,请重新登录"),
    PASSWORD_ERROR(402, "密码错误"),
    PERMISSION_DENNY(403, "权限不足"),
    USER_NOT_EXIST(404,"用户不存在"),
    UN_KNOWN_FABRIC_ERROR(500, "Fabric未知错误"),
    USER_ALREADY_EXIST(501,"用户已存在"),
    OIlORDER_ALREADY_EXIST(502,"订单已存在"),
    OIlORDER_NOT_EXIST(503,"订单不存在" ),
    BEEN_CHECKED(504,"订单已经审核");


    private Integer code;
    private String msg;

}
