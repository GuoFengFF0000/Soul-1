package com.qf;

import com.qf.pojo.resp.BaseResp;
import com.qf.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Testgxfsgffg {

    @Autowired
    UserService userService;

    @Test
    public void ts(){
        Map<String, Object> map = new HashMap<>();

        map.put("uid",1);
        map.put("aid",1);
        map.put("gid",10);
        map.put("num",2);

        BaseResp gift = userService.gift(map);
        System.out.println(gift.getMessage());
    }
}
