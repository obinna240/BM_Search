package com.pcg.utils;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;




/**
 * Utility method for cleaning strings
 * @author oonyimadu
 *
 */
public class Cleaner 
{
	
	/**
	 * 
	 * @param string
	 * @return String[]
	 */
	public static String[] splitUnderscore(String string)
	{
		String[] splitString = null;
		if(StringUtils.isNotBlank(string))
		{
			splitString = string.split("_");
		}
		return splitString;
	}
	
	public static List<String> mergeListForFiler(String filterType, String[] array)
	{
		List<String> mergerList = null;
		if(array.length>0)
		{
			mergerList = new ArrayList<String>();
			for(String arr:array)
			{
				arr = StringUtils.normalizeSpace(arr);
				arr = filterType+":"+arr;
				mergerList.add(arr);
			}
		}
		return mergerList;
		
	}
	
	/**
	 * 
	 * @param filterType
	 * @param flist
	 * @return List<String>
	 */
	public static List<String> mergeListForFiler(String filterType, List<String> flist)
	{
		List<String> mergerList = new ArrayList<String>();
		for(String lst:flist)
		{
			lst = replaceAll(StringUtils.normalizeSpace(lst));
						
			lst = filterType+":"+lst;
			mergerList.add(lst);
			
		}
		return mergerList;
	}
	
	/**
	 * 
	 * @param filterType
	 * @param flist
	 * @return List<String>
	 */
	public static List<String> mergeListForFiler(String filterType, String flist)
	{
		List<String> mergerList = new ArrayList<String>();
		if(StringUtils.isNotBlank(flist))
		{
			flist = StringUtils.normalizeSpace(flist);
			flist = new StringBuffer().append(filterType).append(":").append(flist).toString();
			mergerList.add(flist);
		}
		
		return mergerList;
	}
	
	/**
	 * 
	 * @param filters
	 * @return String 
	 */
	public static String createFilterParam(List<String> filters)
	{
		String filterParam = null;
		if(CollectionUtils.isNotEmpty(filters))
		{
			int fsize = filters.size();
			if(fsize == 1)
			{
				filterParam = filters.get(0);
			}
			else if(fsize > 1)
			{
				filterParam = "";
				for(int i=0;i<fsize;i++)
				{
					if(i!=fsize-1)
					{
						filterParam = filterParam +filters.get(i)+" AND ";
					}
					else if(i==fsize-1)
					{
						filterParam = filterParam+filters.get(i);
					}
				}
			}
		}
		return filterParam;
	}
	
	
	/**
	 * Escapes strings for SOLR
	 * @param string
	 * @return
	 */
	public static String replaceAll(String string)
	{
		if(string!=null)
		{
			string = StringUtils.replace(string, "\\", "\\\\").replace( "/", "\\/").
				replace("(", "\\(").replace(")", "\\)")
				.replace( "+", "\\+").replace( "-", "\\-").replace( "&&", "\\&&").replace("||", "\\||").
				replace("!", "\\!").replace("{", "\\{").replace("}", "\\}").replace("[", "\\[").
				replace("]", "\\]").replace("^", "\\^").replace("~", "\\~").
				replace("*", "\\*").replace("?", "\\?").replace(":", "\\:");
		}		
		return string;
	
	}
	
	protected static String unicodeEscape(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ((c >> 7) > 0) {
				sb.append("\\u");
				sb.append(SearchConstants.HEX_CHAR[(c >> 12) & 0xF]); // append the hex character
														// for the left-most
														// 4-bits
				sb.append(SearchConstants.HEX_CHAR[(c >> 8) & 0xF]); // hex for the second group
													// of 4-bits from the left
				sb.append(SearchConstants.HEX_CHAR[(c >> 4) & 0xF]); // hex for the third group
				sb.append(SearchConstants.HEX_CHAR[c & 0xF]); // hex for the last group, e.g.,
												// the right most 4-bits
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	/**
	public static void main(String[] args)
	{
		String x = " ";
		String[] xx = Cleaner.splitUnderscore(x);
		System.out.println(xx.length);
	}
	*/
}
