package group.uchain.oilsupplychain.dto2;

import com.fasterxml.jackson.annotation.JsonFormat;
import group.uchain.oilsupplychain.dto.ApplyDTO;
import group.uchain.oilsupplychain.service.impl.TypeChangeService;
import group.uchain.oilsupplychain.utils.BatchNumberUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author panghu
 * @Title: ApplyDTO
 * @ProjectName oil-supply-chain
 * @Description: TODO
 * @date 19-4-3 下午7:14
 */
@Data
@NoArgsConstructor
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

}
