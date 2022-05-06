package com.wellsfargo.test.service;

import java.util.List;

import com.wellsfargo.test.dto.DonationDto;

public interface DonationService {

	public DonationDto registerDonation(DonationDto donationDto);

	public List<DonationDto> getDonationsByDonorId(Long donarId);

}
