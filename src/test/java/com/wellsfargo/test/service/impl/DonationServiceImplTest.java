package com.wellsfargo.test.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wellsfargo.test.dto.DonationDto;
import com.wellsfargo.test.entity.DonationEntity;
import com.wellsfargo.test.repository.DonationRepository;

@ExtendWith(MockitoExtension.class)
class DonationServiceImplTest {

	@Mock
	private DonationRepository donationRepository;

	@InjectMocks
	private DonationServiceImpl donationServiceImpl;

	@Test
	void testRegisterDonation() {
		doReturn(new DonationEntity()).when(donationRepository).save(Mockito.any(DonationEntity.class));
		assertNotNull(donationServiceImpl.registerDonation(createDonationDto()));
	}

	@Test
	void testGetDonationsByDonorId() {
		doReturn(Arrays.asList(new DonationEntity())).when(donationRepository).findByDonarId(Mockito.anyLong());
		assertEquals(1, donationServiceImpl.getDonationsByDonorId(1L).size());
	}

	private DonationDto createDonationDto() {
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
