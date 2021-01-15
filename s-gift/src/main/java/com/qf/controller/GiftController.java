package com.qf.controller;

import com.qf.pojo.resp.BaseResp;
import com.qf.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gift")
public class GiftController {

    @Autowired
    GiftService giftService;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public BaseResp findAll(){
        return giftService.findAll();
    }

    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public BaseResp findById(@RequestParam("id")Integer id){
        return giftService.findById(id);
    }
}
