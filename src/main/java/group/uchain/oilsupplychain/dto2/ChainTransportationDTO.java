package group.uchain.oilsupplychain.dto2;

import group.uchain.oilsupplychain.dto.TransportationDTO;
import group.uchain.oilsupplychain.service.impl.TypeChangeService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author panghu
 * @Title: TransportationDOT
 * @ProjectName oil-supply-chain
 * @Description: TODO
 * @date 19-4-3 下午7:17
 */
@NoArgsConstructor
@Data
public class ChainTransportationDTO {

    @Autowired
    private TypeChangeService typeChangeService;
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
     * 统一编号
     */
    private String batchNumber;

    /**
     * 设备编号
     */
    private String equipmentNumber;



}
