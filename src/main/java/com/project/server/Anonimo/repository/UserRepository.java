package com.project.server.Anonimo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.server.Anonimo.models.User;

public interface UserRepository extends MongoRepository<User,String> { }
