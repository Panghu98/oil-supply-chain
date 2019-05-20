package group.uchain.oilsupplychain.dto2;

import group.uchain.oilsupplychain.dto.SendDTO;
import group.uchain.oilsupplychain.service.impl.TypeChangeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author panghu
 * @Title: SendDTO
 * @ProjectName oil-supply-chain
 * @Description: TODO
 * @date 19-4-3 下午7:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChainSendDTO {

    private String batchNumber;
    /**
     * 油品种类
     */
    private String variety;

    /**
     * 数量
     */
    private String count;

    /**
     * 抽样检测状态
     */
    private String sampleStatus;

    /**
     * 产品合格证书
     */
    private String certification;

    /**
     * 油品状态
     */
    private Byte  oilStatus;

    private String sender;

    private String receiver;

}
