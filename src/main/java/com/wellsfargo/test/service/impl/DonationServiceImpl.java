package com.wellsfargo.test.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.test.dto.DonationDto;
import com.wellsfargo.test.entity.DonationEntity;
import com.wellsfargo.test.repository.DonationRepository;
import com.wellsfargo.test.service.DonationService;

@Service
public class DonationServiceImpl implements DonationService {

	@Autowired
	private DonationRepository donationRepository;

	@Override
	public DonationDto registerDonation(DonationDto donationDto) {
		DonationEntity entity = new DonationEntity();
		BeanUtils.copyProperties(donationDto, entity);
		donationRepository.save(entity);
		donationDto.setId(entity.getId());
		return donationDto;
	}

	@Override
	public List<DonationDto> getDonationsByDonorId(Long donarId) {
		List<DonationEntity> list = donationRepository.findByDonarId(donarId);
		List<DonationDto> dtos = new ArrayList<>();
		for (DonationEntity entity : list) {
			DonationDto dto = new DonationDto();
			BeanUtils.copyProperties(entity, dto);
			dtos.add(dto);
		}
		return dtos;
	}

}
