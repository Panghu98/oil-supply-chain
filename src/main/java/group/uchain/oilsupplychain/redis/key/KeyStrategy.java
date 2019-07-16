package group.uchain.oilsupplychain.redis.key;

/**
 * @author panghu
 * @title: KeyStrategy
 * @projectName demo
 * @date 19-4-22 上午10:15
 */
public interface KeyStrategy {

    /**
     * 获取失效时间
     * @return
     */
    int getExpireSeconds();

    /**
     * 获取前缀
     * @return
     */
    String getPrefix();

}
