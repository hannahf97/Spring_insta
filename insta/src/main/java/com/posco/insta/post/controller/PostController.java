package com.posco.insta.post.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;
import com.posco.insta.post.service.PostServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
@TokenRequired
public class PostController {
    @Autowired
    PostServiceImpl postService;

    @Autowired
    SecurityService securityService;

    @GetMapping("/")
    public List<PostDto> getPost(){
        return postService.getPosts();
    }

    @PostMapping("/my")
    @TokenRequired
    public List<SelectPostJoinUserDto> getPostById(){
        PostDto postDto = new PostDto();
        postDto.setUserId(String.valueOf(securityService.getIdAtToken()));
        return postService.getPostByUserId(postDto);
    }

    @DeleteMapping("/{id}")
    @TokenRequired
    public Integer deleteMyPost(@PathVariable String id){
        PostDto postDto = new PostDto();
        postDto.setId(Integer.valueOf(id));
        postDto.setUserId(String.valueOf(securityService.getIdAtToken()));
        return postService.deltePostByUserIdAndId(postDto);
    }

    @GetMapping("/other")
    @TokenRequired
    public List<SelectPostJoinUserDto> getOtherPostById(){
        PostDto postDto = new PostDto();
        postDto.setUserId(String.valueOf(securityService.getIdAtToken()));
        return postService.getOtherPostByUserId(postDto);
    }

    @PutMapping("/{id}")
    public Integer updateMyPost(@RequestBody PostDto postDto, @PathVariable String id){
        postDto.setUserId(String.valueOf(securityService.getIdAtToken()));
        postDto.setId(Integer.valueOf(id));
        return postService.updatePost(postDto);
    }

    @PostMapping("/")
    public Integer postPost(@RequestBody PostDto postDto){
        postDto.setUserId(String.valueOf(securityService.getIdAtToken()));
        return postService.insertPost(postDto);
    }

    @GetMapping("/{id}")
    public SelectPostJoinUserDto getPostById(@PathVariable String id){
        PostDto postDto = new PostDto();
        postDto.setId(Integer.valueOf(id));
        return postService.getPostOneById(postDto);
    }

    @GetMapping("/key/{key}")
    public List<SelectPostJoinUserDto> findPostsLikeKey(@PathVariable String key){

        return postService.findPostsLikeKey(key);
    }

    @GetMapping("/following")
    @Operation(description = "내가 팔로윙으로 있고 follwer인 애들의 getPost")
    public List<SelectPostJoinUserDto> getPostsByMyFollowing(PostDto postDto){
        postDto.setUserId(String.valueOf(securityService.getIdAtToken()));
        return postService.findFollowGetPost(postDto);
    }
}
