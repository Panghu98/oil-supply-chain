package group.uchain.oilsupplychain.redis.key;

/**
 * @author panghu
 */
public abstract class BasePrefix implements KeyStrategy {

    private int expireSeconds;
    private String prefix;


    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int getExpireSeconds() {
        return this.expireSeconds;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }
}
