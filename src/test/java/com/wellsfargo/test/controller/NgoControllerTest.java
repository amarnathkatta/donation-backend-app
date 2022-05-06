package com.wellsfargo.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

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
import com.wellsfargo.test.dto.NgoDto;
import com.wellsfargo.test.service.NgoService;

@WebMvcTest(NgoController.class)
@AutoConfigureMockMvc
class NgoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NgoService ngoService;

	private ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	public void setup() {
		mapper.registerModule(new JavaTimeModule());
	}

	@Test
	void testRegisterNgo() throws Exception {
		NgoDto ngoDto = createNgoDto();
		NgoDto savedNgoDto = createNgoDto();
		savedNgoDto.setId(123L);

		when(this.ngoService.registerNgo(ngoDto)).thenReturn(savedNgoDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/e-donation/api/v1/ngos/register-ngo")
				.content(this.mapper.writeValueAsString(ngoDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals(mapper.writeValueAsString(savedNgoDto), result.getResponse().getContentAsString());
	}
	
	@Test
	void testRegisterNgo_whenInvalidData() throws Exception {
		NgoDto ngoDto = createNgoDto();
		ngoDto.setNgoName("A");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/e-donation/api/v1/ngos/register-ngo")
				.content(this.mapper.writeValueAsString(ngoDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(400, result.getResponse().getStatus());
	}

	private NgoDto createNgoDto() {
		NgoDto ngoDto = new NgoDto();
		ngoDto.setId(1L);
		ngoDto.setNgoName("TATA");
		ngoDto.setAddress("Hyderabad");
		ngoDto.setUsername("user1");
		ngoDto.setPassword("3443433");
		ngoDto.setPhoneNumber(9898567878L);
		ngoDto.setStartedIn(LocalDate.of(2020, 05, 20));
		return ngoDto;
	}

}
