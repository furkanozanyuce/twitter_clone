package com.foy.twitter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "retweet", schema = "twitter")
public class Retweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_retweeted")
    private Boolean isRetweeted;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

}
