package com.pcg.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;

/**
 * Test the Cleaner class
 * @author oonyimadu
 *
 */
public class CleanerTest 
{
	@Test
	public void checkStringIsSplitByUnderscore()
	{
		
		String underScoredString = "separate_this_string";
		Integer expectedLength = 3;
		assertEquals(expectedLength,(Integer)Cleaner.splitUnderscore(underScoredString).length);
	}
	

	@Test
	public void checkSplitOnEmptyStringReturnsNull()
	{
		
		String underScoredString = " ";
	
		assertNull(Cleaner.splitUnderscore(underScoredString));
	}
	
	
	@Test
	public void checkSplitOnNullReturnsNull()
	{
		
		String underScoredString = null;
		
		assertNull(Cleaner.splitUnderscore(underScoredString));
	}
	
	@Test
	public void checkIfListIsMerged()
	{
		String[] array = {"x","y","z"};
		String filterType= "test";
		List<String> expectedList = Arrays.asList(filterType+":x", filterType+":y", filterType+":z");
		assertThat(Cleaner.mergeListForFiler(filterType, Arrays.asList(array)), is(expectedList));
			
	}
	
	@Test
	public void checkIfReturnedListIsNull()
	{
		String[] array = {};
		String filterType= "test";
		assertThat(Cleaner.mergeListForFiler(filterType, Arrays.asList(array)), IsEmptyCollection.empty());
			
	}
	
	@Test
	public void checkFilterParamIsCreated()
	{
		String[] array = {"x","y","z"};
		List<String> expectedList = Arrays.asList(array);
		String filterParam = array[0]+" AND "+array[1]+" AND "+array[2];
		assertEquals(filterParam, Cleaner.createFilterParam(expectedList));
	}
	
	@Test
	public void checkFilterParamIsCreatedWhenOneElementOfTheArrayIsBlankOrEmpty()
	{
		String[] array = {"x","y",""};
		List<String> expectedList = Arrays.asList(array);
		String filterParam = array[0]+" AND "+array[1]+" AND "+array[2];
		assertEquals(filterParam, Cleaner.createFilterParam(expectedList));
	}
	
	@Test
	public void checkFilterParamReturnsNullWhenListIsBlankOrEmpty()
	{
		
		assertNull(Cleaner.createFilterParam(new ArrayList<String>()));
	}
	
	@Test
	public void checkReplaceAllPlusSignIsReplacedWithAnEscapedCharacter()
	{
		String testString = "A+B+C++D";
		assertEquals("A\\+B\\+C\\+\\+D",Cleaner.replaceAll(testString));
	}
	
	@Test
	public void checkReplaceAllMinusSignIsReplacedWithAnEscapedCharacter()
	{
		String testString = "A-B-C--D";
		assertEquals("A\\-B\\-C\\-\\-D",Cleaner.replaceAll(testString));
	}
	
	@Test
	public void checkReplaceAllReturnsEmptyStringGivenAnEmptyString()
	{
		String testString = "";
		assertEquals("",Cleaner.replaceAll(testString));
	}
	
	@Test
	public void checkReplaceAllReturnsNullGivenANull()
	{
		String testString = null;
		assertEquals(null,Cleaner.replaceAll(testString));
	}
	

}




