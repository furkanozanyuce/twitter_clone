package com.foy.twitter.service;

import com.foy.twitter.dto.RetweetRequest;
import com.foy.twitter.entity.Retweet;
import com.foy.twitter.entity.Tweet;
import com.foy.twitter.entity.User;
import com.foy.twitter.exceptions.TwitterException;
import com.foy.twitter.repository.RetweetRepository;
import com.foy.twitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RetweetServiceImpl implements RetweetService{

    private final RetweetRepository retweetRepository;
    private final TweetRepository tweetRepository;

    @Autowired
    public RetweetServiceImpl(RetweetRepository retweetRepository, TweetRepository tweetRepository) {
        this.retweetRepository = retweetRepository;
        this.tweetRepository = tweetRepository;
    }


    @Override
    public Retweet retweet(RetweetRequest retweetRequest, User user) {
        Tweet tweet = tweetRepository.findById(retweetRequest.getTweetId())
                .orElseThrow(() -> new TwitterException("Tweet not found", HttpStatus.NOT_FOUND));
        Retweet retweet = new Retweet();
        retweet.setTweet(tweet);
        retweet.setUser(user);
        retweet.setIsRetweeted(true);

        if (retweetRequest.getMessage() != null && !retweetRequest.getMessage().isEmpty()) {
            retweet.setMessage(retweetRequest.getMessage());
        } else {
            retweet.setMessage(tweet.getSentence());
        }
        return retweetRepository.save(retweet);
    }

    @Override
    public void deleteRetweet(Long retweetId, User user) {
        Retweet retweet = retweetRepository.findByIdAndUserId(retweetId, user.getId())
                .orElseThrow(() -> new TwitterException("Retweet not found or you are not authorized to delete it", HttpStatus.NOT_FOUND));
        retweetRepository.delete(retweet);
    }
}
