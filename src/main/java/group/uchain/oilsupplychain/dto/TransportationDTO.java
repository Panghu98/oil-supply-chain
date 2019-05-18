package group.uchain.oilsupplychain.dto;

import lombok.Data;

/**
 * @author panghu
 * @Title: TransportationDOT
 * @ProjectName oil-supply-chain
 * @Description: TODO
 * @date 19-4-3 下午7:17
 */
@Data
public class TransportationDTO {

    /**
     * 油品品号
     */
    private String variety;

    /**
     * 油品数量
     */
    private String count;


    /**
     * 产品发送者
     */
    private String sender;

    /**
     * 产品接收者
     */
    private String receiver;

    /**
     * 运输公司
     */
    private String company;

    /**
     * 运输设备编号
     */
    private String equipmentNumber;

}
