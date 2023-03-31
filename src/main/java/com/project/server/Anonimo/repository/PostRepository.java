package com.project.server.Anonimo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.server.Anonimo.models.Post;

public interface PostRepository extends MongoRepository<Post,String> { }
