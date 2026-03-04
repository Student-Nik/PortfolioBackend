package com.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.dto.ContactRequest;
import com.portfolio.service.EmailService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class EmailController {

	private final EmailService emailService;

	@Autowired
	public EmailController(EmailService emailService) {
		super();
		this.emailService = emailService;
	}
	
	// Send and Receive Mail (Name, Email and Message)
	
	@PostMapping("/me")
    public ResponseEntity<String> sendContactEmail(
            @Valid @RequestBody ContactRequest contactRequest) {
		return emailService.sendEmails(contactRequest);
    }
}
