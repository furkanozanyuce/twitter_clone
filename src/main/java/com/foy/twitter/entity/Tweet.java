package com.foy.twitter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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

    public Tweet() {
    }

    public Tweet(Long id, String sentence, User user, Set<Retweet> retweets, Set<Like> likes, Set<Comment> comments) {
        this.id = id;
        this.sentence = sentence;
        this.user = user;
        this.retweets = retweets;
        this.likes = likes;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public Set<Retweet> getRetweets() {
        return retweets;
    }

    public void setRetweets(Set<Retweet> retweets) {
        this.retweets = retweets;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    public Set<Comment> getComments() {
        return comments;
    }


    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    //    public void addRetweet(Retweet retweet) {
//        if (retweets == null) {
//            retweets = new HashSet<>();
//        }
//        retweets.add(retweet);
//    }
//
//    public void addLike(Like like) {
//        if (likes == null) {
//            likes = new HashSet<>();
//        }
//        likes.add(like);
//    }
//
//    public void addComment(Comment comment) {
//        if (comments == null) {
//            comments = new HashSet<>();
//        }
//        comments.add(comment);
//    }

}
