package com.foy.twitter.dto;

import java.time.LocalDateTime;

public record TweetResponse(String sentence, Long userId, String userName, LocalDateTime createdAt) {
}
