package com.foy.twitter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tweet", schema = "twitter")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "sentence")
    private String sentence;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    private Set<Retweet> retweets;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    private Set<Like> likes;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    private Set<Comment> comments;


    public void addRetweet(Retweet retweet) {
        if (retweets == null) {
            retweets = new HashSet<>();
        }
        retweets.add(retweet);
    }

    public void addLike(Like like) {
        if (likes == null) {
            likes = new HashSet<>();
        }
        likes.add(like);
    }

    public void addComment(Comment comment) {
        if (comments == null) {
            comments = new HashSet<>();
        }
        comments.add(comment);
    }

}
