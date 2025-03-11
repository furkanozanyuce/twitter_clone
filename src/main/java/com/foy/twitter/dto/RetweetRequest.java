package com.foy.twitter.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RetweetRequest {

    @NotNull
    private Long tweetId;

    private String message;

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
