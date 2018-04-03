package com.pcg.utils;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.pcg.objects.PostCodeBuilder;

/**
 * 
 * @author oonyimadu
 *
 */
@Component
public class SolrQUtils 
{
	@Autowired PostCodeBuilder postCodeBuilder;
	@Value("${defaultDistanceInKm}")
	String defaultDistanceInKm;
	
	@Value("${sfield}")
	String sfield;
	
	@Value("${generalQuery}")
	private String generalQuery;
	
	@Value("${stringQuerySolrField}")
	private String stringQuerySolrField;
	
	@Value("${solrUrl}")
	private String solrUrl;
	

	@Value("${field1}")
	private String age;//age_ms_s
	

	@Value("${field2}")
	private String condition;//conditions_ms_s
	
	@Value("${field3}")
	private String partialPc;//partialPostCodesOfferedIn_ms_s
		
	@Value("${field4}")
	private String nationlLs;
	
	

	@Value("${field5}")
	private String category;
	/**
	 * Appends the search term query to the query object
	 * @param searchString
	 * @param query
	 * @return SolrQuery
	 */
	public SolrQuery getStringQuery(String searchString, SolrQuery query)
	{
		if(StringUtils.isBlank(searchString))
		{
			query.setQuery(generalQuery);
		}
		else
		{
			searchString= StringUtils.normalizeSpace(searchString);
			
			searchString = Cleaner.unicodeEscape(Cleaner.replaceAll(searchString));
			StringBuffer sbf = new StringBuffer();
			query.setQuery(sbf.append(stringQuerySolrField).append(":").append(searchString).toString());
		}
		return query;
	}
	
	/**
	 * Appends nationalListing value to the query
	 * Also assumes that user enters true, t, 1, X, T or x
	 * @param nationalListing
	 * @param query
	 * @return
	 */
	public String getNationalListing(String nationalListing)
	{
		String retVal = null;
		if(StringUtils.isNotBlank(nationalListing))
		{
			nationalListing = StringUtils.normalizeSpace(nationalListing);
			String[] arrVal = {"True","T","1","X"}; //legal values of true 
			if(StringUtils.equalsAnyIgnoreCase(nationalListing, arrVal))
			{
				
				
				retVal = "true";
			}
		}
		return retVal;
	}
	
	/**
	 * Does a postcode check and appends the postcode object to the search query
	 * @param postcode
	 * @param query
	 * @param radius
	 * @return SolrQuery
	 */
	public SolrQuery checkPostcode(String postcode, SolrQuery query, String radius)
	{
		
		if(StringUtils.isNotBlank(postcode))
		{
			//PostCodeBuilder postCodeBuilder = new PostCodeBuilder();
			postCodeBuilder.init(StringUtils.normalizeSpace(postcode));
			
			Map<String,Double> lonlat = postCodeBuilder.getLongLat(); 
			if(MapUtils.isNotEmpty(lonlat))
			{
				Double longitude = lonlat.get("longitude");
				Double latitude = lonlat.get("latitude");
				if(longitude!=null && latitude!=null)
				{
					String  longString = longitude!=null?longitude.toString():"";
					String latString = latitude!=null?latitude.toString():"";
					StringBuilder sb = new StringBuilder();
					String coordinates = sb.append(latString).append(",").append(longString).toString();
					query.setParam("pt", coordinates);
					query.setParam("sort", "geodist() asc");
					query.setParam("fq", "{!geofilt}");
					
					query = getRadiusQuery(radius, query);
					query.setParam("sfield",sfield);
					query.setParam("fl","*,distance:geodist()");
					//query.setRequestHandler(qt);
				}
			}
		}
		return query;
	}
	
	/**
	 * Returns the solrQuery with filters appended
	 * @param filters
	 * @param newfilters
	 * @param query
	 * @return SolrQuery
	 */
	public SolrQuery appendFilters(List<String> filters, List<String> newfilters, SolrQuery query)
	{
		if(CollectionUtils.isNotEmpty(filters))
		{
			String filteredStrings = Cleaner.createFilterParam(filters);
			//query.setParam("fq", filteredStrings);
			newfilters .add(filteredStrings);
			
		}
		if(CollectionUtils.isNotEmpty(newfilters))
		{
			String[] arrFQ = new String[newfilters.size()];
			arrFQ = newfilters.toArray(arrFQ);
			query.setFilterQueries(arrFQ);
		}
		return query;
	}
	
	/**
	 * Creates a new Solr Client
	 * @return SolrClient 
	 */
	public SolrClient returnSolrClient()
	{
		
		SolrClient server = new HttpSolrClient.Builder(solrUrl).build();
		return server;
	}
	
	/**
	 * Appends the 'd' parameter to the solr radius query and throws an 
	 * exception if an illegal parameter type is entered
	 * @param radius
	 * @return SolrQuery
	 */
	private SolrQuery getRadiusQuery(String radius, SolrQuery query)
	{
			if(StringUtils.isBlank(radius))
			{
				query.setParam("d", defaultDistanceInKm);
			}
			else
			{
				radius = StringUtils.normalizeSpace(radius);
				if(NumberUtils.isCreatable(radius))
				{
					Double dkm = Km2Miles.convertMiToKm(NumberUtils.createDouble(radius));
					query.setParam("d", dkm.toString());
				}
				else 
				{
					throw new IllegalArgumentException("Radius cannot be alphabetic, it must be a number or decimal");
				}
			}
			
					
		return query;
	}
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getPartialPc() {
		return partialPc;
	}

	public String getNationlLs() {
		return nationlLs;
	}

	public void setNationlLs(String nationlLs) {
		this.nationlLs = nationlLs;
	}

	public void setPartialPc(String partialPc) {
		this.partialPc = partialPc;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public static void main(String[] args)
	{
		String radius = StringUtils.normalizeSpace("45.40092");
		
		if(NumberUtils.isCreatable(radius))
		{
			Double doub = NumberUtils.createDouble(radius);
			Number num = NumberUtils.createNumber(radius);
			System.out.println(doub);
			System.out.println(num);
			//throw new IllegalArgumentException("Radius cannot be alphanumeric, it must be a number or decimal");
		}
		else if(StringUtils.isAlpha(radius))
		{
			throw new IllegalArgumentException("Radius cannot be alphabetic, it must be a number or decimal");
		}
		else if(StringUtils.isNumeric(radius))
		{
			Double dkm = Km2Miles.convertMiToKm(Double.parseDouble(radius));
			
		}
		
	}
}



