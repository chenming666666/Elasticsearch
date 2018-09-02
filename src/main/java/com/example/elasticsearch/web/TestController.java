 package com.example.elasticsearch.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.elasticsearch.service.HotelService;

@RestController
public class TestController {

	@Autowired
	HotelService hotelService;
	
	@GetMapping("/test")    //http://localhost:8080/test
	public Object test() {
		
		return hotelService.pageAndSortQuery();
		
	} 
	
}
