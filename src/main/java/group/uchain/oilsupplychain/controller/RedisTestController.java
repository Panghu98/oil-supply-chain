package group.uchain.oilsupplychain.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import group.uchain.oilsupplychain.redis.RedisModelMap;
import group.uchain.oilsupplychain.redis.RedisUtil;
import group.uchain.oilsupplychain.redis.StateParameter;
import group.uchain.oilsupplychain.redis.key.ModelKey;
import group.uchain.oilsupplychain.vo.ViewUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author panghu
 * @Title: RedisTestController
 * @ProjectName demo
 * @Description: TODO
 * @date 19-3-5 上午11:59
 */
@Slf4j
@Controller
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/test")
public class RedisTestController {

    @Autowired
    RedisUtil redisUtil;


    /**
     * @auther: zhangyingqi
     * @date: 17:26 2018/8/28
     * @param: []
     * @return: org.springframework.ui.ModelMap
     * @Description: 测试redis存储&读取
     */
    @RequestMapping(value = "/test")
    @ResponseBody
    public Object test(String key) {
        RedisModelMap redisModelMap = new RedisModelMap();
        try {
            String value = redisUtil.get(key).toString();
            log.info("redisValue=" + value);
            log.info("读取redis成功");
            JSONArray jsonArray = JSON.parseArray(value);
            return jsonArray;
        } catch (Exception e) {
            log.error(e.getMessage());
            return redisModelMap.getModelMap(StateParameter.FAULT, null, "操作失败");
        }
    }

    @RequestMapping(value = "/set")
    @ResponseBody
    public Object set(String key) {
        Random random = new Random();
        redisUtil.lSet(key,new ViewUser(random.nextInt(1000),"油库厂","2"));
        log.info("插入成功");
        long length = redisUtil.lGetListSize(key)+1;
        log.error(String.valueOf(length));
        //不同同时进行，这个时候的list长度无效
        return redisUtil.lGet(key,0,length);
    }




    @RequestMapping(value = "/get")
    @ResponseBody
    public Object get(String key) {
        Object result = redisUtil.lGet(key,0,redisUtil.lGetListSize(key));
        return result;
    }

}
