package com.wellsfargo.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

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
import com.wellsfargo.test.dto.DonarDto;
import com.wellsfargo.test.service.DonarService;

@WebMvcTest(DonarController.class)
@AutoConfigureMockMvc
public class DonarControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DonarService donarService;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testRegisterDonar() throws Exception {
		DonarDto donarDto = createDonarDto();
		DonarDto savedDonarDto = createDonarDto();
		savedDonarDto.setId(123L);

		when(this.donarService.registerDonar(donarDto)).thenReturn(savedDonarDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/e-donation/api/v1/donors/register-donar")
				.content(this.mapper.writeValueAsString(donarDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals(mapper.writeValueAsString(savedDonarDto), result.getResponse().getContentAsString());
	}

	@Test
	public void testRegisterDonar_whenInvalidData() throws Exception {
		DonarDto donarDto = createDonarDto();
		donarDto.setName("A");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/e-donation/api/v1/donors/register-donar")
				.content(this.mapper.writeValueAsString(donarDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(400, result.getResponse().getStatus());
	}

	@Test
	public void testUpdateDonar() throws Exception {
		DonarDto donarDto = createDonarDto();
		DonarDto updatedDonarDto = createDonarDto();
		updatedDonarDto.setAddress("New Address");

		when(this.donarService.updateDonar(donarDto)).thenReturn(updatedDonarDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/e-donation/api/v1/donors/update-donar")
				.content(this.mapper.writeValueAsString(donarDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals(mapper.writeValueAsString(updatedDonarDto), result.getResponse().getContentAsString());
	}

	@Test
	public void testUpdateDonar_whenInvalidData() throws Exception {
		DonarDto donarDto = createDonarDto();
		donarDto.setPhoneNumber(123L);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/e-donation/api/v1/donors/update-donar")
				.content(this.mapper.writeValueAsString(donarDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(400, result.getResponse().getStatus());
	}

	@Test
	public void testGetAllDonars() throws Exception {
		List<DonarDto> donorDtos = Arrays.asList(createDonarDto());
		when(this.donarService.getAllDonars()).thenReturn(donorDtos);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/e-donation/api/v1/donors/all")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals(mapper.writeValueAsString(donorDtos), result.getResponse().getContentAsString());
	}

	@Test
	public void testGetDonarByNgoId() throws Exception {
		List<DonarDto> donorDtos = Arrays.asList(createDonarDto());
		when(this.donarService.getDonarsRegisteredWithNgo(1L)).thenReturn(donorDtos);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/e-donation/api/v1/donors/by-ngo-id/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertEquals(mapper.writeValueAsString(donorDtos), result.getResponse().getContentAsString());
	}

	public static DonarDto createDonarDto() {
		DonarDto donarDto = new DonarDto();
		donarDto.setId(1L);
		donarDto.setName("Xyz");
		donarDto.setEmailId("xyz@pqr.com");
		donarDto.setNgoId(1L);
		donarDto.setUsername("xyztest");
		donarDto.setPassword("123Aqww");
		donarDto.setAddress("Hyderabad, Telangana");
		donarDto.setPhoneNumber(9878457845L);
		return donarDto;
	}

}
