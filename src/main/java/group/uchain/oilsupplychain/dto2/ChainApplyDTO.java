package group.uchain.oilsupplychain.dto2;

import com.fasterxml.jackson.annotation.JsonFormat;
import group.uchain.oilsupplychain.dto.ApplyDTO;
import group.uchain.oilsupplychain.utils.BatchNumberUtil;
import lombok.Data;

/**
 * @author panghu
 * @Title: ApplyDTO
 * @ProjectName oil-supply-chain
 * @Description: TODO
 * @date 19-4-3 下午7:14
 */
@Data
public class ChainApplyDTO {

    /**
     * 油品品号
     */
    private String variety;

    /**
     * 数量
     */
    private String count;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String sendTime;

    /**
     * 批次号
     */
    private String batchNumber;

    public ChainApplyDTO(ApplyDTO applyDTO) {
        this.variety = applyDTO.getVariety();
        this.batchNumber = BatchNumberUtil.getCode();
        this.count = applyDTO.getCount();
        this.receiver = applyDTO.getReceiver();
        this.sendTime = applyDTO.getSendTime();
        this.sender = applyDTO.getSender();
    }
}
