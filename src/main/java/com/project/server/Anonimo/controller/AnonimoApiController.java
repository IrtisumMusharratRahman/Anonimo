package com.project.server.Anonimo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    public ResponseEntity<?> signUpUser(@RequestBody User user){
        User saved = this.userRepository.save(user);
        return ResponseEntity.ok(saved);
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
    public ResponseEntity<?> createPost(@RequestBody Post post){
        Post saved = this.postRepository.save(post);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/changeUserName")
    public ResponseEntity<?> updateUserName(@RequestBody User user){
        User saved = this.userRepository.save(user);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/deletePost/{postID}")
    public void deletePost(@PathVariable String postID){
        this.postRepository.deleteById(postID);
    }


    @GetMapping("/getPost/{postID}")
    public Post getPost(@PathVariable String postID){
        Optional<Post> optional = this.postRepository.findById(postID);
        return optional.get();
    }

    @PostMapping("/addComment")
    public ResponseEntity<?> addComment(@RequestBody Comment comment){
        Post post=new Post();
        Optional<Post> optional = this.postRepository.findById(comment.getPostID());
        if(optional.get() != null){
            post=optional.get();
            List<Comment> cList = post.getComments();
            cList.add(comment);
            post.setComments(cList);
            this.postRepository.save(post);
        }
        
        return ResponseEntity.ok(post);
    }
    
}
