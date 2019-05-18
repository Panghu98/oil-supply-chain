package group.uchain.oilsupplychain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author panghu
 * @title: ViewUser
 * @projectName oil-supply-chain
 * @date 19-4-4 下午9:31
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ViewUser {

    private Integer id;

    private String username;

    /**
     * 1.炼油厂
     * 2.省级油库
     * 3.运输公司
     * 4.地级市油库
     * 5.加油站
     */
    private String role;

}
