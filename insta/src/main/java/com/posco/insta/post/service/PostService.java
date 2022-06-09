package com.posco.insta.post.service;

import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;

import java.util.List;

public interface PostService {
    List<PostDto> getPosts();
    List<SelectPostJoinUserDto> getPostByUserId(PostDto postDto);

    Integer deltePostByUserIdAndId(PostDto postDto);
}
