package com.pcg.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import com.pcg.db.PostCodeDBCache;

import java.util.List;


/**
 * 
 * @author oonyimadu
 *
 */
public interface PostCodeDBCacheRepository extends MongoRepository<PostCodeDBCache, String>, PostCodeDBCacheOperations
{
	List<PostCodeDBCache> findByPostcodeIgnoringCase(String postcode);
	List<PostCodeDBCache> findByLatLong(String latLong);
	
}

