package com.qf.controller;

import com.qf.pojo.vo.CircleFriends;
import com.qf.pojo.resp.BaseResp;
import com.qf.pojo.vo.Follow;
import com.qf.pojo.vo.Remark;
import com.qf.service.CircleFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@RequestMapping("/circleFriend")
public class CircleFriendController {

    @Autowired
    CircleFriendService circleFriendService;

    /**
     * 调用user服务获取用户信息
     */
    @RequestMapping(value = "/findUserById",method = RequestMethod.POST)
    public BaseResp findUserById( HttpServletRequest request){
        return circleFriendService.findById(request);
    }

    /**
     * 发布朋友圈
     */
    @RequestMapping("/insertComment")
    public BaseResp insertComment(@RequestBody CircleFriends circleFriends){

        return circleFriendService.insertComment(circleFriends);

    }
    /**
     * 发表评论
     */
    @RequestMapping("/insertRemark")
    public BaseResp insertRemark(@RequestBody Remark remark,HttpServletRequest request){
        return circleFriendService.insertRemark(remark,request);
    }
    /**
     * 根据评论id查看所有发表的评论
     */
    @RequestMapping("/findAllRemarkByCfId")
    public BaseResp findAllRemark(@RequestParam("cfId")Integer cfId){
        return circleFriendService.findAllRemarkByCfId(cfId);
    }
    /**
     *
     * 根据时间查看
     */
    @RequestMapping("/circleFriendFindAllByNew")
    public BaseResp circleFriendFindAllByNew(){

        return circleFriendService.circleFriendFindAllByNew();
    }
    /**
     *
     * 根据点赞数量查看
     */
    @RequestMapping("/circleFriendFindAllByNewByZanNums")
    public BaseResp circleFriendFindAllByNewByZanNums(){
        return circleFriendService.circleFriendFindAllByNewByZanNums();
    }
    /**
     * 查看关注的人的朋友圈
     */
    @RequestMapping("/findFollowFriends")
    public BaseResp findFollowFriends(@RequestParam("id") Integer id){
        return circleFriendService.findFollowFriends(id);
    }

    /**
     * 点赞
     */
    @RequestMapping("/addZanNums")
    public BaseResp addZanNums(@RequestParam("cfId") Integer cfId,HttpServletRequest request){
        return circleFriendService.addZanNums(cfId,request);
    }
    /**
     * 关注
     */
    @RequestMapping("/follow")
    public BaseResp follow(@RequestBody Follow follow){
        return circleFriendService.addFollow(follow);
    }
    /**
     * 显示关注人数
     */
    @RequestMapping("/findFollowByUid")
    public BaseResp findFollowByUid(@RequestParam("uid") Integer uid){
        return circleFriendService.findFollowByUid(uid);
    }
    /**
     * 显示粉丝人数
     */
    @RequestMapping("/findFollowByFollowUser")
    public BaseResp findFollowByUid(@RequestParam("followUser") String followUser){
        return circleFriendService.findFollowByFollowUser(followUser);
    }
    /**
     * 后台crud
     */
    @RequestMapping("/adminFriendAdd")
    public BaseResp adminFriendAdd(@RequestBody CircleFriends circleFriends){
        return circleFriendService.adminFriendAdd(circleFriends);
    }

    @RequestMapping("/adminFriendDel")
    public BaseResp adminFriendDel(@RequestBody Map map){
        return circleFriendService.adminFriendDel(Integer.valueOf(map.get("cfId").toString()));
    }

    @RequestMapping("/adminFriendUpdate")
    public BaseResp adminFriendUpdate(@RequestBody CircleFriends circleFriends){
        return circleFriendService.adminFriendUpdate(circleFriends);
    }
    @RequestMapping("/adminFriendFindAll")
    public BaseResp adminFriendFindAll(){
        return circleFriendService.adminFriendFindAll();
    }

    @RequestMapping("/adminFriendFindByCfId")
    public BaseResp adminFriendFindByCfId(@RequestBody Map map){
        return circleFriendService.adminFriendFindByCfId(Integer.valueOf(map.get("cfId").toString()));
    }
}
