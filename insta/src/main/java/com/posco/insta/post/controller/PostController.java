package com.posco.insta.post.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;
import com.posco.insta.post.service.PostServiceImpl;
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
    public Integer deleteMyPost(@PathVariable String id){
        PostDto postDto = new PostDto();
        postDto.setId(Integer.valueOf(id));
        postDto.setUserId(String.valueOf(securityService.getIdAtToken()));
        return postService.deltePostByUserIdAndId(postDto);
    }
}
