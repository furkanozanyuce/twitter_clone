package com.foy.twitter.service;

import com.foy.twitter.entity.Tweet;
import com.foy.twitter.entity.User;

import java.util.List;

public interface TweetService {

    List<Tweet> getAll();

    Tweet getById(Long id);

    Tweet save(Tweet tweet);

    Tweet update(Long id, Tweet tweet);

    Tweet replaceOrCreate(Long id, Tweet tweet, User authenticatedUser);

    void deleteById(Long id);

}
