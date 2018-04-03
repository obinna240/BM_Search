package com.pcg.db;

import java.util.List;

public interface PostCodeDBCacheOperations {
	public List<PostCodeDBCache> findByPartialPostCode(String pc); 
	public boolean deleteObject(String postcode);
}
