package group.uchain.oilsupplychain.dto2;

import lombok.Data;

/**
 * @author panghu
 * @title: ChainTransUserDTO
 * @projectName oil-supply-chain
 * @date 19-4-6 上午9:39
 */
@Data
public class ChainTransUserDTO {

    private Integer id;

    private String railwayTrafficVolume;

    private String pipeTrafficVolume;

    private String seaTrafficVolume;

}
