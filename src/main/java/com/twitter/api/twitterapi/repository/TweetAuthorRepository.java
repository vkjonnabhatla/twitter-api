package com.twitter.api.twitterapi.repository;

import com.twitter.api.twitterapi.model.TweetAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetAuthorRepository extends JpaRepository<TweetAuthor, Long> {
}
