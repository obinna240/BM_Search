package com.pcg.objects;

import java.util.Map;

import com.pcg.objects.queries.QueryBean;

/**
 * 
 * @author oonyimadu
 * A ResultBean which represents an indexed class
 */
public class ResultBean 
{
	private Integer totalNumberOfResults;
	private Integer pageNumber;
	private Integer start;
	private IndexObjectBeans indexObjectBeans;
	private QueryBean queryBean;
	private Map<String,Map<String,Long>> facetMap;
	
	public Integer getTotalNumberOfResults() {
		return totalNumberOfResults;
	}
	public void setTotalNumberOfResults(Integer totalNumberOfResults) {
		this.totalNumberOfResults = totalNumberOfResults;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	
	public QueryBean getQueryBean() {
		return queryBean;
	}
	public void setQueryBean(QueryBean queryBean) {
		this.queryBean = queryBean;
	}
	public IndexObjectBeans getIndexObjectBeans() {
		return indexObjectBeans;
	}
	public void setIndexObjectBeans(IndexObjectBeans indexObjectBeans) {
		this.indexObjectBeans = indexObjectBeans;
	}
	public Map<String,Map<String,Long>> getFacetMap() {
		return facetMap;
	}
	public void setFacetMap(Map<String,Map<String,Long>> facetMap) {
		this.facetMap = facetMap;
	}

	
	
	
}
