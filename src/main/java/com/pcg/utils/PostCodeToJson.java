package com.pcg.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.pcg.exceptions.PostCodeFormatException;
import com.pcg.objects.PostCodeAPIDetails;

/**
 * 
 * @author oonyimadu
 *
 */
@Component
public class PostCodeToJson 
{
	@Autowired
	PostCodeAPIDetails apiDetails;
	private static final Logger logger = LogManager.getLogger(PostCodeToJson.class);
	private JsonObject jsonObjects;
	
		
	/**
	 * 
	 * @param postcode
	 * @return URI
	 */
	public URI buildURI(String postcode)
	{
		URI uri = null;
		String apiHost = apiDetails.getApiHost();
		if(StringUtils.isBlank(apiHost))
		{
			apiHost = "s4spostcodes.cloudapp.net";
		}
		String apiScheme = apiDetails.getApiScheme();
		if(StringUtils.isBlank(apiScheme))
		{
			apiScheme = "http";
		}
		String apiPath = apiDetails.getApiPath();
		if(StringUtils.isBlank(apiPath))
		{
			apiPath = "/postcodes";
		}
		if(StringUtils.isNotBlank(postcode))
		{
			StringBuffer str = new StringBuffer();
			str.append(apiPath).append("/").append(postcode);
			apiPath = str.toString();
			try 
			{
				uri = new URIBuilder().setScheme(apiScheme).setHost(apiHost).setPath(apiPath).build();
			} 
			catch (URISyntaxException e) 
			{
				logger.warn(e.getMessage());
				//e.printStackTrace();
			}
		}
		return uri;
	}
	
	public void setJsonObject(String postcode)
	{
		JSONBuilderClass jsBuilder = this.new JSONBuilderClass();
		JsonObject jsObject = jsBuilder.getPostCodeObjects(postcode);
		setJsonObjects(jsObject);
	}
	/**
	 * 
	 * @author oonyimadu
	 *
	 */
	private class JSONBuilderClass
	{
		/**
		 * 
		 * @param postcode
		 * @return JsonObject
		 */
		private JsonObject getPostCodeObjects(String postcode)
		{
			JsonObject jsonObject = null;
			if(StringUtils.isNotBlank(postcode))
			{
				
				//try
				//{
					boolean pcVal = PostCodeValidator.validate(postcode);
					if(pcVal)
					{
						URI uri = buildURI(postcode);
						CloseableHttpClient client = HttpClients.createDefault();
						HttpGet httpget = new HttpGet(uri);
						ResponseHandler<Object> rh = new ResponseHandler<Object>() {
	
						    @Override
						    public JsonObject handleResponse(
						            final HttpResponse response) throws IOException {
						        StatusLine statusLine = response.getStatusLine();
						        HttpEntity entity = response.getEntity();
						       
						        if (statusLine.getStatusCode() >= 300) 
						        {
						        	HttpResponseException ec = new HttpResponseException(
						                    statusLine.getStatusCode(),
						                    statusLine.getReasonPhrase());
						        	//throw new HttpResponseException(
						            //        statusLine.getStatusCode(),
						            //        statusLine.getReasonPhrase());
						        	logger.error(ec.getMessage());
						           
						            
						                    
						                   
						           
						        }
						        
						        if (entity == null) {
						            //throw new ClientProtocolException("Response contains no content");
						            ClientProtocolException cc =  new ClientProtocolException("Response contains no content");
						            logger.error(cc.getMessage());
						        }
						        Gson gson = new GsonBuilder().create();
						        ContentType contentType = ContentType.getOrDefault(entity);
						        Charset charset = contentType.getCharset();
						        Reader reader = new InputStreamReader(entity.getContent(), charset);
						        //return gson.fromJson(reader, MyJsonObject.class);
						        return  gson.fromJson(reader, JsonObject.class);
						    }
						};
						try {
							jsonObject =  (JsonObject) client.execute(httpget, rh);
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				//}
				//catch (PostCodeFormatException e) 
				//{
					// TODO Auto-generated catch block
				//	logger.warn(e.getMessage());
				//}
				
			}
			//setJsonObjects(jsonObject);
			return jsonObject;
		}
	}



	public JsonObject getJsonObjects() {
		return jsonObjects;
	}

	public void setJsonObjects(JsonObject jsonObjects) {
		this.jsonObjects = jsonObjects;
	}
}
