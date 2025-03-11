package com.foy.twitter.service;

import com.foy.twitter.dto.RetweetRequest;
import com.foy.twitter.entity.Retweet;
import com.foy.twitter.entity.User;

public interface RetweetService {

    Retweet retweet(RetweetRequest retweetRequest, User user);
    void deleteRetweet(Long retweetId, User user);

}
