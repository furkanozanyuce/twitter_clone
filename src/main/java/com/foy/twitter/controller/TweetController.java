package com.foy.twitter.controller;

import com.foy.twitter.dto.TweetRequest;
import com.foy.twitter.dto.TweetResponse;
import com.foy.twitter.entity.Tweet;
import com.foy.twitter.entity.User;
import com.foy.twitter.service.TweetService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    private final TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }


    @GetMapping
    public List<TweetResponse> getAll() {
        return tweetService.getAll().stream().map(tweet -> new TweetResponse(tweet.getSentence(), tweet.getUser().getId())).toList();
    }

    @GetMapping("/{tweetId}")
    public TweetResponse getById(@PathVariable("tweetId") Long id) {
        Tweet tweet = tweetService.getById(id);
        return new TweetResponse(tweet.getSentence(), tweet.getUser().getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponse save(@Validated @RequestBody TweetRequest tweetRequest, @AuthenticationPrincipal User user) {
        Tweet tweet = new Tweet();
        tweet.setSentence(tweetRequest.getSentence());
        tweet.setUser(user);

        tweetService.save(tweet);

        return new TweetResponse(tweet.getSentence(), tweet.getUser().getId());
    }

    @PutMapping("/{tweetId}")
    public TweetResponse replaceOrCreate(@PathVariable("tweetId") Long id,@Validated @RequestBody TweetRequest tweetRequest, @AuthenticationPrincipal User authenticatedUser) {
        Tweet tweet = new Tweet();
        tweet.setSentence(tweetRequest.getSentence());

        Tweet savedTweet = tweetService.replaceOrCreate(id, tweet, authenticatedUser);
        return new TweetResponse(savedTweet.getSentence(), savedTweet.getUser().getId());
    }

    @PatchMapping("/{tweetId}")
    public TweetResponse update(@PathVariable("tweetId") Long id,@Validated @RequestBody TweetRequest tweetRequest) {
        Tweet tweet = new Tweet();
        if (tweetRequest.getSentence() != null) {
            tweet.setSentence(tweetRequest.getSentence());
        }

        Tweet updatedTweet = tweetService.update(id, tweet);
        return new TweetResponse(updatedTweet.getSentence(), updatedTweet.getUser().getId());
    }

    @DeleteMapping("/{tweetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("tweetId") Long id) {
        tweetService.deleteById(id);
    }

}































