package com.foy.twitter.service;

import com.foy.twitter.entity.Comment;
import com.foy.twitter.entity.Tweet;
import com.foy.twitter.entity.User;
import com.foy.twitter.exceptions.TwitterException;
import com.foy.twitter.repository.CommentRepository;
import com.foy.twitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final TweetRepository tweetRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, TweetRepository tweetRepository) {
        this.commentRepository = commentRepository;
        this.tweetRepository = tweetRepository;
    }

    @Override
    public Comment save(Comment comment) {
        Tweet tweet = tweetRepository.findById(comment.getTweet().getId())
                .orElseThrow(() -> new TwitterException("Tweet not found", HttpStatus.NOT_FOUND));
        comment.setTweet(tweet);
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Long id, String sentence, User authenticatedUser) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new TwitterException("Comment not found", HttpStatus.NOT_FOUND));

        if (!comment.getUser().getId().equals(authenticatedUser.getId())) {
            throw new TwitterException("You are not authorized to update this comment", HttpStatus.FORBIDDEN);
        }
        comment.setSentence(sentence);
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long id, User authenticatedUser) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new TwitterException("Comment not found", HttpStatus.NOT_FOUND));

        if (!comment.getUser().getId().equals(authenticatedUser.getId())
                && !comment.getTweet().getUser().getId().equals(authenticatedUser.getId())) {
            throw new TwitterException("You are not authorized to delete this comment", HttpStatus.FORBIDDEN);
        }
        commentRepository.delete(comment);
    }
}
