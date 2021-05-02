package com.twitter.api.twitterapi.mapper;

import com.twitter.api.twitterapi.dto.TweetAuthorDTO;
import com.twitter.api.twitterapi.model.TweetAuthor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface TweetAuthorMapper extends EntityMapper<TweetAuthorDTO, TweetAuthor>{

    @Mapping(target = "tweets", ignore = true)
    TweetAuthor toEntity(TweetAuthorDTO dto);

    default TweetAuthor fromId(Long id) {
        if (id == null) {
            return null;
        }
        TweetAuthor tweetAuthor = new TweetAuthor();
        tweetAuthor.setId(id);
        return tweetAuthor;
    }
}
