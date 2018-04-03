package com.pcg.db;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Component
public class PostCodeDBCacheConverter implements Converter<PostCodeDBCache, DBObject>{

	@Override
	public DBObject convert(PostCodeDBCache source) {
		DBObject dbObject = new BasicDBObject();
		dbObject.put("postcode", source.getPostcode());
		dbObject.put("latitude", source.getLatitude());
		dbObject.put("longitude", source.getLongitude());
		dbObject.put("latLong", source.getLatLong());
       
        
        //dbObject.removeField("_class");
        return dbObject;
	}

}


