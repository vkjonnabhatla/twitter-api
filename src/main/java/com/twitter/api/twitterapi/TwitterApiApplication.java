package com.twitter.api.twitterapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class TwitterApiApplication {

	@Value("${application.name}")
	public String name;
		public static void main(String[] args) {
		SpringApplication.run(TwitterApiApplication.class, args);
	}

}
