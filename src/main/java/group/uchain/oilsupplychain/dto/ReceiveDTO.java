package group.uchain.oilsupplychain.dto;

import lombok.Data;

/**
 * @author panghu
 * @Title: ReceiveDTO
 * @ProjectName oil-supply-chain
 * @Description: TODO
 * @date 19-4-3 下午7:15
 */
@Data
public class ReceiveDTO {


    /**
     * 油品状态
     */
    private String oilStatus;

    /**
     * 抽样检测结果
     */
    private String sampleStatus;

    private String certification;

    private String count;

    private String unit;

    private String variety;

    private String sender;

    private String receiverId;


}
