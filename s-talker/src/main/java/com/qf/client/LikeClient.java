package com.qf.client;

import com.qf.pojo.vo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@FeignClient(name = "s-love")
public interface LikeClient {

    @RequestMapping(value = "/findFriend",method = RequestMethod.POST)
    public List<User> findFriend(@RequestBody Map map);


}
