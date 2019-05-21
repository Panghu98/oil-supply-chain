package group.uchain.oilsupplychain.service.impl;

import group.uchain.oilsupplychain.dto.*;
import group.uchain.oilsupplychain.dto2.*;
import group.uchain.oilsupplychain.mapper.UserFormMapper;
import group.uchain.oilsupplychain.utils.BatchNumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author panghu
 * @title: TypeChangeService
 * @projectName oil-supply-chain
 * @date 19-4-6 下午3:54
 */
@Slf4j
@Service
public class TypeChangeService {

    @Autowired
    private UserFormMapper userFormMapper;

    @Autowired
    private BatchNumberService batchNumberService;



    public ChainApplyDTO getChainApplyDTO(ApplyDTO applyDTO) {
        ChainApplyDTO chainApplyDTO= new ChainApplyDTO();
        chainApplyDTO.setBatchNumber(BatchNumberUtil.getCode());
        chainApplyDTO.setReceiver(applyDTO.getReceiver());
        chainApplyDTO.setVariety(applyDTO.getVariety());
        chainApplyDTO.setSendTime(applyDTO.getSendTime());
        chainApplyDTO.setSender(applyDTO.getSender());
        chainApplyDTO.setCount(countChange(applyDTO.getCount(),applyDTO.getUnit()));
        System.out.println(chainApplyDTO);
        return chainApplyDTO;
    }

    public ChainSellDTO getChainSellDTO(SellDTO sellDTO,String sellerID) {
        ChainSellDTO chainSellDTO = new ChainSellDTO();
        chainSellDTO.setCapacity(sellDTO.getCapacity());
        chainSellDTO.setMoney(sellDTO.getMoney());
        chainSellDTO.setPay(sellDTO.getPay());
        chainSellDTO.setSellerId(sellerID);
        chainSellDTO.setVariety(sellDTO.getVariety());
        return chainSellDTO;
    }

    public ChainReceiveDTO getChainReceiveDTO(ReceiveDTO receiveDTO,String id){
       ChainReceiveDTO chainReceiveDTO = new ChainReceiveDTO();
       chainReceiveDTO.setBatchNumber(batchNumberService.getBatchNumberById(id));
       chainReceiveDTO.setCertification(receiveDTO.getCertification());
       chainReceiveDTO.setVariety(receiveDTO.getVariety());
       chainReceiveDTO.setCount(countChange(receiveDTO.getCount(),receiveDTO.getUnit()));
       chainReceiveDTO.setSampleStatus(receiveDTO.getSampleStatus());
       chainReceiveDTO.setSenderID(receiveDTO.getSender());
        return chainReceiveDTO;
    }

    public ChainTransportationApplyDTO getChainTransportationApplyDTO(TransportationApplyDTO transportationApplyDTO,String id){
        ChainTransportationApplyDTO chainTransportationApplyDTO = new ChainTransportationApplyDTO();
        chainTransportationApplyDTO.setBatchNumber(batchNumberService.getBatchNumberById(id));
        chainTransportationApplyDTO.setCompany(transportationApplyDTO.getCompany());
        chainTransportationApplyDTO.setCount(countChange(transportationApplyDTO.getCount(),transportationApplyDTO.getUnit()));
        chainTransportationApplyDTO.setReceiver(transportationApplyDTO.getReceiver());
        chainTransportationApplyDTO.setSender(transportationApplyDTO.getSender());
        chainTransportationApplyDTO.setSendTime(transportationApplyDTO.getSendTime());
        chainTransportationApplyDTO.setVariety(transportationApplyDTO.getVariety());
        return chainTransportationApplyDTO;
    }

    public ChainTransportationDTO getChainTransportationDTO(TransportationDTO transportationDTO,String id){
        ChainTransportationDTO chainTransportationDTO = new ChainTransportationDTO();
        chainTransportationDTO.setBatchNumber(batchNumberService.getBatchNumberById(id));
        chainTransportationDTO.setCompany(transportationDTO.getCompany());
        chainTransportationDTO.setCount(countChange(transportationDTO.getCount(),transportationDTO.getUnit()));
        chainTransportationDTO.setEquipmentNumber(transportationDTO.getEquipmentNumber());
        chainTransportationDTO.setReceiver(transportationDTO.getReceiver());
        chainTransportationDTO.setSender(transportationDTO.getSender());
        chainTransportationDTO.setVariety(transportationDTO.getVariety());
        return chainTransportationDTO;
    }

    public ChainSendDTO getChainSendDTO(SendDTO sendDTO,String id){
        ChainSendDTO chainSendDTO = new ChainSendDTO();
        chainSendDTO.setBatchNumber(batchNumberService.getBatchNumberById(id));
        chainSendDTO.setCertification(sendDTO.getCertification());
        chainSendDTO.setCount(countChange(sendDTO.getCount(),sendDTO.getUnit()));
        chainSendDTO.setOilStatus(sendDTO.getOilStatus());
        chainSendDTO.setReceiver(sendDTO.getReceiver());
        chainSendDTO.setSampleStatus(sendDTO.getSampleStatus());
        chainSendDTO.setSender(sendDTO.getSender());
        chainSendDTO.setVariety(sendDTO.getVariety());
        return chainSendDTO;
    }

    public String getNameById(Long id){
        return userFormMapper.getUsernameById(id);
    }

    public String getNormalName(String name){
        switch (name){
            case "diesel":
                return "柴油";
            case "92":
                return "92号汽油";
            case "95":
                return "95号汽油";
            case "98":
                return "98号汽油";
            default:
                return "未知类型";
        }
    }

    public static String countChange(String count,String unit){
        Double finalCount = null;
        if (unit.equals("t")){
            finalCount = Double.valueOf(count);
        }else if(unit.equals("L")){
            finalCount = Double.valueOf(count)/1000;
        }
        return String.valueOf(finalCount);
    }

}
