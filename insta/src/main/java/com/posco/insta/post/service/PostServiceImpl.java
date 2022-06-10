package com.posco.insta.post.service;

import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectPostJoinUserDto;
import com.posco.insta.post.repository.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostMapper postMapper;
    @Override
    public List<PostDto> getPosts() {
        return postMapper.getPost();
    }

    @Override
    public List<SelectPostJoinUserDto> getPostByUserId(PostDto postDto){
        return postMapper.getPostByUserId(postDto);
    }

    @Override
    public Integer deltePostByUserIdAndId(PostDto postDto){
        return postMapper.deltePostByUserIdAndId(postDto);
    }

    @Override
    public List<SelectPostJoinUserDto> getOtherPostByUserId(PostDto postDto) {
        return postMapper.getOtherPostByUserId(postDto);
    }

    @Override
    public Integer insertPost(PostDto postDto) {
        return postMapper.insertPost(postDto);
    }

    @Override
    public Integer updatePost(PostDto postDto) {
        return postMapper.updateMyPost(postDto);
    }

    @Override
    public SelectPostJoinUserDto getPostOneById(PostDto postDto) {
        return postMapper.getPostById(postDto);
    }

    @Override
    public List<SelectPostJoinUserDto> findPostsLikeKey(String key) {
        return postMapper.findPostBykey(key);
    }

    @Override
    public List<SelectPostJoinUserDto> findFollowGetPost(PostDto postDto) {
        return postMapper.findFollowGetPost(postDto);
    }


}
