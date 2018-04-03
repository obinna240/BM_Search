package com.pcg.indexObjects;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.pcg.db.PostCodeDBCache;
import com.pcg.db.PostCodeDBCacheRepository;
import com.pcg.interfaces.IndexerInterface;
import com.pcg.objects.PostCodeBuilder;
import com.pcg.utils.PostCodeValidator;

@Component
public class BirminghamIndexer implements IndexerInterface{
	
	@Value("${solrUrl}")
	private String solrUrl;
	private SolrClient solr;
	Integer responseType;
	@Autowired
	private PostCodeBuilder postCodeBuilder;
	
	@Autowired
	private PostCodeDBCacheRepository postCodeCache;
	private static final Logger logger = LogManager.getLogger(BirminghamIndexer.class);
	
	/**
	 * 
	 */
	public void init()
	{
		if(StringUtils.isBlank(solrUrl))
		{
			solrUrl = "http://localhost:8983/solr/birmingham";
		}
		solr = new HttpSolrClient.Builder(solrUrl).build();
		
	}
	
	@Override
	public void indexFromFile(String fileName) {
		// TODO Auto-generated method stub
		init();
		try {
			index(fileName);
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(solr!=null)
			{
				try {
					solr.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * Inserts into our database cache
	 * @param postcode
	 * @param latlong
	 */
	private void insertIntoMongoDB(String postcode, Map<String,Double> latlong)
	{
		if(MapUtils.isNotEmpty(latlong))
		{
			PostCodeDBCache cacheObject = new PostCodeDBCache();
			Double lat = latlong.get("latitude");
			Double lon = latlong.get("longitude");
			cacheObject.setLatitude(lat);
			cacheObject.setLongitude(lon);
			cacheObject.setPostcode(postcode);
			cacheObject.setLatLong(new StringBuffer().append(lat.toString()).append(",").append(lon.toString()).toString());
			postCodeCache.save(cacheObject);
		}
	}
	
	/**
	 * 
	 * @param postcode
	 * @return Map<String,Double> 
	 */
	private Map<String,Double> getLongLat(String postcode)
	{
		Map<String,Double> llat = null;
		List<PostCodeDBCache> cacheObject = postCodeCache.findByPostcodeIgnoringCase(postcode);
		if(CollectionUtils.isEmpty(cacheObject))
		{
			postCodeBuilder.init(postcode);
			llat = postCodeBuilder.getLongLat();
			
			if(MapUtils.isEmpty(llat))
			{
				//check using partial postcodes
				List<PostCodeDBCache> partialObject = postCodeCache.findByPostcodeIgnoringCase(getStartEndPC(postcode)[0]);
				
				if(CollectionUtils.isNotEmpty(partialObject))
				{
				
				//	System.out.println(postcode+" %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
					PostCodeDBCache firstPostCode = partialObject.get(0);
					llat = new HashMap<String, Double>();
					llat.put("longitude", firstPostCode.getLongitude());
					llat.put("latitude", firstPostCode.getLatitude());
				}
				//else
				//{
				//	System.out.println("£££££££££££££££££££££££££££££££££££££££££"+" ***********************************************");
				//	System.out.println(postcode);
				//	System.out.println("£££££££££££££££££££££££££££££££££££££££££"+" ***********************************************");
				//	System.exit(0);
				//}
			}
			else
			{
				insertIntoMongoDB(postcode, llat);
			}
		}
		else
		{
			PostCodeDBCache firstPostCode = cacheObject.get(0);
			llat = new HashMap<String, Double>();
			llat.put("longitude", firstPostCode.getLongitude());
			llat.put("latitude", firstPostCode.getLatitude());
					
		}
				
		return llat;
	}
	
	private String[] getStartEndPC(String postcode)
	{
		return postcode.split(" ");
		
	}
	
	/**
	 * 
	 * @param eventId
	 * @param eventName
	 * @param description
	 * @param address
	 * @param postcode
	 * @param contactPhone
	 * @param contactEmail
	 * @param contactName
	 * @param contactJobTitle
	 * @param age_ms_s
	 * @param conditions_ms_s
	 * @param partialPostCodesOfferedIn_ms_s
	 * @param whoFor_string_s
	 * @param whenAvailable_string_ns
	 * @param venuePostCode_string_ns
	 * @param facebook_string_ns
	 * @param twitter_string_ns
	 * @param lastUpdated_string_ns
	 * @param nationalListing
	 */
	public Integer indexFromDB(String eventId,String eventName, String description,String address,
			String postcode,String contactPhone, String contactEmail,
			String contactName, String contactJobTitle, String age_ms_s, String conditions_ms_s,
			String partialPostCodesOfferedIn_ms_s,String whoFor_string_s,String whenAvailable_string_ns,
			String venuePostCode_string_ns, String venueAddress_string_ns, String facebook_string_ns, String twitter_string_ns,
			String lastUpdated_string_ns, String nationalListing, String category, String BirminghamEventType)
		{
			// TODO Auto-generated method stub
			Integer countOfIndices = 0;
			init(); //we should get rid of this
			try{
			SolrInputDocument sDoc = new SolrInputDocument();
							  
			sDoc.addField("id", StringUtils.normalizeSpace(eventId));
			sDoc.addField("eventId",StringUtils.normalizeSpace(eventId));
			sDoc.addField("eventName",StringUtils.normalizeSpace(eventName));
			sDoc.addField("description",StringUtils.normalizeSpace(description));
			sDoc.addField("address",StringUtils.normalizeSpace(address));
			
			sDoc.addField("BirminghamEventType",StringUtils.normalizeSpace(BirminghamEventType));
			
			//include new field category
			if(StringUtils.isNotBlank(category))
			{
				category = StringUtils.normalizeSpace(category);
				String[] split = category.split("\\|");
				for(String str:split)
				{
					sDoc.addField("category",str);
				}
				
			}
			
			postcode = StringUtils.normalizeSpace(postcode);
			
			//System.out.println("$$$$ Index From DB $$$$$"+" derrived PC "+postcode);
			
			sDoc.addField("postcode",postcode);
			
			Map<String,Double> lolat = new HashMap<String,Double>();
			
			if(StringUtils.isBlank(venuePostCode_string_ns))
			{
				sDoc.addField("venuePostCode_string_ns",postcode);
				sDoc.addField("venueAddress_string_ns",StringUtils.normalizeSpace(address));
				if(StringUtils.isNotBlank(postcode))
				{
					lolat= getLongLat(postcode);
					String[] pc = getStartEndPC(postcode);
					if(pc.length==2){
					sDoc.addField("postcode_start",pc[0]);
					sDoc.addField("postcode_end",pc[1]);}
				}
			}
			else{
				venuePostCode_string_ns  = StringUtils.normalizeSpace(venuePostCode_string_ns);
				sDoc.addField("venuePostCode_string_ns",venuePostCode_string_ns);
				sDoc.addField("venueAddress_string_ns",StringUtils.normalizeSpace(venueAddress_string_ns));
				lolat= getLongLat(venuePostCode_string_ns);
			}
			
			
			if(MapUtils.isNotEmpty(lolat))
			{
				Double latitude = lolat.get("latitude");
				sDoc.addField("latitude_lat_s",latitude);
				Double longitude = lolat.get("longitude");
				sDoc.addField("longitude_lon_s",longitude);
				
				String  longString = longitude!=null?longitude.toString():"";
				String latString = latitude!=null?latitude.toString():"";
				StringBuilder sb = new StringBuilder();
				String coordinates = sb.append(latString).append(",").append(longString).toString();
				sDoc.addField("geoLocation_string_s",coordinates);
			}
		  
			contactPhone = StringUtils.isNotBlank(contactPhone)?StringUtils.normalizeSpace(contactPhone):"";
			sDoc.addField("contactPhone",contactPhone);
			contactEmail = StringUtils.isNotBlank(contactEmail)?StringUtils.normalizeSpace(contactEmail):"";
			sDoc.addField("contactEmail",contactEmail);
			contactName = StringUtils.isNotBlank(contactName)?StringUtils.normalizeSpace(contactName):"";
			sDoc.addField("contactName",contactName);
			contactJobTitle= StringUtils.isNotBlank(contactJobTitle)?StringUtils.normalizeSpace(contactJobTitle):"";
			sDoc.addField("contactJobTitle",contactJobTitle);
		
			if(StringUtils.isNotBlank(age_ms_s))
			{
				age_ms_s = StringUtils.normalizeSpace(age_ms_s);
				String[] split = age_ms_s.split("\\|");
				for(String str:split)
				{
					sDoc.addField("age_ms_s",str);
				}
				
			}
		
			
			if(StringUtils.isNotBlank(conditions_ms_s))
			{
				conditions_ms_s = StringUtils.normalizeSpace(conditions_ms_s);
				String[] split = conditions_ms_s.split("\\|");
				for(String str:split)
				{
					sDoc.addField("conditions_ms_s",str);
				}
				
			}
						
			if(StringUtils.isNotBlank(partialPostCodesOfferedIn_ms_s))
			{
				partialPostCodesOfferedIn_ms_s = StringUtils.normalizeSpace(partialPostCodesOfferedIn_ms_s);
				String[] split = partialPostCodesOfferedIn_ms_s.split("\\|");
				for(String str:split)
				{
					sDoc.addField("partialPostCodesOfferedIn_ms_s",str);
				}
				
			}
			
			whoFor_string_s= StringUtils.isNotBlank(whoFor_string_s)?StringUtils.normalizeSpace(whoFor_string_s):"";
			sDoc.addField("whoFor_string_s",whoFor_string_s);
			whenAvailable_string_ns= StringUtils.isNotBlank(whenAvailable_string_ns)?StringUtils.normalizeSpace(whenAvailable_string_ns):"";
			sDoc.addField("whenAvailable_string_ns",whenAvailable_string_ns);
			
			facebook_string_ns= StringUtils.isNotBlank(facebook_string_ns)?StringUtils.normalizeSpace(facebook_string_ns):"";
			sDoc.addField("facebook_string_ns",facebook_string_ns);
			
			twitter_string_ns= StringUtils.isNotBlank(twitter_string_ns)?StringUtils.normalizeSpace(twitter_string_ns):"";
			sDoc.addField("twitter_string_ns",twitter_string_ns);
			
			lastUpdated_string_ns= StringUtils.isNotBlank(lastUpdated_string_ns)?StringUtils.normalizeSpace(lastUpdated_string_ns):"";
			sDoc.addField("lastUpdated_string_ns",lastUpdated_string_ns);
			 
				
		
			if(StringUtils.isBlank(nationalListing)|| StringUtils.equals("0",nationalListing)||StringUtils.equalsIgnoreCase("false",nationalListing)||StringUtils.equalsIgnoreCase("no",nationalListing))	
			{
				sDoc.addField("nationalListing",false);
			}
			else if(StringUtils.isBlank(nationalListing)|| StringUtils.equals("1",nationalListing)||StringUtils.equalsIgnoreCase("true",nationalListing)||StringUtils.equalsIgnoreCase("yes",nationalListing))
			{
				sDoc.addField("nationalListing",true);
			}
		
			
			int addStatus = solr.add(sDoc).getStatus();
			if(addStatus == 0){
				logger.info("Successfully added to Index object with event id "+ eventId);
				logger.info("Preparing to commit to index object with event id "+ eventId);
			}
			
			int commitStatus = solr.commit().getStatus();
			
			
			if(commitStatus==0)
			{
				countOfIndices++;
				logger.info("Successfully committed object with event id "+ eventId );
				
			}
			else
			{
				logger.fatal(eventId +" was not successfully commited");
				//email
			}
		}
			
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(solr!=null)
			{
				try {
					solr.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		return countOfIndices;
	}

	@Override
	public Integer removeDocumentById(String id) {
		return delete(id);
	}
	
	
	@Override
	public Integer clearIndex() {
		return delete(null);
	}
	
	
	
	private int delete(String id)
	{
		init();
		try {
			if(StringUtils.isNotBlank(id))
			{
				responseType = solr.deleteById(id).getStatus();
				
			}
			else
			{
				responseType = solr.deleteByQuery("*:*").getStatus();
				
			}
			solr.commit();
		}
		catch (SolrServerException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		finally{
			if(solr!=null)
			{
				try {
					solr.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		return responseType;
	}
	/**
	 * 
	 * @param csvLocation
	 * @throws IOException
	 * @throws SolrServerException
	 */
	private void index(String csvLocation) throws IOException, SolrServerException
	{
		init();
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

}
