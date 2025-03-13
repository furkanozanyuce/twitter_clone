package com.foy.twitter.dto;

public record LoginResponse(Long userId, String email, String userName, String message) {
}
