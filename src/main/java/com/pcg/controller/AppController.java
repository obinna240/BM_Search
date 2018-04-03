package com.pcg.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcg.indexObjects.BirminghamIndexer;
import com.pcg.objects.PaginatorBean;
import com.pcg.objects.ResultBean;
import com.pcg.utils.PaginationUtils;
import com.pcg.utils.SolrQueryUtils;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/query")
public class AppController
{
	
	@Autowired
	SolrQueryUtils solrQueryUtils;
	@Autowired
	BirminghamIndexer bhamIndexer;
	
	
	private static final Logger logger = LogManager.getLogger(AppController.class);
	
	/**
	 * 
	 * @param ss
	 * @param pc
	 * @param age
	 * @param conditions
	 * @param partialPostCodesOfferedIn
	 * @param radius
	 * @param pp
	 * @param category
	 * @return ResponseEntity<ResultBean>
	 */
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ResponseEntity<ResultBean> generalSearch(
			@RequestParam(required=false) String ss, //searchString
			@RequestParam(required=false) String pc, //postcode
			@RequestParam(required=false) List<String> age, //age
			@RequestParam(required=false) List<String> conditions, //conditions
			@RequestParam(required=false) List<String> partialPostCodesOfferedIn, //partialPostCode
			@RequestParam(required=false) List<String> category, //category
			@RequestParam(required=false) String nationalListing, //nationalListing
			@RequestParam(required=false) String radius, //radius
			@RequestParam(required=false) String pp //page
			
			)
	
	{
		logger.info("== Logging user request ==");
		PaginatorBean paginatorBean = null;
		Integer pageNumber = null;
		
		if(pp==null)
		{
			paginatorBean = PaginationUtils.checkPagination(null);
		}
		else
		{
			pageNumber =  Integer.parseInt(pp);
			paginatorBean = PaginationUtils.checkPagination(pageNumber);
		}
		
		Map<String,Object> qParamStartRow = PaginationUtils.getQueryParams(paginatorBean);
				
		ResultBean resultBean = solrQueryUtils.doSearch(ss,pc,age,conditions,partialPostCodesOfferedIn,nationalListing,category,radius,pp, qParamStartRow);
		
		return new ResponseEntity<ResultBean>(resultBean,HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param eventId
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value="/removeFromIndex/{eventId}", method=RequestMethod.GET)
	public ResponseEntity<String> removeById(@PathVariable String eventId)
	{
		logger.info("Preparing to remove object with eventId "+eventId);
		Integer response = bhamIndexer.removeDocumentById(eventId);
		HttpStatus status = response!=20?HttpStatus.NOT_FOUND:HttpStatus.OK;
		String message = response!=20?"Resource with ID "+eventId+" does not exist, and has not been deleted from the index":"Resource with eventID "+eventId+ " has been deleted from the index";
		logger.info(message);
		return new ResponseEntity<String>(message,status);
	}
	
	/**
	 * Deletes the entire index
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value="/removeAllFromIndex", method=RequestMethod.GET)
	public ResponseEntity<String> deleteIndex()
	{
		logger.info("Preparing to clear index");
		Integer response = bhamIndexer.clearIndex();
		
		HttpStatus status = response!=0?HttpStatus.NOT_FOUND:HttpStatus.OK;
		String message = response!=0?"Index has not been deleted. Contact your administrator or check your logs":"Index has been deleted. Check logs to confirm";
		logger.info(message);
		return new ResponseEntity<String>(message,status);
		
	}
}
