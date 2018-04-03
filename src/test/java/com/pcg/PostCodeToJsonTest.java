package com.pcg;


import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;


import static org.mockito.Mockito.verify;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.JsonObject;
import com.pcg.objects.PostCodeAPIDetails;
import com.pcg.utils.PostCodeToJson;


@RunWith(MockitoJUnitRunner.class)
public class PostCodeToJsonTest 
{
	@Mock //alternatively use mock() static method
	PostCodeAPIDetails apiDetails;
	@InjectMocks
	PostCodeToJson postCodeToJson;
	
	//create a mock PostCodeAPIDetails object
	@Test
	public void checkThatURIisNotNullForAPostCode()
	{
		URI uri = postCodeToJson.buildURI("SL6 5BW");
		assertNotNull("is Not Null", uri);
		
	}
	
	@Test
	public void checkThatJsonObjectIsNotNullForAPostCode()
	{
		postCodeToJson.setJsonObject("SL6 5BW");
		assertNotNull(postCodeToJson.getJsonObjects());
		
	}
	
	
	
	
}

/**
public class PostCodeToJson 
{
	@Autowired
	PostCodeAPIDetails apiDetails;
	private static final Logger logger = LogManager.getLogger(PostCodeToJson.class);
	private JsonObject jsonObjects;
	
	
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

	private class JSONBuilderClass
	{
		
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
						        if (statusLine.getStatusCode() >= 300) {
						            throw new HttpResponseException(
						                    statusLine.getStatusCode(),
						                    statusLine.getReasonPhrase());
						        }
						        if (entity == null) {
						            throw new ClientProtocolException("Response contains no content");
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
*/