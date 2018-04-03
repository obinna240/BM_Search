package com.pcg;


import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.pcg.db.PostCodeDBCache;
import com.pcg.db.PostCodeDBCacheRepository;
import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class PostCodeDBCacheRepositoryTest {
	// @Autowired
	 private PostCodeDBCacheRepository mongoRepository;
	 
	//  @Before
	  public void setUp() throws Exception 
	  {
		  PostCodeDBCache object= new PostCodeDBCache();
		  object.setLatitude(5.00000);
		  object.setLongitude(-1.00000);
		  object.setPostcode("XX XX");

	      this.mongoRepository.save(object);
	      
	        assertNotNull(object.getPostcode());
	
	   }
	 
	// @Test
	 public void testFetchData(){
	        /*Test data retrieval*/
	    	List<PostCodeDBCache> object = mongoRepository.findByPostcodeIgnoringCase("XX XX");
	        assertNotNull(object.get(0));
	        assertEquals(new Double(5.00000), object.get(0).getLatitude());
	        
	        
	    }
	 
	 // @Test
	    public void testDataUpdate(){
	        /*Test update*/
	    	List<PostCodeDBCache> object = mongoRepository.findByPostcodeIgnoringCase("XX XX");
	    	object.get(0).setPostcode("XX XY");
	    	mongoRepository.save(object);
	    	object = mongoRepository.findByPostcodeIgnoringCase("XX XY");
	        assertNotNull(object.get(0));
	        assertEquals("XX XY", object.get(0).getPostcode());
	    }
	 
	 // @After
	    public void tearDown() throws Exception {
	      this.mongoRepository.deleteObject("XX XY");
	      this.mongoRepository.deleteObject("XX XX");
	    }
	 
	
}


 

   
 
 
  