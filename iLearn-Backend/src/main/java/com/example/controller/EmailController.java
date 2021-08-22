package com.example.controller;

import java.util.LinkedHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.EmailService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(value="/email")
@NoArgsConstructor
@CrossOrigin(value = "*")
public class EmailController {

	private EmailService eServ;
	
	  @RequestMapping(value = "/sendemail")
	   public String sendmail() {
	      return "Email sent successfully";
	   }  
	/*
	@PostMapping("/mail")
	public ResponseEntity<String> updateItem(@RequestBody LinkedHashMap<String,String>item){
		System.out.println(i);
        iServ.updateItem(i);
        try {
			eServ.sendEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new ResponseEntity<String>("item updated successfully",HttpStatus.ACCEPTED);
	}*/
	

}
