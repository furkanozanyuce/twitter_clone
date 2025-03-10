package com.foy.twitter.service;

import com.foy.twitter.entity.Tweet;
import com.foy.twitter.exceptions.TwitterException;
import com.foy.twitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService{

    private final TweetRepository tweetRepository;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }


    @Override
    public List<Tweet> getAll() {
        return tweetRepository.findAll();
    }

    @Override
    public Tweet getById(Long id) {
        return tweetRepository.findById(id).orElseThrow(() -> new TwitterException("Tweet not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Tweet save(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet update(Long id, Tweet tweet) {
        Tweet tweetToUpdate = tweetRepository.findById(id).orElseThrow(() -> new TwitterException("Tweet not found", HttpStatus.NOT_FOUND));
        if (tweet.getSentence() != null) {
            tweetToUpdate.setSentence(tweet.getSentence());
        }
        return tweetRepository.save(tweetToUpdate);
    }

    @Override
    public Tweet replaceOrCreate(Long id, Tweet tweet) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);
        if (optionalTweet.isPresent()) {
            tweet.setId(id);
            return tweetRepository.save(tweet);
        }
        return tweetRepository.save(tweet);
    }

    @Override
    public void deleteById(Long id) {
        tweetRepository.deleteById(id);
    }
}
