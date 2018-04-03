package com.pcg.exceptions;

public class PostCodeFormatException extends Exception
{
	private String postcode;
	private final String WIKI = "https://en.wikipedia.org/wiki/Postcodes_in_the_United_Kingdom#Validation"; 
	
	public PostCodeFormatException(String postcode)
	{
	   this.postcode = postcode;
	}   
	
	public String getPostCode()
	{
	    return postcode;
	}   
	
	public String getMessage()
	{
		return "The postcode entered is invalid. Please see regex "+WIKI+ " -- to enter a valid UK Postcode";
	}
}
