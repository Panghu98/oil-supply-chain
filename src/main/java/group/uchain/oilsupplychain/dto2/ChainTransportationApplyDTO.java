package group.uchain.oilsupplychain.dto2;

import group.uchain.oilsupplychain.dto.TransportationApplyDTO;
import group.uchain.oilsupplychain.service.impl.TypeChangeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author panghu
 * @Title: TransportationApplyDOT
 * @ProjectName oil-supply-chain
 * @Description: TODO
 * @date 19-4-3 下午7:16
 */
@Data
public class ChainTransportationApplyDTO {


    @Autowired
    private TypeChangeService typeChangeService;
    /**
     * 油品品号
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
     * 批次号
     */
    private String batchNumber;

    /**
     * 配送时间
     */
    private String sendTime;

    public ChainTransportationApplyDTO(TransportationApplyDTO transportationApplyDTO,String batchNumber) {
        this.variety = transportationApplyDTO.getVariety();
        this.company = transportationApplyDTO.getCompany();
        this.sender = transportationApplyDTO.getSender();
        this.receiver = transportationApplyDTO.getReceiver();
        this.count = typeChangeService.countChange(transportationApplyDTO.getCount(),transportationApplyDTO.getUnit());
        this.batchNumber = batchNumber;
        this.sendTime = transportationApplyDTO.getSendTime();
    }
}
