package com.wellsfargo.test.dto;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(of = { "id" })
public class NgoDto {

	private Long id;

	@NotBlank
	@Length(min = 3, max = 100)
	private String ngoName;

	@NotBlank
	@Length(min = 3, max = 50)
	private String username;

	@NotBlank
	@Length(min = 3, max = 50)
	private String password;

	@NotBlank
	@Length(min = 3, max = 50)
	private String address;

	@NotNull
	@Min(value = 1000000000)
	@Max(value = 9999999999L)
	private Long phoneNumber;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Past
	private LocalDate startedIn;

}
