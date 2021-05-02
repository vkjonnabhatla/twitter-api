package com.twitter.api.twitterapi.util;

import com.twitter.api.twitterapi.dto.TweetDTO;
import com.twitter.api.twitterapi.mapper.EntityMapper;
import com.twitter.api.twitterapi.mapper.TweetMapper;
import com.twitter.api.twitterapi.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class ResponseUtil {

    @Autowired
    public TweetMapper tweetMapper;

    public <R,T> ResponseEntity<R> send(Optional<T> obj){
        if(!obj.isEmpty()){
            return new ResponseEntity<>((R) tweetMapper.toDto((Tweet)obj.get()), HttpStatus.OK);
            //return ResponseEntity.ok().body(tweetMapper.toDto(obj.get()));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
