package com.pcg.objects.queries;

import java.util.List;

/**
 * 
 * @author oonyimadu
 *
 */
public class BirminghamQueryBean extends QueryBean
{

	private List<String> age;
	private List<String> conditions;
	private List<String> partialPostCodesOfferedIn;
	private String nationalListing;
	private List<String> category;
	/**
	 * 
	 * @param searchString_ss
	 * @param postcode_pc
	 * @param area
	 * @param radius
	 * @param page_pp
	 * @param age
	 * @param conditions
	 * @param partialPostCodesOfferedIn
	 */
	public BirminghamQueryBean(String searchString_ss, String postcode_pc,
			 String radius, String page_pp, List<String> age, List<String> conditions,
			 List<String> partialPostCodesOfferedIn, String nationalListing, List<String> category) 
	{
		super(searchString_ss, postcode_pc,  radius, page_pp);
		this.age = age;
		this.conditions = conditions;
		this.partialPostCodesOfferedIn = partialPostCodesOfferedIn;
		this.nationalListing = nationalListing;
		this.category = category;
	}

	public List<String> getAge() {
		return age;
	}

	public void setAge(List<String> age) {
		this.age = age;
	}

	public List<String> getConditions() {
		return conditions;
	}

	public void setConditions(List<String> conditions) {
		this.conditions = conditions;
	}

	public List<String> getPartialPostCodesOfferedIn() {
		return partialPostCodesOfferedIn;
	}

	public void setPartialPostCodesOfferedIn(List<String> partialPostCodesOfferedIn) {
		this.partialPostCodesOfferedIn = partialPostCodesOfferedIn;
	}

	public String isNationalListing() {
		return nationalListing;
	}

	public void setNationalListing(String nationalListing) {
		this.nationalListing = nationalListing;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}
	
	
	
}

