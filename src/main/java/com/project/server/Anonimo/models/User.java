package com.project.server.Anonimo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String userID;
    private String userName;
    private String userEmail;
    private String userPassword;
}
