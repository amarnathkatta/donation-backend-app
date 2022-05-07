package com.wellsfargo.test.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(of = { "id" })
public class DonationDto {

	private Long id;

	@NotNull
	private Long donarId;

	@NotNull
	private Long ngoId;

	@NotEmpty
	@Length(min = 3, max = 100)
	private String donationType;

	@NotNull
	private Double amount;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Future
	private LocalDate donationDate;

}
