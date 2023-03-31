package com.project.server.Anonimo.models;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Comment {
    @Id
    private String commentID;
    private String postID;
    private String commentContent;
    private String commentTime;
}
