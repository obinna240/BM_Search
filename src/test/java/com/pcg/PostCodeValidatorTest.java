package com.pcg;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.pcg.exceptions.PostCodeFormatException;
import com.pcg.utils.PostCodeValidator;

/**
 * 
 * @author oonyimadu
 * Class to test that postcode is valid
 */
@RunWith(Parameterized.class)
public class PostCodeValidatorTest 
{
	private String postcode;
	private Boolean expectedResult;
	
	
	/**
	 * 
	 * @param postcode
	 * @param expectedResult
	 */
	public PostCodeValidatorTest(String postcode, Boolean expectedResult) {
	      this.postcode = postcode;
	      this.expectedResult = expectedResult;
	}
	
	@Parameterized.Parameters
	public static Collection postcodes() {
	      return Arrays.asList(new Object[][] {
	         { "SL6 5BW", true },
	       { "DBBBB 888UJU", false },
	         { "EC4N 1hp", true },
	        { "abc. 34HG", false },
	        { " ", false },
	         { "B12 0AH", true }
	      });
	 }
	

	 @Test
	 public void checkPostCodeIsValid() throws PostCodeFormatException 
	 {
	    
	      
		assertEquals(expectedResult, PostCodeValidator.validate(postcode));
		
			
	     
	      
	 }
	 
	 
}

