package group.uchain.oilsupplychain.dto2;

import group.uchain.oilsupplychain.dto.ReceiveDTO;
import group.uchain.oilsupplychain.service.UserService;
import group.uchain.oilsupplychain.service.impl.TypeChangeService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author panghu
 * @Title: ReceiveDTO
 * @ProjectName oil-supply-chain
 * @Description: TODO
 * @date 19-4-3 下午7:15
 */
@NoArgsConstructor
@Data
public class ChainReceiveDTO {


    @Autowired
    private UserService userService;

    @Autowired
    private TypeChangeService typeChangeService;

    private String count;

    /**
     * 抽样检测结果
     */
    private String sampleStatus;

    private String certification;

    private String variety;

    private String senderID;

    private String batchNumber;

}
