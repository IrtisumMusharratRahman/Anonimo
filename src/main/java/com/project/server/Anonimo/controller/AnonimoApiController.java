package com.project.server.Anonimo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.server.Anonimo.models.Comment;
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
    public String signUpUser(@RequestBody User user){
        String status;
        User saved = this.userRepository.save(user);
        if(saved!=null){
            status="valid";
        }else{
            status="invalid";
        }
        return status;
    }

    @GetMapping("/signin/{userEmail}/{userPassword}")
    public User signInUser(@PathVariable String userEmail, @PathVariable String userPassword){
        User user = new User();
        List<User> users = this.userRepository.findAll();
        for(int i=0;i<users.size();i++){
            if(userEmail.equals(users.get(i).getUserEmail()) && userPassword.equals(users.get(i).getUserPassword())){
                user=users.get(i);
                break;
            }
        }
        return user;
    }

    @GetMapping("/getAllPosts")
    public List<Post> getAllPosts(){
        List<Post> posts = this.postRepository.findAll();
        return posts;
    }

    @PostMapping("/createPost")
    public String createPost(@RequestBody Post post){
        String status;
        Post saved = this.postRepository.save(post);
        if(saved!=null){
            status="valid";
        }else{
            status="invalid";
        }
        return status;
    }

    @PutMapping("/changeUserName")
    public String updateUserName(@RequestBody User user){
        String status;
        User saved = this.userRepository.save(user);
        if(saved!=null){
            status="valid";
        }else{
            status="invalid";
        }
        return status;
    }

    @DeleteMapping("/deletePost/{postID}")
    public String deletePost(@PathVariable String postID){
        this.postRepository.deleteById(postID);
        return "valid";
    }


    @GetMapping("/getPost/{postID}")
    public Post getPost(@PathVariable String postID){
        Optional<Post> optional = this.postRepository.findById(postID);
        return optional.get();
    }

    @PostMapping("/addComment")
    public String addComment(@RequestBody Comment comment){
        Post post;
        Post saved;
        String status;
        Optional<Post> optional = this.postRepository.findById(comment.getPostID());
        if(optional.get() != null){
            post=optional.get();
            post.getComments().add(comment);
            saved = this.postRepository.save(post);
            if(saved!=null){
                status="valid";
            }else{
                status="invalid";
            }
            
        }else{
            status="invalid";
        }

        return status;
    }
    
}
