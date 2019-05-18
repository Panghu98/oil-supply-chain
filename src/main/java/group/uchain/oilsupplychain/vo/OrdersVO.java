package group.uchain.oilsupplychain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author panghu
 * @title: OrdersVO
 * @projectName oil-supply-chain
 * @date 19-4-5 下午3:13
 */
@Data
public class OrdersVO implements Serializable {

    private static final long serialVersionUID = -8090707222074181693L;

    private String id;

    private String batchNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-5 ")
    private Date date;

    private String from;

    private String to;

}
