package com.foy.twitter.controller;

import com.foy.twitter.dto.LikeResponse;
import com.foy.twitter.entity.Like;
import com.foy.twitter.entity.User;
import com.foy.twitter.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }


    @PostMapping("/like")
    public LikeResponse likeTweet(@RequestParam Long tweetId, @AuthenticationPrincipal User user) {
        Like like = likeService.likeTweet(tweetId, user);
        return new LikeResponse(like.getId(), like.getTweet().getId(), like.getIsLiked());
    }

    @PostMapping("/dislike")
    public LikeResponse dislikeTweet(@RequestParam Long tweetId, @AuthenticationPrincipal User user) {
        likeService.dislikeTweet(tweetId, user);
        return new LikeResponse(tweetId, tweetId, false);
    }
}
