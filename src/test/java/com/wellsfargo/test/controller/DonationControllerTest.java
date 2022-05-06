package com.wellsfargo.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wellsfargo.test.dto.DonationDto;
import com.wellsfargo.test.service.DonationService;

@WebMvcTest(DonationController.class)
@AutoConfigureMockMvc
public class DonationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DonationService donationService;

	private ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	public void setup() {
		mapper.registerModule(new JavaTimeModule());
	}

	@Test
	public void testAddDonation() throws Exception {
		DonationDto donationDto = createDonationDto();
		DonationDto savedDonationDto = createDonationDto();
		savedDonationDto.setId(123L);

		when(this.donationService.registerDonation(donationDto)).thenReturn(savedDonationDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/e-donation/api/v1/donations/add-donation")
				.content(this.mapper.writeValueAsString(donationDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals(mapper.writeValueAsString(savedDonationDto), result.getResponse().getContentAsString());
	}

	@Test
	public void testAddDonation_whenDataIsInvalid() throws Exception {
		DonationDto donationDto = createDonationDto();
		donationDto.setDonationType("A");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/e-donation/api/v1/donations/add-donation")
				.content(this.mapper.writeValueAsString(donationDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(400, result.getResponse().getStatus());
	}

	@Test
	public void testGetDonationsByDonorId() throws Exception {
		List<DonationDto> donationDtos = Arrays.asList(createDonationDto());
		when(this.donationService.getDonationsByDonorId(1L)).thenReturn(donationDtos);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/e-donation/api/v1/donations/by-id/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals(mapper.writeValueAsString(donationDtos), result.getResponse().getContentAsString());
	}

	public static DonationDto createDonationDto() {
		DonationDto donationDto = new DonationDto();
		donationDto.setId(1L);
		donationDto.setNgoId(1L);
		donationDto.setDonarId(1L);
		donationDto.setAmount(50000.0);
		donationDto.setDonationDate(LocalDate.now().plusDays(2));
		donationDto.setDonationType("Health");
		return donationDto;
	}

}
