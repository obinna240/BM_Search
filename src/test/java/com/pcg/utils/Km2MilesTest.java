package com.pcg.utils;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


public class Km2MilesTest {
	
	
	
	@Test
	public void testConvertKmToMi_checkThatEstimatedMileageIsRounded() 
	{
			
		assertEquals(25.46, Km2Miles.convertKmToMi(40.996),0.00);
	}
	
	@Test
	public void checkKmIsConvertedToZeroMilesWhenkmIsZero()
	{
		assertEquals(0, Km2Miles.convertKmToMi(0),0.00);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void checkThatExceptionIsThrownWhenPlacessIsLessThanZero()
	{
		double sampleValue = 4.1;
		int samplePlaces = -1;
		Km2Miles.round(sampleValue, samplePlaces);
		 
	}
	
	@Test(expected=NullPointerException.class)
	public void checkThatNullPointerExceptionIsReturnedIfADoubleIsNull()
	{
		double sampleValue = new Double(null);
		int samplePlaces = -1;
		Km2Miles.round(sampleValue, samplePlaces);
		 
	}
	
	@Test
	public void testConvertMiToKm_checkThatEstimatedMileageIsRoundedTo2DPlaces() 
	{
			
		assertEquals(41.00, Km2Miles.convertMiToKm(25.46),0.00);//40.996
	}
}

/**
 * 
 

public static double round(double value, int places) 
{
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
}


public static double convertMiToKm(double miles) {
	   
    double kilometres = miles / 0.621;
    kilometres = round(kilometres,2);
    return kilometres;
}

*/