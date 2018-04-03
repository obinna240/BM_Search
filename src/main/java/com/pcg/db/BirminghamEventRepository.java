package com.pcg.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.pcg.indexObjects.BirminghamIndexer;

@Repository
public class BirminghamEventRepository {
	@Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BirminghamIndexer birminghamIndexer;
    @Value("${storedProc}")
	private String storedProc;
    @Value("${resultSet}")
    private String resultSet;
   
    /**
     * 
     * @return
     */
	public Optional<Map<String, Object>> indexAll()
	{
		final long start = System.nanoTime();
		
		Map<String, Object> outcomeOfIndex = new HashMap<String,Object>();
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(storedProc);
		Map<String,Object> retVal = jdbcCall.execute();
		
		ArrayList<Map<String,Object>> items = (ArrayList<Map<String,Object>>) retVal.get(resultSet);//("#result-set-1"); 
		Integer numberOfDocumentsIndexed = 0;
		for(Map<String,Object> object:items)
		{
							
				Integer totalCount = birminghamIndexer.indexFromDB((String)object.get("eventId"),(String)object.get("eventName"), 
						(String)object.get("description"),(String)object.get("address"),
						(String)object.get("postcode"),(String)object.get("contactPhone"),(String)object.get("contactEmail"),
						(String)object.get("contactName"),(String)object.get("contactJobTitle"),(String)object.get("age_ms_s"), 
						(String)object.get("conditions_ms_s"),
						(String)object.get("partialPostCodesOfferedIn_ms_s"),
						(String)object.get("whoFor_string_s"),(String)object.get("whenAvailable_string_n"),
						(String)object.get("venuePostCode_string_ns"), (String)object.get("venueAddress_string_ns"),(String)object.get("facebook_string_ns"),
						(String)object.get("twitter_string_ns"),
						(String)object.get("lastUpdated_string_ns"), 
						(String)object.get("nationalListing"),
						(String)object.get("category"),
						(String)object.get("BirminghamEventType"));
				
				numberOfDocumentsIndexed = numberOfDocumentsIndexed+totalCount;
			
		}
		
		final long end = System.nanoTime();
		
		
		outcomeOfIndex.put("Duration of Indexing", "Took: " + ((end - start) / 1000000) + "ms");
		outcomeOfIndex.put("Total Number of Documents Indexed", numberOfDocumentsIndexed);
		//return new ArrayList();
		return Optional.of(outcomeOfIndex);
	}
}

