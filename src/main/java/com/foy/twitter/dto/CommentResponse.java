package com.foy.twitter.dto;

public record CommentResponse(Long commentId, Long tweetId, Long userId, String userName, String sentence) {
}
