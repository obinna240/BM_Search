package com.pcg;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pcg.objects.PostCodeBuilder;

//delete after
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//Test methods are run in ascending order
//delete after
@RunWith(SpringRunner.class)
@SpringBootTest
public class PcgBirminghamApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	/**
	@Test
	public void testPostCodeBuilder()
	{
		PostCodeBuilder pcb =  new PostCodeBuilder("SL6 5BW");
		
	}
	*/
	
	@BeforeClass
	public static void connectToDB()
	{
		//connect to solr;
	}
	
	@AfterClass
	public static void disconnectFromDB()
	{
		//Disconnect from Solr;
	}
	public static void main(String[] args) {
	    Result result = JUnitCore.runClasses(PcgBirminghamApplicationTests.class);
	    for (Failure failure : result.getFailures()) {
	      System.out.println(failure.toString());
	    }
	    
	}
}
