package com.pcg.objects;

import java.util.List;

/**
 * Birmingham Custom Object extends Generic IndexObject
 * @author oonyimadu
 *
 */
public class BM_EventObject extends IndexObject
{
	/**
	 * Start of custom fields
	 * Sting naming convention
	 * a_b_c, where a=column name, b=category e.g 
	 * multiple string and c=searchable (s) or not searchable (ns)
	 */
	private List<String> age_ms_s; //ms=multiple string
	private List<String> conditions_ms_s;
	private List<String> partialPostCodesOfferedIn_ms_s;
	private String misc_string_s; //string = single string entry //miscellaneous
	private String latitude_lat_s; //lat = latitude
	private String longitude_lon_s; //
	private List<String> whoFor_ms_s; //who is the service for
	
	private String whenAvailable_string_ns; //when is the service available
	private String venueAddress_string_ns; //venue address
	private String venuePostCode_string_ns; //venue postcode
	private String venueLong_string_s; //venue long
	
	private String venueLat_string_s; //venue lat
	private String facebook_string_ns; //facebook url
	private String twitter_string_ns; //twitter handle
	private String lastUpdated_string_ns; //date entry made purely for presentation purposes

	private String geoLocation_string_s; //place name
	private String postcode_start;
	private String postcode_end;
	private String organizationAddress; //the event host's address
	private String organizationPostcode; //the event host's postcode
	private String contactPhone;
	private String contactEmail; //standard
	private String contactName;
	private String contactJobTitle;	
	private Double distance;
	private boolean nationalListing;
	
	//additional fields
	private List<String> category;
	private String BirminghamEventType;
	
	public String getPostcode_start() {
		return postcode_start;
	}
	public void setPostcode_start(String postcode_start) {
		this.postcode_start = postcode_start;
	}
	public String getPostcode_end() {
		return postcode_end;
	}
	public void setPostcode_end(String postcode_end) {
		this.postcode_end = postcode_end;
	}
	public String getOrganizationAddress() {
		return organizationAddress;
	}
	public void setOrganizationAddress(String organizationAddress) {
		this.organizationAddress = organizationAddress;
	}
	public String getOrganizationPostcode() {
		return organizationPostcode;
	}
	public void setOrganizationPostcode(String organizationPostcode) {
		this.organizationPostcode = organizationPostcode;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactJobTitle() {
		return contactJobTitle;
	}
	public void setContactJobTitle(String contactJobTitle) {
		this.contactJobTitle = contactJobTitle;
	}
	
	public String getVenueLong_string_s() {
		return venueLong_string_s;
	}
	public void setVenueLong_string_s(String venueLong_string_s) {
		this.venueLong_string_s = venueLong_string_s;
	}
	public String getVenueLat_string_s() {
		return venueLat_string_s;
	}
	public void setVenueLat_string_s(String venueLat_string_s) {
		this.venueLat_string_s = venueLat_string_s;
	}
	public List<String> getAge_ms_s() {
		return age_ms_s;
	}
	public void setAge_ms_s(List<String> age_ms_s) {
		this.age_ms_s = age_ms_s;
	}
	public List<String> getConditions_ms_s() {
		return conditions_ms_s;
	}
	public void setConditions_ms_s(List<String> conditions_ms_s) {
		this.conditions_ms_s = conditions_ms_s;
	}
	public List<String> getPartialPostCodesOfferedIn_ms_s() {
		return partialPostCodesOfferedIn_ms_s;
	}
	public void setPartialPostCodesOfferedIn_ms_s(
			List<String> partialPostCodesOfferedIn_ms_s) {
		this.partialPostCodesOfferedIn_ms_s = partialPostCodesOfferedIn_ms_s;
	}
	public String getMisc_string_s() {
		return misc_string_s;
	}
	public void setMisc_string_s(String misc_string_s) {
		this.misc_string_s = misc_string_s;
	}
	public String getLatitude_lat_s() {
		return latitude_lat_s;
	}
	public void setLatitude_lat_s(String latitude_lat_s) {
		this.latitude_lat_s = latitude_lat_s;
	}
	public String getLongitude_lon_s() {
		return longitude_lon_s;
	}
	public void setLongitude_lon_s(String longitude_lon_s) {
		this.longitude_lon_s = longitude_lon_s;
	}

	public String getWhenAvailable_string_ns() {
		return whenAvailable_string_ns;
	}
	public void setWhenAvailable_string_ns(String whenAvailable_string_ns) {
		this.whenAvailable_string_ns = whenAvailable_string_ns;
	}
	public String getVenueAddress_string_ns() {
		return venueAddress_string_ns;
	}
	public void setVenueAddress_string_ns(String venueAddress_string_ns) {
		this.venueAddress_string_ns = venueAddress_string_ns;
	}
	public String getVenuePostCode_string_ns() {
		return venuePostCode_string_ns;
	}
	public void setVenuePostCode_string_ns(String venuePostCode_string_ns) {
		this.venuePostCode_string_ns = venuePostCode_string_ns;
	}
	public String getFacebook_string_ns() {
		return facebook_string_ns;
	}
	public void setFacebook_string_ns(String facebook_string_ns) {
		this.facebook_string_ns = facebook_string_ns;
	}
	public String getTwitter_string_ns() {
		return twitter_string_ns;
	}
	public void setTwitter_string_ns(String twitter_string_ns) {
		this.twitter_string_ns = twitter_string_ns;
	}
	public String getLastUpdated_string_ns() {
		return lastUpdated_string_ns;
	}
	public void setLastUpdated_string_ns(String lastUpdated_string_ns) {
		this.lastUpdated_string_ns = lastUpdated_string_ns;
	}

	public String getGeoLocation_string_s() {
		return geoLocation_string_s;
	}
	public void setGeoLocation_string_s(String geoLocation_string_s) {
		this.geoLocation_string_s = geoLocation_string_s;
	}
	public List<String> getWhoFor_ms_s() {
		return whoFor_ms_s;
	}
	public void setWhoFor_ms_s(List<String> whoFor_ms_s) {
		this.whoFor_ms_s = whoFor_ms_s;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public boolean isNationalListing() {
		return nationalListing;
	}
	public void setNationalListing(boolean nationalListing) {
		this.nationalListing = nationalListing;
	}
	public List<String> getCategory() {
		return category;
	}
	public void setCategory(List<String> category) {
		this.category = category;
	}
	public String getBirminghamEventType() {
		return BirminghamEventType;
	}
	public void setBirminghamEventType(String birminghamEventType) {
		BirminghamEventType = birminghamEventType;
	}
}
