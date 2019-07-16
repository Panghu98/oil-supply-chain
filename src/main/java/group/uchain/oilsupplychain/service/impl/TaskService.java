package group.uchain.oilsupplychain.service.impl;

import group.uchain.oilsupplychain.mapper.UserFormMapper;
import group.uchain.oilsupplychain.utils.FabricMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


/**
 * @author panghu
 * @title: TaskService
 * @projectName test
 * @date 19-5-28 上午10:36
 */
@Service
public class TaskService {

    private UserFormMapper userFormMapper;

    @Autowired
    public TaskService(UserFormMapper userFormMapper) {
        this.userFormMapper = userFormMapper;
    }

    /**
     * 默认单位是毫秒
     */
    @Scheduled(cron = "0 0 0 1/1 * ? ")
    public void addOil(){
        System.out.println("start to add oil for refinery.....");
        List<String> refineries = userFormMapper.getRefinery();
        Random random = new Random();
        for (String id:refineries
             ) {
            int volume = random.nextInt(2000)+1000;
            System.out.println("选择炼油厂ID:"+id+"   "+","+"增加的数量"+volume+"t");
            FabricMethod.addOilReserve(id,volume);
        }

    }

}
