package group.uchain.oilsupplychain.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @author panghu
 * @Title: IDUtil
 * @ProjectName oil-supply-chain
 * @date 19-3-29 上午8:18
 */
public class IDUtil {

    public static Long idGenerator(){
        return getRandom() +getTime();
    }

    /**
     * 生成固定长度随机码
     *
     */
    public static long getRandom() {
        long min = 1, max = 9;
        for (int i = 1; i < (long) 5; i++) {
            min *= 9;
            max *= 9;
        }
        return (((long) (new Random().nextDouble() * (max - min)))) + min;
    }

    private static Integer getTime(){
        long date = System.currentTimeMillis();
        return Integer.valueOf(String.valueOf(date).substring(4));
    }

    /**
     * 生成长UUID
     */
    private static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(randomUUID());
    }



}