package com.example.postservice.controllers;

import com.example.postservice.dtos.requests.PostRequest;
import com.example.postservice.dtos.requests.PostUpdateRequest;
import com.example.postservice.dtos.respones.PostResponse;
import com.example.postservice.services.PostImageService;
import com.example.postservice.services.PostService;
import feign.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;

    }
    @PostMapping
    public ResponseEntity<String> add(
            @RequestParam("description") String description,
            @RequestParam("userId") UUID userId,
            @RequestParam(value = "files", required = false) MultipartFile[] files
    ){
        PostRequest postRequest = new PostRequest(description, userId);
        this.postService.add(postRequest,files);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Post Shared.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody PostUpdateRequest postUpdateRequest){
        this.postService.update(postUpdateRequest,id);
        return ResponseEntity.ok().body("Post Updated");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponse>> getByUser(@PathVariable UUID userId){
        return ResponseEntity.ok().body(this.postService.getByUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAll(){
        return new ResponseEntity<>(this.postService.getAllPostsWithUserDetails(), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        this.postService.delete(id);
        return  ResponseEntity.ok().body("Post Deleted");
    }
}
