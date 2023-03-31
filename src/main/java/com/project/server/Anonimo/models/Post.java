package com.project.server.Anonimo.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "posts")
public class Post {
    @Id
    private String postID;
    private String userID;
    private String postContent;
    private String postTag;
    private String postTime;
    private List<Comment> comments;
}
