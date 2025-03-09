package com.foy.twitter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "like", schema = "twitter")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_liked")
    private Boolean isLiked;

    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

}
