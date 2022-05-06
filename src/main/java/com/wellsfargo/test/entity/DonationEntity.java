package com.wellsfargo.test.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "donation")
@Setter
@Getter
public class DonationEntity {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "donar_id")
	private Long donarId;

	@Column(name = "ngo_id")
	private Long ngoId;

	@Column(name = "donation_type")
	private String donationType;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "donation_date")
	private LocalDate donationDate;

}
