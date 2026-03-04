package com.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.portfolio.dto.ContactRequest;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	private final JavaMailSender javaMailSender;

	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}
	
	// Send and Receive Mail (Name, Email and Message)
	
	/*
	 * Confirmation email to user
	 * Notification email to yourself
	 */
	
	/*public ResponseEntity<String> sendEmails(ContactRequest contactRequest) {

	    try {

	        // Mail to USER
	        SimpleMailMessage userMail = new SimpleMailMessage();
	        userMail.setTo(contactRequest.getEmail());
	        userMail.setSubject("Thank You for Contacting Us");
	        userMail.setText("Hi " + contactRequest.getName() +
	                ",\n\nWe received your message:\n\n" +
	                contactRequest.getMessage() +
	                "\n\nWe will get back to you soon.");

	        javaMailSender.send(userMail);

	        // Mail to YOU
	        SimpleMailMessage adminMail = new SimpleMailMessage();
	        adminMail.setTo("javadev.nikhilkute@gmail.com");
	        adminMail.setSubject("New Contact Form Submission");
	        adminMail.setText("Name: " + contactRequest.getName() +
	                "\nEmail: " + contactRequest.getEmail() +
	                "\nMessage:\n" + contactRequest.getMessage());

	        javaMailSender.send(adminMail);

	        return ResponseEntity.ok("E-mail Sent Successfully!");

	    } catch (Exception e) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Failed to send email: " + e.getMessage());
	    }
	}*/
	
	public ResponseEntity<String> sendEmails(ContactRequest contactRequest) {

	    try {

	        // ========== 1️ Mail to USER (Professional HTML) ==========
	        MimeMessage userMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper userHelper =
	                new MimeMessageHelper(userMessage, true, "UTF-8");

	        userHelper.setTo(contactRequest.getEmail());
	        userHelper.setSubject("Thank You for Contacting me");

	        String userHtml = """
	            <div style="font-family: Arial, sans-serif; padding:20px; background:#f4f4f4;">
	                <div style="max-width:600px; margin:auto; background:white; padding:30px; border-radius:8px;">
	                    
	                    <h2 style="color:#4CAF50;">Thank You, %s 👋</h2>
	                    
	                    <p>I have successfully received your message.</p>
	                    
	                    <div style="background:#f9f9f9; padding:15px; border-left:4px solid #4CAF50;">
	                        <p><strong>Your Message:</strong></p>
	                        <p>%s</p>
	                    </div>
	                    
	                    <p style="margin-top:20px;">
	                        Our team will review your request and get back to you shortly.
	                    </p>

	                    <hr style="margin:30px 0;">
	                    
	                    <p style="font-size:12px; color:gray;">
	                        This is an automated email. Please do not reply.
	                    </p>
	                </div>
	            </div>
	        """.formatted(
	                contactRequest.getName(),
	                contactRequest.getMessage()
	        );

	        userHelper.setText(userHtml, true);
	        javaMailSender.send(userMessage);


	        // ========== 2️⃣ Mail to YOU (Admin Notification) ==========
	        MimeMessage adminMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper adminHelper =
	                new MimeMessageHelper(adminMessage, true, "UTF-8");

	        adminHelper.setTo("javadev.nikhilkute@gmail.com");
	        adminHelper.setSubject("🚀 New Contact Form Submission");

	        String adminHtml = """
	            <div style="font-family: Arial, sans-serif; padding:20px;">
	                <h2>New Contact Form Submission</h2>
	                <table style="border-collapse: collapse; width:100%%;">
	                    <tr>
	                        <td style="padding:8px; border:1px solid #ddd;"><strong>Name</strong></td>
	                        <td style="padding:8px; border:1px solid #ddd;">%s</td>
	                    </tr>
	                    <tr>
	                        <td style="padding:8px; border:1px solid #ddd;"><strong>Email</strong></td>
	                        <td style="padding:8px; border:1px solid #ddd;">%s</td>
	                    </tr>
	                    <tr>
	                        <td style="padding:8px; border:1px solid #ddd;"><strong>Message</strong></td>
	                        <td style="padding:8px; border:1px solid #ddd;">%s</td>
	                    </tr>
	                </table>
	            </div>
	        """.formatted(
	                contactRequest.getName(),
	                contactRequest.getEmail(),
	                contactRequest.getMessage()
	        );

	        adminHelper.setText(adminHtml, true);
	        javaMailSender.send(adminMessage);

	        return ResponseEntity.ok(
	        	    "Thank you for contacting me. I have received your message and will respond shortly."
	        	);

	    } catch (Exception e) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Failed to send email: " + e.getMessage());
	    }
	}
}
