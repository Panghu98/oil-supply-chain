package group.uchain.oilsupplychain.dto;

import lombok.Data;

/**
 * @author panghu
 * @title: SellDto
 * @projectName oil-supply-chain
 * @date 19-4-5 上午8:39
 */
@Data
public class SellDTO {

    /**
     * 油品品号
     */
    private String variety;

    /**
     * 交易升数
     */
    private Double capacity;

    /**
     * 交易金额
     */
    private Double money;

    /**
     * 单价
     */
    private Double univalent;

    /**
     * 支付方式
     */
    private String pay;



}
