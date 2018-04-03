package com.pcg.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;

/**
 * 
 * @author oonyimadu
 *
 */
@Repository 
public class PostCodeDBCacheRepositoryImpl implements PostCodeDBCacheOperations{
	private MongoOperations mongo;
	MongoDbFactory mongoDbFactory;
	MappingMongoConverter mappingMongoConverter;
	/**
	@Autowired
	private MongoOperations mongo;
	@Autowired MongoDbFactory mongoDbFactory;
	MappingMongoConverter mappingMongoConverter;
	*/
	@Autowired
    public PostCodeDBCacheRepositoryImpl(MongoDbFactory mongoDbFactory, MappingMongoConverter mappingMongoConverter) {
        this.mongoDbFactory = mongoDbFactory;
        this.mappingMongoConverter = mappingMongoConverter;
        mongo = new MongoTemplate(mongoDbFactory, mappingMongoConverter);
    }
	/**
	 * 
	 */
	@Override
	public List<PostCodeDBCache> findByPartialPostCode(String postcode) 
	{
		
		Query query = new Query();
		StringBuilder strBuilder = new StringBuilder();
		strBuilder = strBuilder.append("^").append(postcode);
		query.addCriteria(Criteria.where("postcode").regex(strBuilder.toString()));
		query.with(new Sort(Sort.Direction.ASC, "postcode"));
		List<PostCodeDBCache> aList = mongo.find(query,PostCodeDBCache.class);
		return aList;
	}

	@Override
	public boolean deleteObject(String postcode) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("postcode").is(postcode));
		return mongo.remove(query, PostCodeDBCache.class).wasAcknowledged();
	}
	
}

	

	
	


	

