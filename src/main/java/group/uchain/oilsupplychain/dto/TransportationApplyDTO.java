package group.uchain.oilsupplychain.dto;

import lombok.Data;

/**
 * @author panghu
 * @Title: TransportationApplyDOT
 * @ProjectName oil-supply-chain
 * @Description: TODO
 * @date 19-4-3 下午7:16
 */
@Data
public class TransportationApplyDTO {


    /**
     * 油品品号  柴油
     */
    private String variety;

    /**
     * 运输公司
     */
    private String company;

    /**
     * 产品发送者
     */
    private String sender;

    /**
     * 产品接收者
     */
    private String receiver;

    /**
     * 申请运输数量
     */
    private String count;

    /**
     * 配送时间
     */
    String sendTime;


}
