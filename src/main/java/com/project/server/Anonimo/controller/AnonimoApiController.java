package com.project.server.Anonimo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.server.Anonimo.models.Post;
import com.project.server.Anonimo.models.User;
import com.project.server.Anonimo.repository.PostRepository;
import com.project.server.Anonimo.repository.UserRepository;

@RestController
@RequestMapping("/apis")
public class AnonimoApiController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody User user){
        User saved = this.userRepository.save(user);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/signin/{userEmail}/{userPassword}")
    public User signInUser(@PathVariable String userEmail, @PathVariable String userPassword){
        User user = new User();
        List<User> users = this.userRepository.findAll();
        for(int i=0;i<users.size();i++){
            if(userEmail.equals(users.get(i).getUserEmail()) && userPassword.equals(users.get(i).getUserPassword())){
                user=users.get(i);
            }
        }
        return user;
    }

    @GetMapping("/getAllPosts")
    public List<Post> getAllPosts(){
        List<Post> posts = this.postRepository.findAll();
        return posts;
    }

    // @GetMapping("/getPost/{postID}")
    // public Post getPost(@PathVariable String postID){
    //     Post post = this.postRepository.findOne();
    //     return post;
    // }

    @GetMapping("/")
    public String testing(){
        return "This is for testing";
    }
    
}
