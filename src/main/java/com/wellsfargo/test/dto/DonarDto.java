package com.wellsfargo.test.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(of = { "id" })
public class DonarDto {

	private Long id;

	@NotNull
	private Long ngoId;

	@NotBlank
	@Length(min = 3, max = 100)
	private String name;

	@NotBlank
	@Length(min = 3, max = 50)
	private String username;

	@NotBlank
	@Length(min = 3, max = 50)
	private String password;

	@NotBlank
	@Length(min = 3, max = 100)
	@Email
	private String emailId;

	@NotNull
	@Min(value = 1000000000L)
	@Max(value = 9999999999L)
	private Long phoneNumber;

	@NotBlank
	@Length(min = 3, max = 100)
	private String address;

}
