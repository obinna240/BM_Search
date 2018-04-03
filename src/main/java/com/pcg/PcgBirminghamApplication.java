package com.pcg;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.pcg.db.BirminghamEventRepository;


@SpringBootApplication
public class PcgBirminghamApplication {
	
	

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		ApplicationContext c = SpringApplication.run(PcgBirminghamApplication.class, args);
		/**BirminghamEventRepository repo = c.getBean(BirminghamEventRepository.class);
						
		repo.indexAll();
	*/
		/**
		OutPostCodeDBRepository repo = c.getBean(OutPostCodeDBRepository .class);
		long x = repo.count();
		System.out.println(x);
		Optional<List<OutPostCodeDB>> ans = repo.findByOutcodeIgnoringCase("AB10");
		if(ans.isPresent())
			System.out.println(ans.get().get(0).getLat());
			*/
	}
}




