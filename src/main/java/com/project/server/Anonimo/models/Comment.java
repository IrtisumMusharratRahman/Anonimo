package com.project.server.Anonimo.models;

import lombok.Data;

@Data
public class Comment {
    private String commentID;
    private String postID;
    private String commentContent;
    private String commentTime;
}
