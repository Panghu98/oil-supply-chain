package group.uchain.oilsupplychain.utils;

/**
 * @author panghu
 * @title: StatusUtil
 * @projectName demo
 * @date 19-5-12 上午10:28
 */
public class StatusUtil {
    public static String getStatus(String status){
        switch (status){
            case "Accept":
                return "3";
            case "Reject":
                return "2";
            default:
                return "1";
        }
    }
}
