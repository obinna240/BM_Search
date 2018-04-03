package com.pcg.objects.queries;

/**
 * QueryBean used in processing results
 * @author oonyimadu
 *
 */
public class QueryBean 
{

	
	private String searchString_ss;
	
	private String postcode_pc;
	
	private String radius;
	
	private String page_pp;
	
	/**
	 * 
	 * @param searchString_ss
	 * @param postcode_pc
	 * @param area
	 * @param radius
	 * @param page_pp
	 */
	public QueryBean(String searchString_ss, String postcode_pc, String radius, String page_pp)
	{
		this.searchString_ss = searchString_ss;
		this.postcode_pc =  postcode_pc;
		this.radius = radius;
		this.page_pp = page_pp;
		
			
	}

	public String getSearchString_ss() {
		return searchString_ss;
	}

	public void setSearchString_ss(String searchString_ss) {
		this.searchString_ss = searchString_ss;
	}

	public String getPostcode_pc() {
		return postcode_pc;
	}

	public void setPostcode_pc(String postcode_pc) {
		this.postcode_pc = postcode_pc;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String getPage_pp() {
		return page_pp;
	}

	public void setPage_pp(String page_pp) {
		this.page_pp = page_pp;
	}
}
