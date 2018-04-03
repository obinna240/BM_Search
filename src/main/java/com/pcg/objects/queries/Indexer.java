package com.pcg.objects.queries;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;


/**
 * This is a test class
 * Test indexer.
 * Use Factory method to create a new indexer
 * See com.pcg.factory
 * @author oonyimadu
 *
 */
public class Indexer 
{
	final static String SOLRINDEX = "http://localhost:8983/solr/birmingham";
	
	public static void index(String csvLocation) throws IOException, SolrServerException
	{
		SolrClient solr = new HttpSolrClient.Builder(SOLRINDEX).build();
		CSVParser parser = CSVParser.parse(new File(csvLocation), Charset.defaultCharset(), CSVFormat.EXCEL);
		List<CSVRecord> records = parser.getRecords();
		Integer recordNumber = 2;
		for (CSVRecord csvRecord : records)
		{
			
			Long recNum = csvRecord.getRecordNumber();
			int recNums = recNum.intValue();
			if(recNums>=recordNumber)
			{
			
			SolrInputDocument sDoc = new SolrInputDocument();
			
			
			sDoc.addField("id", csvRecord.get(0));
			sDoc.addField("eventId",csvRecord.get(1));
			sDoc.addField("eventName",csvRecord.get(2));
			sDoc.addField("description",csvRecord.get(3));
			sDoc.addField("address",csvRecord.get(4));
			sDoc.addField("postcode",csvRecord.get(5));
			sDoc.addField("latitude_lat_s",csvRecord.get(6));
			sDoc.addField("longitude_lon_s",csvRecord.get(7));
			sDoc.addField("geoLocation_string_s",csvRecord.get(8));
			sDoc.addField("postcode_start",csvRecord.get(9));
			sDoc.addField("postcode_end",csvRecord.get(10));
			sDoc.addField("contactPhone",csvRecord.get(11));
			sDoc.addField("contactEmail",csvRecord.get(12));
			sDoc.addField("contactName",csvRecord.get(13));
			sDoc.addField("contactJobTitle",csvRecord.get(14));
			
			String age = csvRecord.get(15);
			if(StringUtils.isNotBlank(age))
			{
				age = StringUtils.normalizeSpace(age);
				String[] split = age.split(",");
				for(String str:split)
				{
					sDoc.addField("age_ms_s",str);
				}
				
			}
			
			age = csvRecord.get(16);
			if(StringUtils.isNotBlank(age))
			{
				age = StringUtils.normalizeSpace(age);
				String[] split = age.split(",");
				for(String str:split)
				{
					sDoc.addField("conditions_ms_s",str);
				}
				
			}
			
			age = csvRecord.get(17);
			if(StringUtils.isNotBlank(age))
			{
				age = StringUtils.normalizeSpace(age);
				String[] split = age.split(",");
				for(String str:split)
				{
					sDoc.addField("partialPostCodesOfferedIn_ms_s",str);
				}
				
			}
			
			String strs = csvRecord.get(18);
			sDoc.addField("misc_string_s",strs);
			
			age = csvRecord.get(19);
			if(StringUtils.isNotBlank(age))
			{
				age = StringUtils.normalizeSpace(age);
				String[] split = age.split(",");
				for(String str:split)
				{
					sDoc.addField("whoFor_string_s",str);
				}
				
			}
			
			
			sDoc.addField("whenAvailable_string_ns",csvRecord.get(20));
			sDoc.addField("venuePostCode_string_ns",csvRecord.get(21));
			sDoc.addField("facebook_string_ns",csvRecord.get(22));
			sDoc.addField("twitter_string_ns",csvRecord.get(23));
			sDoc.addField("lastUpdated_string_ns",csvRecord.get(24));
			
			solr.add(sDoc);
			
			solr.commit();
			}
		}
	}
	
	public static void main(String[] args) throws IOException, SolrServerException
	{
		index("C:/Users/oonyimadu/Documents/Projects/Birmingham/Fake_data2.csv");
	}
}





/**
<field name="id" type="string" indexed="true" stored="true" required="true" multiValued="false" />
<!-- doc values are enabled by default for primitive types such as long so we don't index the version field  -->
<field name="_version_" type="long" indexed="false" stored="false"/>
<field name="_root_" type="string" indexed="true" stored="false" docValues="false" />
<field name="_text_" type="text_general" indexed="true" stored="false" multiValued="true"/>

   
<field name="eventId" type="string" multiValued="false" indexed="true" stored="true"/>
<field name="eventName" type="string" multiValued="false" indexed="true" stored="true"/>
<field name="description" type="text_general" multiValued="false" indexed="true" stored="true"/>
<field name="description1" type="text_en" multiValued="false" indexed="true" stored="true"/>
<field name="description2" type="text_en_splitting" multiValued="false" indexed="true" stored="true"/>
<field name="description3" type="text_en_splitting_tight" multiValued="false" indexed="true" stored="true"/>
<field name="address" type="text_general" multiValued="false" indexed="true" stored="true"/>
<field name="postcode" type="string" multiValued="false" indexed="true" stored="true"/>
<field name="latitude_lat_s" type="string" multiValued="false" indexed="true" required="false" stored="true"/>
<field name="longitude_lon_s" type="string" docValues="false" multiValued="false" indexed="true" required="false" stored="true"/>
<field name="geoLocation_string_s" type="location" indexed="true" required="false" stored="true"/>
<field name="postcode_start" type="string" multiValued="false" indexed="true" stored="true"/>
<field name="postcode_end" type="string" multiValued="false" indexed="true" stored="true"/>
<field name="contactPhone" type="string" multiValued="false" indexed="false" stored="true"/>
<field name="contactEmail" type="string" multiValued="false" indexed="false" stored="true"/>
<field name="contactName" type="string" multiValued="false" indexed="false" stored="true"/>
<field name="contactJobTitle" type="string" multiValued="false" indexed="false" stored="true"/>
<field name="age_ms_s" type="string" multiValued="true" indexed="true" stored="true"/>
<field name="conditions_ms_s" type="string" multiValued="true" indexed="true" stored="true"/>
<field name="partialPostCodesOfferedIn_ms_s" type="string" multiValued="true" indexed="true" stored="true"/>
<field name="misc_string_s" type="string" multiValued="false" indexed="true" stored="true"/>
<field name="whoFor_string_s" type="string" multiValued="true" indexed="true" stored="true"/>
<field name="whenAvailable_string_ns" type="string" multiValued="false" indexed="false" stored="true"/>
<field name="venueAddress_string_ns" type="string" multiValued="false" indexed="false" stored="true"/>
<field name="venuePostCode_string_ns" type="string" multiValued="false" indexed="false" stored="true"/>
<field name="facebook_string_ns" type="string" multiValued="false" indexed="false" stored="true"/>
<field name="twitter_string_ns" type="string" multiValued="false" indexed="false" stored="true"/>
<field name="lastUpdated_string_ns" type="string" multiValued="false" indexed="false" stored="true"/>
<field name="organizationAddress" type="string" multiValued="false" indexed="false" stored="true"/>
<field name="organizationPostcode" type="string" multiValued="false" indexed="false" stored="true"/>
<field name="searchFields" type="text_general"  indexed="true" required="false" stored="true"/>
<field name="searchFields2" type="text_general" indexed="true" required="false" stored="true"/>
*/