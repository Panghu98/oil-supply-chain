package group.uchain.oilsupplychain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author panghu
 * @Title: SendDTO
 * @ProjectName oil-supply-chain
 * @Description: TODO
 * @date 19-4-3 下午7:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendDTO {


    /**
     * 油品种类
     */
    private String variety;

    /**
     * 数量
     */
    private String count;

    /**
     * 抽样检测状态
     */
    private String sampleStatus;

    /**
     * 产品合格证书
     */
    private String certification;

    /**
     * 油品状态
     */
    private Byte  oilStatus;

    /**
     *
     */
    private String sender;


    private String receiver;


}
