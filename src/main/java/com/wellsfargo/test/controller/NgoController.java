package com.wellsfargo.test.controller;

import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.test.dto.NgoDto;
import com.wellsfargo.test.exception.InvalidDataException;
import com.wellsfargo.test.service.NgoService;

@RestController
@RequestMapping("/e-donation/api/v1/ngos")
public class NgoController {

	@Autowired
	private NgoService ngoService;

	@PostMapping("/register-ngo")
	public ResponseEntity<NgoDto> registerNgo(@Valid @AssertFalse @RequestBody NgoDto ngoDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("The Data is not valid");
		}
		NgoDto responseDto = ngoService.registerNgo(ngoDto);
		return ResponseEntity.ok(responseDto);
	}

}
