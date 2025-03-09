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
@Table(name = "user", schema = "twitter")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "email")
    private String email;

    @NotNull
    @NotBlank
    @Column(name = "user_name")
    private String userName;

    @NotNull
    @NotBlank
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Tweet> tweets;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_like",
            schema = "twitter",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "like_id")
            }
    )
    private Set<Like> likes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_retweet",
            schema = "twitter",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "retweet_id")
            }
    )
    private Set<Retweet> retweets;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "user_role",
            schema = "twitter",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            }
    )
    private Set<Role> roles;


    public void addTweet(Tweet tweet) {
        if (tweets == null) {
            tweets = new HashSet<>();
        }
        tweets.add(tweet);
    }

    public void addComment(Comment comment) {
        if (comments == null) {
            comments = new HashSet<>();
        }
        comments.add(comment);
    }


}
