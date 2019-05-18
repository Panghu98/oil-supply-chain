package group.uchain.oilsupplychain.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author panghu
 * @Title: BatchNumberUtil
 * @ProjectName oil-supply-chain
 * @Description: 生成批次号
 * 前12位为时间轴,后五位为随机数
 * @date 19-3-25 下午12:43
 */
public class BatchNumberUtil {




    private static final int MAXLENGTH = 5;

    /**
     * 随即编码
     */
    private static final int[] CODE = new int[]{7, 9, 6, 2, 8, 1, 3, 0, 5, 4};

    /**
     * 根据1`        //id = id == null ? 1000 : id;id进行加密+加随机数组成固定长度编码
     */
    private static String toCode(Long id) {
        String idStr = id.toString();
        StringBuilder sb = new StringBuilder();
        for (int i = idStr.length() - 1; i >= 0; i--) {
            sb.append(CODE[idStr.charAt(i) - '0']);
        }
    return sb.append(getRandom(MAXLENGTH - idStr.length())).toString();
    }

    /**
     * 生成时间戳
     */
    private static String getDateTime() {
        DateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        return sdf.format(new Date());
    }

    /**
     * 生成固定长度随机码
     *
     * @param len 长度
     */
    private static long getRandom(long len) {
        long min = 1, max = 9;
        for (int i = 1; i < len; i++) {
            min *= 10;
            max *= 10;
        }
        return (((long) (new Random().nextDouble() * (max - min)))) + min;
    }

    /**
     * 生成不带类别标头的编码
     *
     * @param
     */
    public static synchronized String getCode() {
        int bounds = 100;
        int count = 0;
        String result = "";
        try {
            Random random = new Random();
            for (int i = 0; i < bounds; i++) {
                count = random.nextInt(51) + 10;
            }
            result = getDateTime() + toCode((long) count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
