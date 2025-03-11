package com.foy.twitter.controller;

import com.foy.twitter.dto.RetweetRequest;
import com.foy.twitter.dto.RetweetResponse;
import com.foy.twitter.entity.Retweet;
import com.foy.twitter.entity.User;
import com.foy.twitter.service.RetweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retweet")
public class RetweetController {

    private final RetweetService retweetService;

    @Autowired
    public RetweetController(RetweetService retweetService) {
        this.retweetService = retweetService;
    }

    @PostMapping
    public RetweetResponse retweet(@Validated @RequestBody RetweetRequest retweetRequest, @AuthenticationPrincipal User user) {
        Retweet retweet = retweetService.retweet(retweetRequest, user);
        return new RetweetResponse(retweet.getId(), retweet.getTweet().getId(), retweet.getMessage());
    }

    @DeleteMapping("/{retweetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRetweet(@PathVariable("retweetId") Long retweetId, @AuthenticationPrincipal User user) {
        retweetService.deleteRetweet(retweetId, user);
    }
}
