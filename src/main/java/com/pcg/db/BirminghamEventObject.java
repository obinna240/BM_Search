package com.pcg.db;
/**
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;
*/
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author oonyimadu
 *
 */

/**
@SuppressWarnings("serial")
@Entity
@NamedStoredProcedureQueries({
@NamedStoredProcedureQuery(name = "BirminghamPostcode", procedureName = "BirminghamPostcode",resultClasses=BirminghamEventObject.class,
parameters={
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="eventId"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="eventName"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="description"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="address"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="postcode"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="latitude_lat_s"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="longitude_lon_s"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="geoLocation_string_s"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="postcode_start"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="postcode_end"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="contactPhone"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="contactEmail"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="contactName"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="contactJobTitle"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="age_ms_s"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="conditions_ms_s"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="partialPostCodesOfferedIn_ms_s"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="whoFor_string_s"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="whenAvailable_string_ns"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="venuePostCode_string_ns"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="facebook_string_ns"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="twitter_string_ns"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="lastUpdated_string_ns"),
	@StoredProcedureParameter(mode=ParameterMode.OUT, type = String.class, name="nationalListing")
})})*/
public class BirminghamEventObject //implements Serializable
{
	//@Id
	private String eventId;
	private String eventName;
	private String description;
	private String address;
	private String postcode;
	private String latitude_lat_s;
	private String longitude_lon_s;
	private String geoLocation_string_s;
	private String postcode_start;
	private String postcode_end;
	private String contactPhone;
	private String contactEmail;
	private String contactName;
	private String contactJobTitle;
	private String age_ms_s;
	private String conditions_ms_s;		
	private String partialPostCodesOfferedIn_ms_s;
	private String whoFor_string_s;		
	private String whenAvailable_string_ns;
	private String venuePostCode_string_ns;
	private String facebook_string_ns;
	private String twitter_string_ns;
	private String lastUpdated_string_ns;
	private String nationalListing;
	private String category;
	private String BirminghamEventType;
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
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
	public String getGeoLocation_string_s() {
		return geoLocation_string_s;
	}
	public void setGeoLocation_string_s(String geoLocation_string_s) {
		this.geoLocation_string_s = geoLocation_string_s;
	}
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
	public String getAge_ms_s() {
		return age_ms_s;
	}
	public void setAge_ms_s(String age_ms_s) {
		this.age_ms_s = age_ms_s;
	}
	public String getConditions_ms_s() {
		return conditions_ms_s;
	}
	public void setConditions_ms_s(String conditions_ms_s) {
		this.conditions_ms_s = conditions_ms_s;
	}
	public String getPartialPostCodesOfferedIn_ms_s() {
		return partialPostCodesOfferedIn_ms_s;
	}
	public void setPartialPostCodesOfferedIn_ms_s(
			String partialPostCodesOfferedIn_ms_s) {
		this.partialPostCodesOfferedIn_ms_s = partialPostCodesOfferedIn_ms_s;
	}
	public String getWhoFor_string_s() {
		return whoFor_string_s;
	}
	public void setWhoFor_string_s(String whoFor_string_s) {
		this.whoFor_string_s = whoFor_string_s;
	}
	public String getWhenAvailable_string_ns() {
		return whenAvailable_string_ns;
	}
	public void setWhenAvailable_string_ns(String whenAvailable_string_ns) {
		this.whenAvailable_string_ns = whenAvailable_string_ns;
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
	public String getNationalListing() {
		return nationalListing;
	}
	public void setNationalListing(String nationalListing) {
		this.nationalListing = nationalListing;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBirminghamEventType() {
		return BirminghamEventType;
	}
	public void setBirminghamEventType(String birminghamEventType) {
		BirminghamEventType = birminghamEventType;
	}

}
/**
	public BirminghamEventObject(String eventId, String eventName)
	{
		this.eventId = eventId;
		this.eventName = eventName;
	}
	*/
