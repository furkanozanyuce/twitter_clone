package com.foy.twitter.dto;

public record RetweetResponse(Long retweetId, Long tweetId, String message) {
}
