package group.uchain.oilsupplychain.dto;

import lombok.Data;

/**
 * @author panghu
 * @Title: ApplyDTO
 * @ProjectName oil-supply-chain
 * @Description: TODO
 * @date 19-4-3 下午7:14
 */
@Data
public class ApplyDTO {


    /**
     * 油品品号
     */
    private String variety;

    /**
     * 数量
     */
    private String count;

    /**
     * 单位
     */
    private String unit;

    /**
     * 发油单位
     */
    private String sender;

    /**
     * 收油单位
     */
    private String receiver;

    /**
     * 配送时间
     */
    private String sendTime;


}
