package com.foy.twitter.service;

import com.foy.twitter.entity.Comment;
import com.foy.twitter.entity.User;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment);
    Comment update(Long id, String sentence, User authenticatedUser);
    void delete(Long id, User authenticatedUser);
    List<Comment> findByTweetId(Long tweetId);

}
