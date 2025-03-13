package com.foy.twitter.controller;

import com.foy.twitter.dto.CommentRequest;
import com.foy.twitter.dto.CommentResponse;
import com.foy.twitter.entity.Comment;
import com.foy.twitter.entity.Tweet;
import com.foy.twitter.entity.User;
import com.foy.twitter.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse add(@Validated @RequestBody CommentRequest commentRequest, @AuthenticationPrincipal User user) {
        Comment comment = new Comment();

        comment.setTweet(new Tweet(commentRequest.getTweetId(), null, null, null, null, null));
        comment.setSentence(commentRequest.getSentence());
        comment.setUser(user);

        Comment savedComment = commentService.save(comment);
        return new CommentResponse(savedComment.getId(), savedComment.getTweet().getId(), savedComment.getUser().getId(), savedComment.getUser().getUserName(), savedComment.getSentence());
    }

    @PutMapping("/{commentId}")
    public CommentResponse update(@PathVariable("commentId") Long commentId, @Validated @RequestBody CommentRequest commentRequest,
                                         @AuthenticationPrincipal User user) {
        Comment updatedComment = commentService.update(commentId, commentRequest.getSentence(), user);
        return new CommentResponse(updatedComment.getId(), updatedComment.getTweet().getId(), updatedComment.getUser().getId(), updatedComment.getUser().getUserName(), updatedComment.getSentence());
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("commentId") Long commentId, @AuthenticationPrincipal User user) {
        commentService.delete(commentId, user);
    }

    @GetMapping("/byTweet")
    public List<CommentResponse> getCommentsByTweet(@RequestParam Long tweetId) {
        return commentService
                .findByTweetId(tweetId)
                .stream()
                .map(comment -> new CommentResponse(comment.getId(), comment.getTweet().getId(), comment.getUser().getId(), comment.getUser().getUserName(), comment.getSentence()))
                .toList();
    }
}
