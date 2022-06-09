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
}
