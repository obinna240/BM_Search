package com.pcg.objects;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.pcg.utils.PostCodeToJson;

/**
 * 
 * @author oonyimadu
 *
 */
@Component
public class PostCodeBuilder implements PostCodeInterface
{
	///private String postcode;
	
	@Autowired
	private PostCodeToJson postCodeToJson;
	
	@Value("${apiResultField}")
	private String apiResultField;
	
	@Value("${apiResultLongitude}")
	private String apiResultLongitude;
	
	@Value("${apiResultLatitude}")
	private String apiResultLatitude;
	
	public void init(String postcode)
	{
		
		//this.postcode = postcode;
		if(StringUtils.isBlank(apiResultField))
		{
			apiResultField = "result";
		}
		if(StringUtils.isBlank(apiResultLongitude))
		{
			apiResultLongitude = "longitude";
		}
		if(StringUtils.isBlank(apiResultLatitude))
		{
			apiResultLatitude = "latitude";
		}
		this.postCodeToJson.setJsonObject(postcode);
	}
	
	//@PostConstruct
	//public void init()
	//{
	//	this.postCodeToJson.setJsonObject(postcode);
	//}
	
	/**
	 * 
	 */
	@Override
	public Map<String, Double> getLongLat() {
		// TODO Auto-generated method stub
		
		JsonObject js1 = this.postCodeToJson.getJsonObjects();
		
		Map<String, Double> jdoub = null;
		
		if(js1!=null){
			if(js1.get("status").getAsInt()!=404)
			{
				jdoub = new HashMap<String, Double>();
				Double doubLo = js1.get(apiResultField ).getAsJsonObject().get(apiResultLongitude).getAsDouble();
				Double doubLa = js1.get(apiResultField ).getAsJsonObject().get(apiResultLatitude).getAsDouble();
				
				jdoub.put("longitude", doubLo);
				jdoub.put("latitude", doubLa);
			}
		}
		return jdoub;
	}

	@Override
	public PostCodeObject getPostCodeObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Long> getEastingsNorthings() {
		// TODO Auto-generated method stub
		JsonObject js1 = postCodeToJson.getJsonObjects();
		Long eastings = js1.get(apiResultField ).getAsJsonObject().get("eastings").getAsLong();
		Long northings = js1.get(apiResultField ).getAsJsonObject().get("northings").getAsLong();
		Map<String, Long> jdoub = new HashMap<String, Long>();
		jdoub.put("eastings", eastings);
		jdoub.put("northings", northings);
		return jdoub;
	}


}
