package com.wellsfargo.test.service;

import java.util.List;

import com.wellsfargo.test.dto.DonarDto;

public interface DonarService {

	public DonarDto registerDonar(DonarDto donarDto);

	public DonarDto updateDonar(DonarDto donarDto);

	public Boolean deleteDonar(Long donarid);

	public DonarDto getDonarById(Long donarId);

	public List<DonarDto> getAllDonars();

	public List<DonarDto> getDonarsRegisteredWithNgo(Long ngoId);

}
