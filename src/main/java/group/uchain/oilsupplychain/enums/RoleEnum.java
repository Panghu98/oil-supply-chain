package group.uchain.oilsupplychain.enums;

import lombok.Getter;

/**
 * @author panghu
 * @Title: RoleEnum
 * @ProjectName oil-supply-chain
 * @Description: TODO
 * @date 19-2-24 下午5:01
 */
@Getter
public enum RoleEnum {

    /***/
    OIL_REFINERY(1, "炼油厂"),
    OIL_DEPOT(24,"油库"),
    TRANSPORTATION_COMPANY(3,"运输公司"),
    PETROL_STATION(5,"加油站"),
    DEOPT_AND_REFINERY(124,"炼油厂和油库"),
    STATION_AND_DEPOT(245,"加油站和油库"),
    DEPOT_REFINERY_TRANSPORTATION(1234,"炼油厂,油库,运输公司");


    private Integer role;

    private String tips;

    RoleEnum(Integer role, String tips) {
        this.role = role;
        this.tips = tips;
    }}
