package com.qf.controller;

import com.qf.pojo.vo.TV;
import com.qf.pojo.resp.BaseResp;
import com.qf.service.TVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TVController {

    @Autowired
    TVService tvService;

    //查询全部,不分页
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public BaseResp findAll(){
        return tvService.findAll();
    }

    //根据region查询
    @RequestMapping(value = "/findByRegion",method = RequestMethod.GET)
    public BaseResp findByRegion(@RequestParam("region")String region){
        return tvService.findByRegion(region);
    }

    //根据id查询
    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public BaseResp findById(@RequestParam("id")Integer id){
        return tvService.findById(id);
    }

    //新增/修改
    @RequestMapping(value = "/insertOrUpdate",method = RequestMethod.POST)
    public BaseResp insertOrUpdate(@RequestBody TV tv){
        return tvService.insertOrUpdate(tv);
    }

    //根据id删除
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public BaseResp delete(@RequestParam("id") Integer id){
        return tvService.delete(id);
    }

    //查询全部,分页
    @RequestMapping(value = "/findAllByLimit",method = RequestMethod.GET)
    public BaseResp findAllByLimit(@RequestParam("page")Integer page,@RequestParam("size")Integer size){
        return tvService.findAllByLimit(page,size);
    }

}
