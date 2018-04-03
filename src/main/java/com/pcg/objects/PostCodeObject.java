package com.pcg.objects;

/**
 * Encapsulates a PostCode Object
 * @author oonyimadu
 *
 */
public class PostCodeObject 
{
	private String postcode;
	private String country;
	private String nhs_ha;
	private Double longitude;
	private Double latitude;
	private Long eastings;
	private Long northings;
	private String constituency;
	private String electoralRegion;
	private String primary_care_trust;
	private String region;
	private String incode;
	private String outcode;
	private String admin_district;
	private String parish;
	private String admin_ward;
	private String admin_county;
	private String nuts;
	private String ccg;
	
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getNhs_ha() {
		return nhs_ha;
	}
	public void setNhs_ha(String nhs_ha) {
		this.nhs_ha = nhs_ha;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Long getEastings() {
		return eastings;
	}
	public void setEastings(Long eastings) {
		this.eastings = eastings;
	}
	public Long getNorthings() {
		return northings;
	}
	public void setNorthings(Long northings) {
		this.northings = northings;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getElectoralRegion() {
		return electoralRegion;
	}
	public void setElectoralRegion(String electoralRegion) {
		this.electoralRegion = electoralRegion;
	}
	public String getPrimary_care_trust() {
		return primary_care_trust;
	}
	public void setPrimary_care_trust(String primary_care_trust) {
		this.primary_care_trust = primary_care_trust;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getIncode() {
		return incode;
	}
	public void setIncode(String incode) {
		this.incode = incode;
	}
	public String getOutcode() {
		return outcode;
	}
	public void setOutcode(String outcode) {
		this.outcode = outcode;
	}
	public String getAdmin_district() {
		return admin_district;
	}
	public void setAdmin_district(String admin_district) {
		this.admin_district = admin_district;
	}
	public String getParish() {
		return parish;
	}
	public void setParish(String parish) {
		this.parish = parish;
	}
	public String getAdmin_ward() {
		return admin_ward;
	}
	public void setAdmin_ward(String admin_ward) {
		this.admin_ward = admin_ward;
	}
	public String getAdmin_county() {
		return admin_county;
	}
	public void setAdmin_county(String admin_county) {
		this.admin_county = admin_county;
	}
	public String getNuts() {
		return nuts;
	}
	public void setNuts(String nuts) {
		this.nuts = nuts;
	}
	public String getCcg() {
		return ccg;
	}
	public void setCcg(String ccg) {
		this.ccg = ccg;
	}

}

/**
{
	  "status": 200,
	  "result": {
	    "postcode": "SL6 5BW",
	    "quality": 1,
	    "eastings": 486585,
	    "northings": 181308,
	    "country": "England",
	    "nhs_ha": "South Central",
	    "longitude": -0.75335676391475,
	    "latitude": 51.524061127665,
	    "parliamentary_constituency": "Maidenhead",
	    "european_electoral_region": "South East",
	    "primary_care_trust": "Berkshire East",
	    "region": "South East",
	    "lsoa": "Windsor and Maidenhead 006F",
	    "msoa": "Windsor and Maidenhead 006",
	    "incode": "5BW",
	    "outcode": "SL6",
	    "admin_district": "Windsor and Maidenhead",
	    "parish": "Windsor and Maidenhead, unparished area",
	    "admin_county": null,
	    "admin_ward": "Pinkneys Green",
	    "ccg": "NHS Windsor, Ascot and Maidenhead",
	    "nuts": "Berkshire",
	    "codes": {
	      "admin_district": "E06000040",
	      "admin_county": "E99999999",
	      "admin_ward": "E05002369",
	      "parish": "E43000033",
	      "ccg": "E38000207",
	      "nuts": "UKJ11"
	    }
	  }
	}

*/