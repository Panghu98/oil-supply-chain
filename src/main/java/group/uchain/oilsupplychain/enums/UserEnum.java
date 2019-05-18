package group.uchain.oilsupplychain.enums;

import lombok.Getter;

/**
 * @author panghu
 * @title: UserEnum
 * @projectName demo
 * @date 19-5-3 上午10:50
 */
@Getter
public enum UserEnum {
    /***/
    USER_REFINERY("Refinery_","炼油厂 1"),
    USER_OIL_DEOPT("OilDepoter_","油库  2和4"),
    USER_GAS_STATION("GasStation_","加油站 5")
    ;

    private String role;

    private String tip;

    UserEnum(String role, String tip) {
        this.role = role;
        this.tip = tip;
    }
}
