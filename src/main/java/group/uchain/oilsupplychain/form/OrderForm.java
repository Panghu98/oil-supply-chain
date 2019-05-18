package group.uchain.oilsupplychain.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * @author panghu
 */
@Data
public class OrderForm implements Serializable {

    private static final long serialVersionUID = -8469082151024533784L;

    private String id;

    private String batchNumber;

    @JsonFormat(timezone = "GMT+8")
    private String date;

    private String from;

    private String to;

    /**
     * 申油单 1
     * 发油单 2
     * 运输申请单 3
     * 运输单 4
     * 收油单 5
     * 售油单6
     */
    private String type;

    private String status;


}