package com.qf;

import com.qf.pojo.resp.BaseResp;
import com.qf.service.UserService;
import com.qf.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Testgxfsgffg {

    @Autowired
    UserService userService;

    @Autowired
    RedisUtils redisUtils;

    @Test
    public void ts(){
        Map<String, Object> map = new HashMap<>();

        map.put("uid",3);
        map.put("aid",1);
        map.put("gid",10);
        map.put("num",2);

        BaseResp gift = userService.gift(map);
        System.out.println(gift.getMessage());
    }

    @Test
    public void dd(){
        Set strings = redisUtils.ZRevRangeWithScores(String.valueOf(1), 0, -1);
        for (Object string : strings) {
            ZSetOperations.TypedTuple string1 = (ZSetOperations.TypedTuple) string;
            System.out.println(string1.getValue()+"---"+string1.getScore());
        }
    }
}
