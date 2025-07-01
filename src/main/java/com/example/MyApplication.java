package com.example;

import org.apache.coyote.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DatabaseManager;

import com.example.Report;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate; 
 

@SuppressWarnings("unused")
@RestController
@SpringBootApplication
public class MyApplication {

	DatabaseManager db = new DatabaseManager();

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	@RequestMapping("/reports")
	String allReports(){

		List<Report> reportList = db.getAllReports();
		System.out.println(reportList);

		String json = null; 

		ObjectMapper om = new ObjectMapper();

		try{
			json = om.writeValueAsString(reportList);
		}
		catch (Exception e){
			System.out.println(e);
		}

		System.out.println(json);
		return json; 

		
		
	}

	@RequestMapping("/post")
	public void InsertReport() {

		Report test = new Report();
		
		test.setBody("Henry Buys a bean 3");
        test.setDateNow();
        test.setUser("Henry but not real");


		db.saveReport(test);
	}
	


	@RequestMapping("/int")
	int[] intArray(){
		int[] returnArray = {1, 2, 3};
		return returnArray; 
	}

	public static void main(String[] args) {
		
		//DatabaseManager db = new DatabaseManager(); 
		SpringApplication.run(MyApplication.class, args);
	}

}
