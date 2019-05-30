package group.uchain.oilsupplychain.redis;

/**
 * @author panghu
 * @Title: ModelMap
 * @ProjectName demo
 * @Description: TODO
 * @date 19-3-5 下午1:21
 */
public class RedisModelMap {

    public org.springframework.ui.ModelMap getModelMap(String status, Object data, String msg) {
        org.springframework.ui.ModelMap modelMap = new org.springframework.ui.ModelMap();
        modelMap.put("status", status);
        modelMap.put("data", data);
        modelMap.put("msg", msg);
        return modelMap;

    }


}
