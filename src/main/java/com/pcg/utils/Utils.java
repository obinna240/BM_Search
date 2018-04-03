package com.pcg.utils;

import java.util.ArrayList;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrDocument;

import com.pcg.objects.BM_EventObject;
import com.pcg.objects.IndexObject;

/**
 * 
 * @author oonyimadu
 *
 */
public class Utils {
	
	public static IndexObject doSearchResultBean(SolrDocument solrDoc)
	{
		BM_EventObject bean = new BM_EventObject();
		if(solrDoc != null)
		{
			String eventId = (String) solrDoc.getFieldValue("eventId");
			bean.setEventId(eventId);
							
			String eventName = (String) solrDoc.getFieldValue("eventName");
			eventName = StringUtils.isNotBlank(eventName)?eventName:"";
			bean.setEventName(eventName);
			  
			String address = (String) solrDoc.getFieldValue("address");
			address = StringUtils.isNotBlank(address)?address:"";
			bean.setAddress(address);
			
			String pc = (String) solrDoc.getFieldValue("postcode");
			pc = StringUtils.isNotBlank(pc)?pc:"";
			bean.setPostcode(pc);
			
			String geoLocation = (String) solrDoc.getFieldValue("geoLocation_string_s");
			geoLocation = StringUtils.isNotBlank(geoLocation)?geoLocation:"";
			bean.setGeoLocation_string_s(geoLocation);
			
			String description = (String) solrDoc.getFieldValue("description");
			description = StringUtils.isNotBlank(description)?description:"";
			bean.setDescription(description);
			
			String latitude = (String) solrDoc.getFieldValue("latitude_lat_s");
			latitude = StringUtils.isNotBlank(latitude)?latitude:"";
			bean.setLatitude_lat_s(latitude);
			  
			String longitude = (String) solrDoc.getFieldValue("longitude_lon_s");
			longitude = StringUtils.isNotBlank(longitude)?longitude:"";
			bean.setLongitude_lon_s(longitude);
			  
			String cphone = (String) solrDoc.getFieldValue("contactPhone");
			cphone = StringUtils.isNotBlank(cphone)?cphone:"";
			bean.setContactPhone(cphone);
			
			String bmEventObject = (String)solrDoc.getFieldValue("BirminghamEventType");
			bmEventObject = StringUtils.isNotBlank(bmEventObject)?bmEventObject:"";
			bean.setBirminghamEventType(bmEventObject);
			
			String cemail = (String) solrDoc.getFieldValue("contactEmail");
			cemail = StringUtils.isNotBlank(cemail)?cemail:"";
			bean.setContactEmail(cemail);
			
			String cname = (String) solrDoc.getFieldValue("contactName");
			cname = StringUtils.isNotBlank(cname)?cname:"";
			bean.setContactName(cname);
			  
			String jtitle = (String) solrDoc.getFieldValue("contactJobTitle");
			jtitle = StringUtils.isNotBlank(jtitle)?jtitle:"";
			bean.setContactJobTitle(jtitle);
			
			Object ageRange = solrDoc.getFieldValue("age_ms_s");
			List<String> ar = checkObjAsList(ageRange);
			if(CollectionUtils.isNotEmpty(ar))
			{
				bean.setAge_ms_s(ar);
			}
			
			Object cate = solrDoc.getFieldValue("category");
			List<String> _catObjs = checkObjAsList(cate);
			if(CollectionUtils.isNotEmpty(_catObjs))
			{
				bean.setCategory(_catObjs);
			}
			
			
			
			
			Object cond = solrDoc.getFieldValue("conditions_ms_s");
			List<String> con = checkObjAsList(cond);
			if(CollectionUtils.isNotEmpty(con))
			{
				bean.setConditions_ms_s(con);
			}
			
			Object partialPcOffered = solrDoc.getFieldValue("partialPostCodesOfferedIn_ms_s");
			List<String> ppc = checkObjAsList(partialPcOffered);
			if(CollectionUtils.isNotEmpty(ppc))
			{
				bean.setPartialPostCodesOfferedIn_ms_s(ppc);
			}
			
			String misc = (String) solrDoc.getFieldValue("misc_string_s");
			misc = StringUtils.isNotBlank(misc)?misc:"";
			bean.setMisc_string_s(misc);
			
			Object whofor = solrDoc.getFieldValue("whoFor_string_s"); //should be multiple valued, replace this
			List<String> wf = checkObjAsList(whofor);
			if(CollectionUtils.isNotEmpty(wf))
			{
				bean.setWhoFor_ms_s(wf);
			}
			  
			String wav = (String) solrDoc.getFieldValue("whenAvailable_string_ns");
			wav = StringUtils.isNotBlank(wav)?wav:"";
			bean.setWhenAvailable_string_ns(wav);
			
			String vad = (String) solrDoc.getFieldValue("venueAddress_string_ns");
			vad = StringUtils.isNotBlank(vad)?vad:"";
			bean.setVenueAddress_string_ns(vad);
			
			String vpc = (String) solrDoc.getFieldValue("venuePostCode_string_ns");
			vpc = StringUtils.isNotBlank(vpc)?vpc:"";
			bean.setVenuePostCode_string_ns(vpc);
			
			String fb = (String) solrDoc.getFieldValue("facebook_string_ns");
			fb = StringUtils.isNotBlank(fb)?fb:"";
			bean.setFacebook_string_ns(fb); 
			
			String twt = (String) solrDoc.getFieldValue("twitter_string_ns");
			twt = StringUtils.isNotBlank(twt)?twt:"";
			bean.setTwitter_string_ns(twt); 
			  
			String lns = (String) solrDoc.getFieldValue("lastUpdated_string_ns");
			lns = StringUtils.isNotBlank(lns)?lns:"";
			bean.setLastUpdated_string_ns(lns);
			
			String orga = (String) solrDoc.getFieldValue("organizationAddress");
			orga = StringUtils.isNotBlank(orga)?orga:"";
			bean.setOrganizationAddress(orga);
			
			String orpc= (String) solrDoc.getFieldValue("organizationPostcode");
			orpc = StringUtils.isNotBlank(orpc)?orpc:"";
			bean.setOrganizationPostcode(orpc);
			
			//sets the distance from the radius entered
			Double distance = (Double) solrDoc.getFieldValue("distance");
			bean.setDistance(distance);
		}
		return bean;
	}
	
	/**
	 * 
	 * @param obj
	 * @return List<String>
	 */
	public static List<String> checkObjAsList(Object obj)
	{
		List<String> val = null;
		if(obj!=null)
		{
			if(obj instanceof List)
			{
				val = (List<String>)obj;
			}
			else if(obj instanceof String)
			{
				val = new ArrayList<String>();
				val.add((String)obj);
			}
		}
		return val;
	}
	
	

}
