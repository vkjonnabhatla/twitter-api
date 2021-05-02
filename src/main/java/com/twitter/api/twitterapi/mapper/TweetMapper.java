package com.twitter.api.twitterapi.mapper;

import com.twitter.api.twitterapi.dto.TweetDTO;
import com.twitter.api.twitterapi.model.Tweet;
import com.twitter.api.twitterapi.model.TweetAuthor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {TweetAuthorMapper.class })
//@Component
public interface TweetMapper extends EntityMapper<TweetDTO, Tweet> {

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.screenName", target = "createdByScreenName")
    @Mapping(source = "tweetId", target = "id")
    @Mapping(source = "tweetText", target = "tweetText")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "language", target = "lang")
    public TweetDTO toDto(Tweet entity);
    /*{
        TweetDTO tweetDTO = new TweetDTO();
        tweetDTO.setTweetText(entity.getTweetText());
        tweetDTO.setLang(entity.getLanguage());
        tweetDTO.setCreatedAt(entity.getCreatedAt());
        tweetDTO.setCreatedById(entity.getCreatedBy().getId());
        tweetDTO.setCreatedByScreenName(entity.getCreatedBy().getScreenName());
        return tweetDTO;
    }*/

    @Mapping(source = "createdById", target = "createdBy.id")
    @Mapping(source = "lang", target = "language")
    @Mapping(source = "id", target = "tweetId")
    @Mapping(source = "createdByScreenName", target = "createdBy.screenName")
    public Tweet toEntity(TweetDTO tweetDTO);
    /*{
        Tweet tweet = new Tweet();
        TweetAuthor tweetAuthor = new TweetAuthor();
        tweet.setTweetText(tweetDTO.getTweetText());
        tweet.setLanguage(tweetDTO.getLang());
        tweet.setCreatedAt(tweetDTO.getCreatedAt());
        tweetAuthor.setId(tweetDTO.getCreatedById());
        tweetAuthor.setScreenName(tweetDTO.getCreatedByScreenName());
        tweet.setCreatedBy(tweetAuthor);
        return tweet;
    }*/

    /*public List<TweetDTO> toDtos(List<Tweet> entity){
        if(!CollectionUtils.isEmpty(entity)){
            return entity.stream().map(tweet -> toDto(tweet)).collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    public List<Tweet> toEntities(List<TweetDTO> tweetDTO){
        if(!CollectionUtils.isEmpty(tweetDTO)){
            return tweetDTO.stream().map(tweet -> toEntity(tweet)).collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }*/

    /*@Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.screenName", target = "createdByScreenName")
    @Mapping(source = "tweetId", target = "id")
    @Mapping(source = "tweetText", target = "tweetText")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "language", target = "lang")*/
    //List<TweetDTO> toDtos(List<Tweet> entities);

    /*default Tweet fromId(Long id){
        if(id == null) {
            return null;
        }

        Tweet tweet = new Tweet();
        tweet.setTweetId(id);
        return tweet;
    }*/

    /*default TweetDTO toDto(Tweet tweet) {
        TweetDTO tweetDTO = new TweetDTO();
        tweetDTO.setId(tweet.getTweetId());
        tweetDTO.setLang(tweet.getLanguage());
        tweetDTO.setCreatedAt(tweet.getCreatedAt());
        tweetDTO.setCreatedByScreenName(tweet.getCreatedBy().getScreenName());
        tweetDTO.setTweetText(tweet.getTweetText());
        tweetDTO.setCreatedById(tweet.getCreatedBy().getId());
        return tweetDTO;
    }*/
}
