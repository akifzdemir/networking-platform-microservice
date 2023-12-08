package com.akifozdemir.userservice.controllers;

import com.akifozdemir.userservice.dtos.FollowRequest;
import com.akifozdemir.userservice.services.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/follows")
public class FollowController {
    private final FollowService followService;
    public FollowController(FollowService followService){
        this.followService = followService;
    }

    @PostMapping("/follow")
    public ResponseEntity<String> follow(@RequestBody FollowRequest followRequest){
        this.followService.follow(followRequest);
        return ResponseEntity.ok().body("User has followed");
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<String> unfollow(@RequestBody FollowRequest followRequest){
        this.followService.unfollow(followRequest);
        return ResponseEntity.ok().body("User has unfollowed");
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkIfFollowing(@RequestParam UUID userId,@RequestParam UUID followingId){
        return ResponseEntity.ok().body(this.followService.isFollowing(userId,followingId));

    }
    @PostMapping("/toggle")
    public ResponseEntity<String> toggle(@RequestBody FollowRequest followRequest){
        return ResponseEntity.ok().body(this.followService.toggle(followRequest));
    }
}
