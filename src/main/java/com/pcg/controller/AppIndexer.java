package com.pcg.controller;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pcg.db.BirminghamEventRepository;
import com.pcg.indexObjects.BirminghamIndexer;
import com.pcg.utils.Mailer;
import com.pcg.utils.MailerInterface;

@RestController
@RequestMapping("/api/indexer")
public class AppIndexer {
	private static final Logger logger = LogManager.getLogger(AppIndexer .class);	
	@Autowired
	BirminghamEventRepository birminghamEventRepository;
	@Autowired
	Mailer mailer;

	/**
	 * 
	 * @return String message specifying the application has been deployed
	 */
	@GetMapping("/indexAllFromDB")
	public ResponseEntity<Map<String, Object>> getData()
	{
		
		Optional<Map<String, Object>> outcomeOfIndexing = birminghamEventRepository.indexAll();
		if(outcomeOfIndexing.isPresent())
		{
			String message = "Data has successfully been indexed. Please check the logs for more details";
			outcomeOfIndexing.get().put("message", message);
			logger.info("Indexing was successful");
		}
		else
		{
			outcomeOfIndexing.get().put("message", "Indexing failed");
			logger.fatal("Indexing failed");
		}
		mailer.emailTeams("See attached comprehensive logs of indexing as of "+new Date(), "See attached logs of Birmingham events indexing as of "+new Date()+". Pay close attention to any fatal errors");
		return new ResponseEntity<Map<String,Object>>(outcomeOfIndexing.get(),HttpStatus.CREATED);
		
	
		
	}
	
	
	
	
	

	
}
