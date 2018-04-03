package com.pcg.objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PostCodeAPIDetails
{
	
	@Value("${apiHost}")
	private String apiHost;
	@Value("${apiPath}")
	private String apiPath;
	
	@Value("${apiScheme}")
	private String apiScheme;
	
	public String getApiHost() {
		return apiHost;
	}
	public void setApiHost(String apiHost) {
		this.apiHost = apiHost;
	}
	public String getApiPath() {
		return apiPath;
	}
	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}
	
	public String getApiScheme() {
		return apiScheme;
	}
	public void setApiScheme(String apiScheme) {
		this.apiScheme = apiScheme;
	}
}
