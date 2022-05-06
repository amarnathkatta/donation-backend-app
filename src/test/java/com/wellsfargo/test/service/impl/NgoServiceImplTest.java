package com.wellsfargo.test.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wellsfargo.test.dto.NgoDto;
import com.wellsfargo.test.entity.NgoEntity;
import com.wellsfargo.test.repository.NgoRepository;

@ExtendWith(MockitoExtension.class)
public class NgoServiceImplTest {

	@Mock
	private NgoRepository ngoRepository;

	@InjectMocks
	private NgoServiceImpl ngoServiceImpl;

	@Test
	public void testRegisterNgo() {
		doReturn(new NgoEntity()).when(ngoRepository).save(Mockito.any(NgoEntity.class));
		assertNotNull(ngoServiceImpl.registerNgo(createNgoDto()));
	}

	public static NgoDto createNgoDto() {
		NgoDto ngoDto = new NgoDto();
		ngoDto.setId(1L);
		ngoDto.setPassword("3443433");
		ngoDto.setAddress("Hyderabad");
		ngoDto.setNgoName("TATA");
		ngoDto.setUsername("user1");
		ngoDto.setPhoneNumber(9898567878L);
		ngoDto.setStartedIn(LocalDate.of(2020, 05, 20));
		return ngoDto;
	}
}
