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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.test.dto.DonarDto;
import com.wellsfargo.test.exception.InvalidDataException;
import com.wellsfargo.test.service.DonarService;

@RestController
//@CrossOrigin
@RequestMapping("/e-donation/api/v1/donors")
public class DonarController {

	@Autowired
	private DonarService donarService;

	@PostMapping("/register-donar")
	public ResponseEntity<DonarDto> registerDonar(@Valid @RequestBody DonarDto donarDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("The Data is not valid");
		}
		DonarDto respDto = donarService.registerDonar(donarDto);
		return ResponseEntity.ok(respDto);
	}

	@PutMapping("/update-donar")
	public ResponseEntity<DonarDto> updateDonar(@Valid @RequestBody DonarDto donarDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("The Data is not valid");
		}
		DonarDto respDto = donarService.updateDonar(donarDto);
		return ResponseEntity.ok(respDto);
	}

	@GetMapping("/all")
	public ResponseEntity<List<DonarDto>> getAllDonars() {
		List<DonarDto> donars = donarService.getAllDonars();
		return ResponseEntity.ok(donars);
	}

	@GetMapping("/by-ngo-id/{ngoId}")
	public ResponseEntity<List<DonarDto>> getDonarByNgoId(@PathVariable Long ngoId) {
		List<DonarDto> donars = donarService.getDonarsRegisteredWithNgo(ngoId);
		return ResponseEntity.ok(donars);
	}

}
