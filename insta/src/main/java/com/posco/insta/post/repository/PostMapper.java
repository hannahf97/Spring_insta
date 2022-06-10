package com.posco.insta.post.repository;

import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostDto> getPost();
    List<SelectPostJoinUserDto> getPostByUserId(PostDto postDto);

    Integer deltePostByUserIdAndId(PostDto postDto);

    List<SelectPostJoinUserDto> getOtherPostByUserId(PostDto postDto);

    Integer insertPost(PostDto postDto);

    Integer updateMyPost(PostDto postDto);

    SelectPostJoinUserDto getPostById(PostDto postDto);

    List<SelectPostJoinUserDto> findPostBykey(String key);

    List<SelectPostJoinUserDto> findFollowGetPost(PostDto postDto);
}
