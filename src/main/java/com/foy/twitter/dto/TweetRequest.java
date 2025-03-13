package com.foy.twitter.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class TweetRequest {

    public TweetRequest() {
    }

    public TweetRequest(String sentence) {
        this.sentence = sentence;
    }

    @NotNull
    @NotBlank
    @Column(name = "sentence")
    private String sentence;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
