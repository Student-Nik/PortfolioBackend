package com.portfolio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequest {

	    @NotBlank(message = "Full name is required.")
	    @Size(min = 2, max = 50, message = "Full name must be between 2 and 50 characters.")
	    private String name;

	    @NotBlank(message = "Email address is required.")
	    @Email(message = "Please provide a valid email address.")
	    private String email;

	    @NotBlank(message = "Message cannot be empty.")
	    @Size(min = 10, max = 500, message = "Message must be between 10 and 500 characters.")
	    private String message;
}
