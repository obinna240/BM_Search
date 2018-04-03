package com.pcg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoCollectionNameSetterConfig {
	@Value("${db.collection}")
	private String
	    mongoEventCollectionName;

	@Bean
	public String mongoEventCollectionName() {
	    return
	        mongoEventCollectionName;
	}
}
