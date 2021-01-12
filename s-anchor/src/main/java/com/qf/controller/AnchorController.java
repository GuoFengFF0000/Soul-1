package com.qf.controller;

import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.Anchor;
import com.qf.service.AnchorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anchor")
public class AnchorController {

    @Autowired
    AnchorService anchorService;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public BaseResp findAll(){
        return anchorService.findAll();
    }

    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public BaseResp findById(@RequestParam("id")Integer id){
        return anchorService.findById(id);
    }

    @RequestMapping(value = "/insertOrUpdate",method = RequestMethod.POST)
    public BaseResp insertOrUpdate(@RequestBody Anchor anchor){
        return anchorService.insertOrUpdate(anchor);
    }
}
