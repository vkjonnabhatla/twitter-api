package com.twitter.api.twitterapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
public class TweetAuthorDTO implements Serializable {

    private Long id;

    @NotNull
    private String screenName;

    @NotNull
    private String name;

    @NotNull
    private ZonedDateTime createdAt;

    @Size(max = 1024)
    private String profileImageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TweetAuthorDTO that = (TweetAuthorDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TweetAuthorDTO{" +
                "id=" + id +
                ", screenName='" + screenName + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                '}';
    }
}
