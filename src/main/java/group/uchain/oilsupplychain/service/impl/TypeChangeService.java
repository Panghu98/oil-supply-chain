package group.uchain.oilsupplychain.service.impl;

import group.uchain.oilsupplychain.dto.*;
import group.uchain.oilsupplychain.dto2.*;
import group.uchain.oilsupplychain.mapper.UserFormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author panghu
 * @title: TypeChangeService
 * @projectName oil-supply-chain
 * @date 19-4-6 下午3:54
 */
@Service
public class TypeChangeService {

    @Autowired
    private UserFormMapper userFormMapper;

    @Autowired
    private BatchNumberService batchNumberService;

    public ChainApplyDTO getChainApplyDTO(ApplyDTO applyDTO) {
        return new ChainApplyDTO(applyDTO);
    }

    public ChainSellDTO getChainSellDTO(SellDTO sellDTO,String sellerID) {
        return new ChainSellDTO(sellDTO,sellerID);
    }

    public ChainReceiveDTO getChainReceiveDTO(ReceiveDTO receiveDTO,String id){
        return new ChainReceiveDTO(receiveDTO,batchNumberService.getBatchNumberById(id));
    }

    public ChainTransportationApplyDTO getChainTransportationApplyDTO(TransportationApplyDTO transportationApplyDTO,String id){
        return new ChainTransportationApplyDTO(transportationApplyDTO,batchNumberService.getBatchNumberById(id));
    }

    public ChainTransportationDTO getChainTransportationDTO(TransportationDTO transportationDTO,String id){
        return new ChainTransportationDTO(transportationDTO,batchNumberService.getBatchNumberById(id));
    }

    public ChainSendDTO getChainSendDTO(SendDTO sendDTO,String id){
        return new ChainSendDTO(sendDTO,batchNumberService.getBatchNumberById(id));
    }

    public String getNameById(Long id){
        return userFormMapper.getUsernameById(id);
    }

    public String getNormalName(String name){
        switch (name){
            case "diesel":
                return "柴油";
            case "92":
                return "#92";
            case "95":
                return "#95";
            case "98":
                return "#98";
            default:
                return "未知类型";
        }
    }
}
