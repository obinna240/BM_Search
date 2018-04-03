package com.pcg.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pcg.controller.AppController;
import com.pcg.exceptions.PostCodeFormatException;

public class PostCodeValidator {
	private static String regex = "^([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z]))))\\s*[0-9][A-Za-z]{2})$";
	private static Pattern pattern;
	private static final Logger logger = LogManager.getLogger(PostCodeValidator.class);
	
	static{
		pattern = Pattern.compile(regex);
	}
	
	public static boolean validate(String postcode) //throws PostCodeFormatException //throws PostCodeFormatException
	{
		boolean isPostCode = true;
		Matcher matcher = pattern.matcher(postcode.toUpperCase());
		if(!matcher.matches())
		{
			//System.out.println("This is not a valid postcode");
			logger.info("**************************************************************");
			logger.warn(postcode+ " is an invalid postcode ");
			logger.info("**************************************************************");
			isPostCode = false;
			//throw new PostCodeFormatException(postcode);
		}
		return isPostCode;
	}
	
	/**
	public static void main(String[] args)
	{
		PostCodeValidator pc = new PostCodeValidator();
		try
		{
			PostCodeValidator.validate(" .");
		} catch (PostCodeFormatException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	*/
}

