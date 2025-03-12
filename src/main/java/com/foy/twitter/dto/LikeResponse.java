package com.foy.twitter.dto;

public record LikeResponse(Long likeId, Long tweetId, Boolean isLiked) {
}
