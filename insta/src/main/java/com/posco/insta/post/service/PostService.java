package com.posco.insta.post.service;

import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;

import java.util.List;

public interface PostService {
    List<PostDto> getPosts();
    List<SelectPostJoinUserDto> getPostByUserId(PostDto postDto);

    Integer deltePostByUserIdAndId(PostDto postDto);

    List<SelectPostJoinUserDto> getOtherPostByUserId(PostDto postDto);

    Integer insertPost(PostDto postDto);

    Integer updatePost(PostDto postDto);

    SelectPostJoinUserDto getPostOneById(PostDto postDto);

    List<SelectPostJoinUserDto> findPostsLikeKey(String key);

    List<SelectPostJoinUserDto> findFollowGetPost(PostDto postDto);
}
