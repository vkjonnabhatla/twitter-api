package com.twitter.api.twitterapi.controller;

import com.twitter.api.twitterapi.dto.TweetDTO;
import com.twitter.api.twitterapi.mapper.TweetMapper;
import com.twitter.api.twitterapi.model.Tweet;
import com.twitter.api.twitterapi.repository.TweetRepository;
import com.twitter.api.twitterapi.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RefreshScope
@Slf4j
public class TwitterResource {

    Logger logger = LoggerFactory.getLogger(TwitterResource.class);

    @Value("${application.name}")
    private String application;

    @Autowired
    private TweetMapper tweetMapper;

    @Autowired
    private ResponseUtil responseUtil;

    @Autowired
    private TweetRepository tweetRepository;

    @GetMapping("/hello")
    public String hello(){
        logger.info("====================== "+ application + "================");
        return application;
    }

    @PostMapping("/tweets")
    public ResponseEntity<TweetDTO> createTweet(@RequestBody TweetDTO tweetDTO) throws URISyntaxException {
        logger.debug("REST request to save Tweet : {}", tweetDTO);
        if(tweetDTO.getId() != null){
            HttpHeaders headers = new HttpHeaders();
            headers.set("Tweet", "A new tweet cannot already have an ID");
            return ResponseEntity.badRequest().headers(headers).body(null);
        }

        Tweet tweet =  tweetMapper.toEntity(tweetDTO);
        tweet = tweetRepository.save(tweet);
        TweetDTO result = tweetMapper.toDto(tweet);
        return ResponseEntity.created(new URI("/api/tweets/" + result.getId()))
                .body(result);
    }

    @PutMapping("/tweets")
    public ResponseEntity<TweetDTO> updateTweet(@RequestBody TweetDTO tweetDTO) throws URISyntaxException {
       logger.debug("Rest request to udpate tweet: {} "+ tweetDTO);
       if(tweetDTO.getId() == null){
           return createTweet(tweetDTO);
       }

       Tweet tweet = tweetMapper.toEntity(tweetDTO);
       tweet = tweetRepository.save(tweet);
       TweetDTO result = tweetMapper.toDto(tweet);
       return ResponseEntity.ok().build();
    }

    @GetMapping("/tweets")
    public ResponseEntity<List<TweetDTO>> getAllTweets(Pageable pageable){
        //pageable = PageRequest.of(0, 20, Sort.by("tweetId").descending());
        Page<Tweet> tweets = tweetRepository.findAll(pageable);
        //HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tweets");
        return new ResponseEntity<>(tweetMapper.toDtos(tweets.getContent()), HttpStatus.OK);
    }

    @GetMapping("/feed")
    public ResponseEntity<List<TweetDTO>> getTweetFeed(Pageable pageable){
        //pageable = PageRequest.of(0, 20, Sort.by("tweetId").descending());
        pageable = PageRequest.of(pageable.getPageNumber(), 100);
        Page<Tweet> tweets = tweetRepository.findAll(pageable);
        //HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tweets");
        return new ResponseEntity<>(tweetMapper.toDtos(tweets.getContent()), HttpStatus.OK);
    }

    @GetMapping("/tweets/{id}")
    public ResponseEntity<TweetDTO> getTweet(@PathVariable Long id){
        logger.debug("Rest request to get the tweet: {}"+ id);
        Optional<Tweet> tweet = tweetRepository.findById(id);
        return responseUtil.send(tweet);
    }

    public ResponseEntity<Void> deleteTweet(@PathVariable Long id){
        logger.debug("Rest request to delete the tweet: {} "+ id);
        tweetRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
