package com.posco.insta.follow.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.follow.model.FollowDto;
import com.posco.insta.follow.service.FollowServiceImpl;
import com.posco.insta.post.model.SelectPostJoinUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
@TokenRequired
public class FollowController {
    @Autowired
    FollowDto followDto;
    @Autowired
    SecurityService securityService;

    @Autowired
    FollowServiceImpl followService;

    @GetMapping("/my/follower")
    public List<FollowDto> getMyFollower(){
        followDto.setFollowing(securityService.getIdAtToken());
        return followService.getFollower(followDto);
    }

    @GetMapping("/follower/{id}")
    public List<FollowDto> getFollowerById(@PathVariable String id){
        followDto.setFollowing(Integer.valueOf(id));
        return followService.getFollower(followDto);
    }

    @GetMapping("/following/{id}")
    public List<FollowDto> getFollowingById(@PathVariable String id){
        followDto.setFollower(Integer.valueOf(id));
        return followService.getFollowing(followDto);
    }

    @GetMapping("/my/following")
    public List<FollowDto> getMyFollowing(){
        followDto.setFollower(securityService.getIdAtToken());
        return followService.getFollowing(followDto);
    }
    @PostMapping("/{id}")
    public Integer postFollow(@PathVariable String id){
        followDto.setFollowing(securityService.getIdAtToken());
        followDto.setFollower(Integer.valueOf(id));
        return followService.insertFollow(followDto);
    }

    @DeleteMapping("/{id}")
    public Integer deleteFollow(@PathVariable String id){
        followDto.setFollowing(securityService.getIdAtToken());
        followDto.setFollower(Integer.valueOf(id));
        return followService.deleteFollow(followDto);
    }





}
