package com.pcg.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

/**
 * 
 * @author oonyimadu
 *
 */
public class Km2Miles 
{
	/**
	 * 
	 * @param kilometers
	 * @return double
	 */
	public static double convertKmToMi(double kilometers) {
	   
	    double miles = kilometers * 0.621;
	    miles = round(miles,2);
	    return miles;
	}
	
	/**
	 * 
	 * @param value
	 * @param places
	 * @return double
	 */
	public static double round(double value, int places) 
	{
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	/**
	 * 
	 * @param miles
	 * @return double
	 */
	public static double convertMiToKm(double miles) {
		   
	    double kilometres = miles / 0.621;
	    kilometres = round(kilometres,2);
	    return kilometres;
	}
	
	

}
