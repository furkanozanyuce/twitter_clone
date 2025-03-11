package com.foy.twitter.service;

import com.foy.twitter.entity.Like;
import com.foy.twitter.entity.Tweet;
import com.foy.twitter.entity.User;
import com.foy.twitter.exceptions.TwitterException;
import com.foy.twitter.repository.LikeRepository;
import com.foy.twitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService{

    private final LikeRepository likeRepository;
    private final TweetRepository tweetRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository, TweetRepository tweetRepository) {
        this.likeRepository = likeRepository;
        this.tweetRepository = tweetRepository;
    }

    @Override
    public Like likeTweet(Long tweetId, User user) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TwitterException("Tweet not found", HttpStatus.NOT_FOUND));

        Optional<Like> existingLike = likeRepository.findByTweetIdAndUserId(tweetId, user.getId());
        if (existingLike.isPresent()) {
            return existingLike.get();
        }

        Like like = new Like();
        like.setTweet(tweet);
        like.setUser(user);
        like.setIsLiked(true);
        return likeRepository.save(like);
    }

    @Override
    public void dislikeTweet(Long tweetId, User user) {
        Like like = likeRepository.findByTweetIdAndUserId(tweetId, user.getId())
                .orElseThrow(() -> new TwitterException("Like not found", HttpStatus.NOT_FOUND));
        likeRepository.delete(like);
    }
}
