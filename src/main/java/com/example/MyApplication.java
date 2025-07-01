package com.example;

import org.apache.coyote.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.example.DatabaseManager;

import com.example.Report;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
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
	public ResponseEntity requestMethodName() {
		
		List<Report> reportList = db.getAllReports();
		return ResponseEntity.created(
			URI.create("/reports/all"))
			.body(reportList);


	}


	@PostMapping("/post")
	public ResponseEntity<Report> postMethodName(@RequestBody Report report) {
		
		//Saves report to database
		db.saveReport(report);

		//Returns ResponseEntity
		return ResponseEntity.created(
			URI.create(String.format("/post/%d", report.getID())))
			.body(report);

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
