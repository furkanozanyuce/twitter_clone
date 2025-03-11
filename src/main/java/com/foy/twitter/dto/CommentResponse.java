package com.foy.twitter.dto;

public record CommentResponse(Long tweetId, Long userId, String sentence) {
}
