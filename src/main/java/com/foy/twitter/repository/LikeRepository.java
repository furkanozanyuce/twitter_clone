package com.foy.twitter.repository;

import com.foy.twitter.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByTweetIdAndUserId(Long tweetId, Long userId);

}
