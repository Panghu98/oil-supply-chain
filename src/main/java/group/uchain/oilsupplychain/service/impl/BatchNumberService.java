package group.uchain.oilsupplychain.service.impl;

import group.uchain.oilsupplychain.mapper.OrderFormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author panghu
 * @title: BatchNumberService
 * @projectName oil-supply-chain
 * @date 19-4-7 下午4:20
 * @Description: 通过ID号获取相应的批次号
 */
@Service
public class BatchNumberService {

    private OrderFormMapper orderFormMapper;

    @Autowired
    public BatchNumberService(OrderFormMapper orderFormMapper) {
        this.orderFormMapper = orderFormMapper;
    }

    public String getBatchNumberById(String id) {
        return orderFormMapper.getBatchNumberById(id);
    }


}