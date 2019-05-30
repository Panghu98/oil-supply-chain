package group.uchain.oilsupplychain.redis.key;

/**
 * @author panghu
 * @title: UserKey
 * @projectName test
 * @date 19-5-29 下午7:02
 */
public class UserKey extends BasePrefix {
    public UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public UserKey(String id) {
        super(-1,id);
    }
}
