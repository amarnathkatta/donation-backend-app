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
@Table(name = "ngo")
@Setter
@Getter
public class NgoEntity {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String ngoName;

	@Column(name = "user_name")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "address")
	private String address;

	@Column(name = "phone_number")
	private Long phoneNumber;

	@Column(name = "started_in")
	private LocalDate startedIn;

}
