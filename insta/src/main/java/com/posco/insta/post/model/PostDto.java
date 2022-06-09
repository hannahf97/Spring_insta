package com.posco.insta.post.model;

import lombok.Data;

@Data
public class PostDto {
    private Integer id;
    private String userId;
    private String img;
    private String content;

}
