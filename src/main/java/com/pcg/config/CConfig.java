package com.pcg.config;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
//import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.pcg.db.PostCodeDBCacheConverter;

//public class Config{}



@Configuration
@EnableMongoRepositories(basePackages = "com.pcg.db")
@EnableAutoConfiguration(exclude = { EmbeddedMongoAutoConfiguration.class })
public class CConfig {

	/**
	@Value("${mongoDBName}") 
	String dbName;
	@Value("${mongoReplicasetList}")
	private String[] replicaset;
	@Value("${mongoReplicaSetPort}")
	private String[] replicasetPort;
	*/
	
	@Value("${mongoDBURI}")
	private String mongoDBURI;
	
	@Bean
	  public MappingMongoConverter mappingMongoConverter(MongoDbFactory mongoDbFactory,
	    MongoMappingContext mongoMappingContext, CustomConversions customConversions) throws Exception {

	    MappingMongoConverter converter =
	      new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), mongoMappingContext);
	    converter.setCustomConversions(customConversions);

	    return converter;
	  }
	
	
	
	
	/**
	   * Customize the MongoDB client connection properties.
	   *
	   * @return A {@link MongoClientOptions} bean that is used by the {@link
	   * org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration} when creating the {@link
	   * com.mongodb.Mongo} facade
	   * @see com.mongodb.MongoClientOptions
	   * @see org.springframework.core.env.Environment
	   */
	  @Bean
	  public MongoClientOptions mongoClientOptions(
	    @Value("${spring.data.mongodb.min-connections:2}") final int minConnections,
	    @Value("${spring.data.mongodb.max-connections:10}") final int maxConnections,
	    @Value("${spring.data.mongodb.socket-connect.ms:5000}") final int connectTimeout,
	    @Value("${spring.data.mongodb.socket-timeout.ms:5000}") final int socketTimeout) {

	    return MongoClientOptions.builder()
	      .legacyDefaults()
	      .minConnectionsPerHost(minConnections)
	      .connectionsPerHost(maxConnections)
	      .connectTimeout(connectTimeout)
	      .socketTimeout(socketTimeout)
	      .build();
	  }
  
	/**
    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("localhost", new MongoClientOptions.Builder()//requiredReplicaSetName(REPLICASET_NAME)
        	    .maxConnectionIdleTime (0)
        	    .connectionsPerHost(5)
        	    .cursorFinalizerEnabled(false)
        	    .build());
    }
  */
   // @Override
   // protected String getMappingBasePackage() {
   //     return "com.pcg.db";
   // }

	/**@Override
	public MongoClient mongoClient() {
		// TODO Auto-generated method stub
		 return new MongoClient("localhost", new MongoClientOptions.Builder()//requiredReplicaSetName(REPLICASET_NAME)
 	    .maxConnectionIdleTime (0)
 	    .connectionsPerHost(5)
 	    .cursorFinalizerEnabled(false)
 	    .build());
	}
	*/
	
	@Bean 
    public CustomConversions customConversions() {
		final List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
		converters.add(new PostCodeDBCacheConverter());
        return new CustomConversions(converters);
    }

	//@Override
//	public Mongo mongo() throws Exception {
		// TODO Auto-generated method stub
//		return new MongoClient("localhost", new MongoClientOptions.Builder()//requiredReplicaSetName(REPLICASET_NAME)
 //	    .maxConnectionIdleTime (0)
 //	    .connectionsPerHost(5)
 //	    .cursorFinalizerEnabled(false)
 //	    .build());
	//}
	
	public @Bean 
	MongoDbFactory mongoDbFactory() throws Exception { 
		
		return new SimpleMongoDbFactory(new MongoClientURI(mongoDBURI));//"PostCodeCache");
		
		/**
		 MongoClient mongoClient = new MongoClient(Arrays.asList(
				   new ServerAddress("localhost", 27017),
				   new ServerAddress("localhost", 27018),
				   new ServerAddress("localhost", 27019)));
		 
		return new SimpleMongoDbFactory(new MongoClient(), dbName);//"PostCodeCache");
		*/
	}

	//public @Bean
	//MongoTemplate mongoTemplate() throws Exception {

	//	MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

	//	return mongoTemplate;

	//}
	
	@Bean
	  public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory,
	    MappingMongoConverter mappingMongoConverter) throws UnknownHostException {
	    return new MongoTemplate(mongoDbFactory, mappingMongoConverter);
	  }
  
}
/**
new MongoClientOptions.Builder()
    .requiredReplicaSetName(REPLICASET_NAME)
    .maxConnectionIdleTime (MAX_IDLE_TIME)
    .connectionsPerHost(CONNECTION_PER_HOST)
    .cursorFinalizerEnabled(false)
    .build();
}
*/



  