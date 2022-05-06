package com.wellsfargo.test.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.test.dto.DonationDto;
import com.wellsfargo.test.exception.InvalidDataException;
import com.wellsfargo.test.service.DonationService;

@RestController
@RequestMapping("/e-donation/api/v1/donations")
@CrossOrigin
public class DonationController {

	@Autowired
	private DonationService donationService;

	@PostMapping("/add-donation")
	public ResponseEntity<DonationDto> addDonation(@Valid @RequestBody DonationDto donationDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("The Data is not valid");
		}
		DonationDto respDto = donationService.registerDonation(donationDto);
		return ResponseEntity.ok(respDto);
	}

	@GetMapping("/by-id/{donorId}")
	public ResponseEntity<List<DonationDto>> getDonationsByDonorId(@PathVariable("donorId") Long donarId) {
		return ResponseEntity.ok(donationService.getDonationsByDonorId(donarId));
	}

}
