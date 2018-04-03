package com.pcg.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient.Builder;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.pcg.objects.IndexObject;
import com.pcg.objects.IndexObjectBeans;
import com.pcg.objects.PaginatorBean;
import com.pcg.objects.PostCodeBuilder;
import com.pcg.objects.ResultBean;
import com.pcg.objects.queries.BirminghamQueryBean;
import com.pcg.objects.queries.QueryBean;

/**
 * 
 * @author oonyimadu
 *
 */
@Component
public class SolrQueryUtils 
{
		
	@Autowired 
	SolrQUtils solrQUtils;
	SolrClient server;
		
	private static Log log = LogFactory.getLog(SolrQueryUtils .class);	
	
	/**
	 * Appends filters if using a list
	 * @param filters
	 * @param fieldType
	 * @param solrFieldName
	 * @return List<String>
	 */
	protected List<String> appendFilters(List<String> filters, List<String> fieldType, String solrFieldName)
	{
		
		if(CollectionUtils.isNotEmpty(fieldType))
		{
			
			List<String> list = Cleaner.mergeListForFiler(solrFieldName, fieldType);
			
			
			filters.addAll(list);
		}
		return filters;
	}
	/**
	 * 
	 * @param filters
	 * @param fieldType
	 * @param solrFieldName
	 * @return List<String>
	 */
	protected List<String> appendFilters(List<String> filters, String fieldType, String solrFieldName)
	{
		
		if(StringUtils.isNotBlank(fieldType))
		{
			
			List<String> list = Cleaner.mergeListForFiler(solrFieldName, fieldType);
			
			
			filters.addAll(list);
		}
		return filters;
	}
	
	
	/**
	 * Appends filters if using String field Types
	 * @param filters
	 * @param fieldType
	 * @param solrFieldName
	 * @return List<String>
	 *
	protected List<String> appendFilters(List<String> filters, String fieldType, String solrFieldName)
	{
		
		if(StringUtils.isNotBlank(fieldType))
		{
			String[] val = Cleaner.splitUnderscore(fieldType);
			
			List<String> list = Cleaner.mergeListForFiler(solrFieldName, val);
			filters.addAll(list);
		}
		return filters;
	}
	*/
	/**
	 * 
	 * @param searchString_ss
	 * @param pc
	 * @param age
	 * @param conditions
	 * @param partialPostCodesOfferedIn
	 * @param radius
	 * @param pp
	 * @param qParamStartRow
	 * @return ResultBean
	
	public ResultBean doSearch(String searchString_ss, String pc, String age, String conditions, String partialPostCodesOfferedIn, String radius, 
			 String pp, Map<String,Object> qParamStartRow)
	{
		SolrQuery q = getGenericSearchQuery(searchString_ss, pc, age, conditions, partialPostCodesOfferedIn, radius, 
				 pp, qParamStartRow);
		ResultBean resultBean = getResultFromResponse(q, searchString_ss, pc, 
				radius, pp, age, conditions, partialPostCodesOfferedIn, 
				qParamStartRow);
		return resultBean;
		
	}
	*/
	
	public ResultBean doSearch(String searchString_ss, String pc, List<String> age, List<String> conditions, List<String> partialPostCodesOfferedIn, 
			String nationalListing,List<String> category,String radius, String pp, Map<String,Object> qParamStartRow)
			
	{
		SolrQuery q = getGenericSearchQuery(searchString_ss, pc, age, conditions, partialPostCodesOfferedIn, nationalListing, category, radius, 
				 pp, qParamStartRow);
			
		ResultBean resultBean = getResultFromResponse(q, searchString_ss, pc, 
				radius, pp, age, conditions, partialPostCodesOfferedIn,nationalListing,
				category, qParamStartRow);
		
		return resultBean;
		
	}
	
	private SolrQuery getGenericSearchQuery(String searchString_ss, String pc, List<String> age, List<String> conditions, 
			List<String> partialPostCodesOfferedIn, String nationalListing, List<String> category, String radius, String pp, Map<String,Object> qParamStartRow)
			{		
															
				List<String> lfilters = new ArrayList<String>();
								
				
				SolrQuery query = new SolrQuery();
												
				query = solrQUtils.getStringQuery(searchString_ss, query);
				
				query = solrQUtils.checkPostcode(pc, query,radius);
				
				String retVal = solrQUtils.getNationalListing(nationalListing);
				
				List<String> filters = new ArrayList<String>();
				//fix filters
				filters = appendFilters(filters, age, solrQUtils.getAge());
				filters = appendFilters(filters, conditions, solrQUtils.getCondition());
				filters = appendFilters(filters, partialPostCodesOfferedIn, solrQUtils.getPartialPc());
				filters = appendFilters(filters, category, solrQUtils.getCategory());
				///
				if(StringUtils.isNotBlank(retVal))
				{
					filters = appendFilters(filters, retVal, solrQUtils.getNationlLs());
				}
				///
				query = solrQUtils.appendFilters(filters, lfilters, query);
								
				if(MapUtils.isNotEmpty(qParamStartRow))
				{
					Integer qStart = (Integer)qParamStartRow.get("queryStart");
					Integer qRows = (Integer)qParamStartRow.get("queryRows");
					
					query.setStart(qStart);
					query.setRows(qRows);
				}
				query.setFacet(true);
				query.setFacetMinCount(1);
				String[] facetFieldArray = {solrQUtils.getAge(),solrQUtils.getCondition(),solrQUtils.getPartialPc()};
				query.addFacetField(facetFieldArray);
				
				return query;
			}
	
	/**
	 * 
	 * @param searchString_ss
	 * @param pc
	 * @param age
	 * @param conditions
	 * @param partialPostCodesOfferedIn
	 * @param radius
	 * @param pp
	 * @param qParamStartRow
	 * @return ResultBean
	 *
	private SolrQuery getGenericSearchQuery(
			String searchString_ss, String pc, String age, String conditions, String partialPostCodesOfferedIn, String radius, 
			 String pp, Map<String,Object> qParamStartRow)
			{		
															
				List<String> lfilters = new ArrayList<String>();
								
				
				SolrQuery query = new SolrQuery();
												
				query = solrQUtils.getStringQuery(searchString_ss, query);
				
				query = solrQUtils.checkPostcode(pc, query,radius);
				
				List<String> filters = new ArrayList<String>();
				//fix filters
				filters = appendFilters(filters, age, solrQUtils.getAge());
				filters = appendFilters(filters, conditions, solrQUtils.getCondition());
				filters = appendFilters(filters, partialPostCodesOfferedIn, solrQUtils.getPartialPc());
				query = solrQUtils.appendFilters(filters, lfilters, query);
								
				if(MapUtils.isNotEmpty(qParamStartRow))
				{
					Integer qStart = (Integer)qParamStartRow.get("queryStart");
					Integer qRows = (Integer)qParamStartRow.get("queryRows");
					
					query.setStart(qStart);
					query.setRows(qRows);
				}
				query.setFacet(true);
				query.setFacetMinCount(1);
				String[] facetFieldArray = {solrQUtils.getAge(),solrQUtils.getCondition(),solrQUtils.getPartialPc()};
				query.addFacetField(facetFieldArray);
				
				return query;
			}
	*/
		/**
		 * 
		 * @param response
		 * @return FacetMap
		 */
		public Map<String,Map<String,Long>> getFacets(QueryResponse response) {
		   
		    final List<FacetField> facetFields = response.getFacetFields();
		    Map<String,Map<String,Long>> facetMap = new HashMap<String,Map<String,Long>>();
		    if (CollectionUtils.isNotEmpty(facetFields)) {
		    	for(FacetField face:facetFields)
		    	{
		    		String facetName = face.getName();//System.out.println();
		    		 Map<String,Long> newMap = new HashMap<String,Long>();
		    		// List<FacetCount> facetCounts = new ArrayList<>();
		             for (FacetField.Count count : face.getValues()) {
		            	 newMap.put(count.getName(), count.getCount());
		                		 //System.out.println(count.getName()+"  "+ count.getCount());
		             }
		    		
		    		
		    		//System.out.println(face.getValues());
		    		//System.out.println(face.getValueCount());
		            facetMap.put(facetName, newMap);
		            
		        } 
		        
		    }
		    return facetMap;
		}
		
		/**
		 * 
		 * @param response
		 * @return ResultBean
		 */
		private ResultBean getResultFromResponse(SolrQuery query, String searchString, String postcode, 
				String radius, String page, List<String> age, List<String> conditions, List<String> pcofferedin, 
				String nationalListing, List<String> category, Map<String,Object> qParamStartRow)
		{
			
			server = solrQUtils.returnSolrClient();
			QueryResponse response = null;
			ResultBean resultBean = null;
			PaginatorBean originalPaginatorBean = null;
			if(MapUtils.isNotEmpty(qParamStartRow))
			{
				originalPaginatorBean = (PaginatorBean)qParamStartRow.get("originalPaginatorBean");
			
			}
			IndexObjectBeans indexObjectBeans = null;
			try
			{
				response = server.query(query);
				Long resultSize = response.getResults().getNumFound();
					
				QueryBean queryBean = new BirminghamQueryBean(searchString, postcode,
						radius, page, age, conditions, pcofferedin,nationalListing,category);
						
	
					resultBean = new ResultBean();
					if(resultSize>0)
					{
						indexObjectBeans = new IndexObjectBeans();
						List<IndexObject> indexObjectList = new ArrayList<IndexObject>();
						Integer numberOfResults = (int)(long)resultSize;
						
						
						SolrDocumentList solrList = response.getResults();
						if(CollectionUtils.isNotEmpty(solrList))
						{
							for(SolrDocument solrDoc:solrList)
							{
								//BM_EventObject create BM_EventObject
								IndexObject indexObj = Utils.doSearchResultBean(solrDoc);
								indexObjectList.add(indexObj);
							}
							
							indexObjectBeans.setIndexObject(indexObjectList);
							
							PaginatorBean resultPBean = null;
							
							if(originalPaginatorBean!=null)
							{
								resultPBean = PaginationUtils.doPaginationTest(numberOfResults, originalPaginatorBean);
								indexObjectBeans.setPaginatorBean(resultPBean);
							}
							
							resultBean.setIndexObjectBeans(indexObjectBeans);
							resultBean.setTotalNumberOfResults(numberOfResults);
							resultBean.setStart(query.getStart());
																
														
						}
					}
					else
					{
						resultBean.setIndexObjectBeans(null);
						resultBean.setTotalNumberOfResults(0);
						
					}
					resultBean.setFacetMap(getFacets(response));
					resultBean.setQueryBean(queryBean);
					server.close();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					log.error("Solr Exception " + ex.getMessage());
				}
				
				return resultBean;
			}
	
}


