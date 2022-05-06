package com.wellsfargo.test.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wellsfargo.test.dto.DonarDto;
import com.wellsfargo.test.entity.DonarEntity;
import com.wellsfargo.test.exception.DonarNotFoundException;
import com.wellsfargo.test.repository.DonarRepository;

@ExtendWith(MockitoExtension.class)
class DonarServiceImplTest {

	@Mock
	private DonarRepository donarRepository;

	@InjectMocks
	private DonarServiceImpl donarServiceImpl;

	@Test
	void testRegisterDonar() {
		DonarDto donarDto = createDonarDto();
		doReturn(new DonarEntity()).when(donarRepository).save(Mockito.any(DonarEntity.class));
		assertNotNull(donarServiceImpl.registerDonar(donarDto));
	}

	@Test
	void testUpdateDonar() {
		DonarDto donarDto = createDonarDto();
		doReturn(new DonarEntity()).when(donarRepository).save(Mockito.any(DonarEntity.class));
		assertNotNull(donarServiceImpl.updateDonar(donarDto));
	}

	@Test
	void testDeleteDonar() {
		assertDoesNotThrow(() -> {
			doReturn(Optional.of(new DonarEntity())).when(donarRepository).findById(Mockito.anyLong());
			donarServiceImpl.deleteDonar(1L);
		});
	}

	@Test
	void testDeleteWhenNoDbEntity() {
		doReturn(Optional.empty()).when(donarRepository).findById(Mockito.anyLong());
		assertThrows(DonarNotFoundException.class, () -> donarServiceImpl.deleteDonar(1L));
	}

	@Test
	void testGetAllDonars() {
		doReturn(Arrays.asList(new DonarEntity())).when(donarRepository).findAll();
		assertEquals(1, donarServiceImpl.getAllDonars().size());
	}

	@Test
	void testGetDonarsRegisteredWithNgo() {
		doReturn(Arrays.asList(new DonarEntity())).when(donarRepository).findByNgoId(Mockito.anyLong());
		assertEquals(1, donarServiceImpl.getDonarsRegisteredWithNgo(1L).size());
	}

	private DonarDto createDonarDto() {
		DonarDto donarDto = new DonarDto();
		donarDto.setId(1L);
		donarDto.setName("PQRS");
		donarDto.setNgoId(1L);
		donarDto.setEmailId("pqrs@xyz.com");
		donarDto.setAddress("Hyderabad, Telangana");
		donarDto.setUsername("test");
		donarDto.setPassword("Aqww");
		donarDto.setPhoneNumber(9878457845L);
		return donarDto;
	}

}
