package com.foy.twitter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foy.twitter.dto.TweetRequest;
import com.foy.twitter.entity.Tweet;
import com.foy.twitter.entity.User;
import com.foy.twitter.service.TweetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TweetController.class)
@AutoConfigureMockMvc(addFilters = false) //spring security devre dışı
class TweetControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private TweetService tweetService;


    @Test
    void save() throws Exception {
        TweetRequest tweetRequest = new TweetRequest("Test tweet");
        Tweet tweet = new Tweet();
        tweet.setId(100L);
        tweet.setSentence("Test tweet");
        tweet.setCreatedAt(LocalDateTime.now());
        User user = new User();
        user.setId(100L);
        user.setUserName("TestUser");
        tweet.setUser(user);

        given(tweetService.save(any(Tweet.class))).willReturn(tweet);

        mockMvc.perform(post("/tweet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(tweetRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.sentence").value("Test tweet"))
                .andExpect(jsonPath("$.tweetId").value(100));
    }

    @Test
    void getById() throws Exception {
        Tweet tweet = new Tweet();
        tweet.setId(200L);
        tweet.setSentence("Test tweet");
        tweet.setCreatedAt(LocalDateTime.now());
        User user = new User();
        user.setId(200L);
        user.setUserName("testUser");
        tweet.setUser(user);

        given(tweetService.getById(200L)).willReturn(tweet);

        mockMvc.perform(get("/tweet/200")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sentence").value("Test tweet"))
                .andExpect(jsonPath("$.tweetId").exists());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}