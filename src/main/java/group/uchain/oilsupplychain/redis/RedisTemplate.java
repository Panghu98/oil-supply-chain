package group.uchain.oilsupplychain.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;

/**
 * @author panghu
 * @Title: RedisTemplate
 * @ProjectName demo
 * @Description: TODO
 * @date 19-3-5 上午11:01
 */
@Slf4j
public class RedisTemplate extends org.springframework.data.redis.core.RedisTemplate {

    public static ThreadLocal<Integer> indexdb = ThreadLocal.withInitial(() -> 0);

    @Override
    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        try {
            Integer dbIndex = indexdb.get();
            //如果设置了dbIndex
            if (dbIndex != null) {
                if (connection instanceof JedisConnection) {
                    if (((JedisConnection) connection).getNativeConnection().getDB().intValue() != dbIndex) {
                        connection.select(dbIndex);
                        log.info("选择数据库{}",dbIndex);
                    }
                } else {
                    connection.select(dbIndex);
                    log.info("选择数据库{}",dbIndex);
                }
            } else {
                connection.select(0);
                log.info("选择了数据库0");
            }
        } finally {
            indexdb.remove();
        }
        return super.preProcessConnection(connection, existingConnection);
    }

}
