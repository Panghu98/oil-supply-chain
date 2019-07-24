package group.uchain.oilsupplychain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @author panghu
 * @title: OrderSVO2
 * @projectName oil-supply-chain
 * @date 19-4-7 下午3:58
 */
@Data
public class ApplyOrdersVO {

    private String id;

    private String batchNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date date;

    private String from;

    private String to;

    /**
     * 订单的审核状态
     * 1.未审核 2.审核不通过 3.审核通过
     */
    @JsonIgnore
    private String status;

}
