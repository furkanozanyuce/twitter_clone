package com.foy.twitter.exceptions;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TwitterErrorResponse {

    public TwitterErrorResponse() {
    }

    public TwitterErrorResponse(String message, int status, LocalDateTime dateTime) {
        this.message = message;
        this.status = status;
        this.dateTime = dateTime;
    }

    private String message;
    private int status;
    private LocalDateTime dateTime;


}
