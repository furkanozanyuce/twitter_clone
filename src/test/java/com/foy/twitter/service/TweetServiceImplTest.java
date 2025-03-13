package com.foy.twitter.service;

import com.foy.twitter.entity.Tweet;
import com.foy.twitter.entity.User;
import com.foy.twitter.exceptions.TwitterException;
import com.foy.twitter.repository.TweetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TweetServiceImplTest {

    private TweetService tweetService;

    @Mock
    private TweetRepository tweetRepository;

    @BeforeEach
    void setUp() {
        tweetService = new TweetServiceImpl(tweetRepository);
    }


    @Test
    void getById() {
        Tweet tweet = new Tweet();
        tweet.setId(90L);
        tweet.setSentence("HELLO BRO");
        tweet.setCreatedAt(LocalDateTime.now());
        User user = new User();
        user.setId(90L);
        user.setUserName("TestUser");
        tweet.setUser(user);

        given(tweetRepository.findById(90L)).willReturn(Optional.of(tweet));

        Tweet foundTweet = tweetService.getById(90L);
        assertNotNull(foundTweet);
        assertEquals("HELLO BRO", foundTweet.getSentence());
    }

    @Test
    void getByIdNotFound() {
        given(tweetRepository.findById(1L)).willReturn(Optional.empty());
        assertThatThrownBy(() -> tweetService.getById(1L))
                .isInstanceOf(TwitterException.class);
    }

    @Test
    void save() {
        Tweet tweet = new Tweet();
        tweet.setSentence("New test tweet");
        tweet.setCreatedAt(LocalDateTime.now());
        User user = new User();
        user.setId(1L);
        user.setUserName("TestUser");
        tweet.setUser(user);

        when(tweetRepository.save(tweet)).thenReturn(tweet);
        Tweet savedTweet = tweetService.save(tweet);
        assertEquals("New test tweet", savedTweet.getSentence());
        verify(tweetRepository).save(tweet);
    }

}