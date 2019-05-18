package group.uchain.oilsupplychain.utils;

/**
 * @author panghu
 * @title: RoleChangeUtil
 * @projectName demo
 * @date 19-5-3 下午12:46
 * @description 将前端传入的role转化为区块链的role
 */
public class RoleChangeUtil {

    /**
     * 1 炼油厂  ，2 省级油库，3运输公司，4地级市油库，5加油站
     * @param role
     * @return
     */

    public static String roleChange(Integer role){
        switch (role){
            case 1:
                return "Refinery_";
            case 2:
            case 4:
                return "OilDepoter_";
            case 5:
                return "GasStation_";
            default:
                break;
        }

        return null;
    }

}
