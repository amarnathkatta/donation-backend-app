package com.wellsfargo.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "donar")
@Setter
@Getter
public class DonarEntity {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "ngo_id")
	private Long ngoId;

	@Column(name = "name")
	private String name;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "phone_number")
	private Long phoneNumber;

	@Column(name = "address")
	private String address;

}
