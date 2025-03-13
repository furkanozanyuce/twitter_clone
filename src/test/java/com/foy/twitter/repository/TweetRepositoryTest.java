package com.foy.twitter.repository;

import com.foy.twitter.entity.Tweet;
import com.foy.twitter.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TweetRepositoryTest {

    private TweetRepository tweetRepository;
    private UserRepository userRepository;

    @Autowired
    public TweetRepositoryTest(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    private Tweet tweet;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("thisisfortest@test.com");
        user.setUserName("UserTest");
        user.setPassword("1234");
        user = userRepository.save(user);

        tweet = new Tweet();
        tweet.setSentence("Test tweet!");
        tweet.setUser(user);
        tweet.setCreatedAt(LocalDateTime.now());
        tweet = tweetRepository.save(tweet);
    }

    @AfterEach
    void tearDown() {
        tweetRepository.delete(tweet);
        userRepository.delete(user);
    }

    @DisplayName("Find tweet by tweetId")
    @Test
    void findByTweetId() {
        Tweet foundTweet = tweetRepository.findById(tweet.getId()).orElse(null);
        assertNotNull(foundTweet);
        assertEquals("Test tweet!", foundTweet.getSentence());
        assertEquals(user.getId(), foundTweet.getUser().getId());
    }
}






































