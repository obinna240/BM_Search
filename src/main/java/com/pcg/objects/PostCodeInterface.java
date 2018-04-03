package com.pcg.objects;

import java.util.Map;

/**
 * 
 * @author oonyimadu
 *
 */
public interface PostCodeInterface 
{
	public Map<String, Double> getLongLat();
	public PostCodeObject getPostCodeObject();
	public Map<String, Long> getEastingsNorthings();
}
