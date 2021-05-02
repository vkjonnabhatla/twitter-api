package com.twitter.api.twitterapi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;


@Entity
@Table(name = "tweet")
@Setter
@Getter
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tweet implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tweetId;

    @NotNull
    @Size(max = 140)
    @Column(name = "tweet_text", length = 140, nullable = false)
    private String tweetText;

    @NotNull
    @Column(name="created_at", nullable = false)
    private ZonedDateTime createdAt;

    //@NotNull
    @Column(name = "lang", nullable = true)
    private String language;

    //@NotNull
    @ManyToOne
    private TweetAuthor createdBy;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return tweetId == tweet.tweetId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tweetId);
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweetId=" + tweetId +
                ", tweetText='" + tweetText + '\'' +
                ", createdAt=" + createdAt +
                ", language='" + language + '\'' +
                ", createdBy=" + createdBy +
                '}';
    }
}
