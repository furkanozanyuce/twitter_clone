package com.foy.twitter.service;

import com.foy.twitter.entity.Tweet;

import java.util.List;

public interface TweetService {

    List<Tweet> getAll();

    Tweet getById(Long id);

    Tweet save(Tweet tweet);

    Tweet update(Long id, Tweet tweet);

    Tweet replaceOrCreate(Long id, Tweet tweet);

    void deleteById(Long id);

}
