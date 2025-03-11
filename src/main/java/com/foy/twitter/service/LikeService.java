package com.foy.twitter.service;

import com.foy.twitter.entity.Like;
import com.foy.twitter.entity.User;

public interface LikeService {

    Like likeTweet(Long tweetId, User user);
    void dislikeTweet(Long tweetId, User user);

}
