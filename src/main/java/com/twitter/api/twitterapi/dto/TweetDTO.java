package com.twitter.api.twitterapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@Setter
@Getter
public class TweetDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 140)
    private String tweetText;

    @NotNull
    private ZonedDateTime createdAt;

    @NotNull
    @Size(max = 5)
    private String lang;

    private Long createdById;

    private String createdByScreenName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TweetDTO tweetDTO = (TweetDTO) o;
        return Objects.equals(id, tweetDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TweetDTO{" +
                "id=" + id +
                ", tweetText='" + tweetText + '\'' +
                ", createdAt=" + createdAt +
                ", lang='" + lang + '\'' +
                ", createdById=" + createdById +
                ", createdByScreenName='" + createdByScreenName + '\'' +
                '}';
    }
}
