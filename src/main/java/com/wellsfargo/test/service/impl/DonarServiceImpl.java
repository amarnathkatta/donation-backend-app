package com.wellsfargo.test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.test.dto.DonarDto;
import com.wellsfargo.test.entity.DonarEntity;
import com.wellsfargo.test.exception.DonarNotFoundException;
import com.wellsfargo.test.repository.DonarRepository;
import com.wellsfargo.test.service.DonarService;

@Service(value = "donarService")
public class DonarServiceImpl implements DonarService {

	@Autowired
	private DonarRepository donarRepository;

	@Override
	public DonarDto registerDonar(DonarDto donarDto) {
		DonarEntity entity = new DonarEntity();
		BeanUtils.copyProperties(donarDto, entity);
		DonarEntity dbEntity = donarRepository.save(entity);
		donarDto.setId(dbEntity.getId());
		return donarDto;
	}

	@Override
	public DonarDto updateDonar(DonarDto donarDto) {
		DonarEntity entity = new DonarEntity();
		BeanUtils.copyProperties(donarDto, entity);
		donarRepository.save(entity);
		return donarDto;
	}

	@Override
	public Boolean deleteDonar(Long donarid) {
		DonarDto donarById = getDonarById(donarid);
		DonarEntity entity = new DonarEntity();
		BeanUtils.copyProperties(donarById, entity);
		donarRepository.delete(entity);
		return true;
	}

	@Override
	public DonarDto getDonarById(Long donarId) {
		Optional<DonarEntity> entity = donarRepository.findById(donarId);
		if (entity.isPresent()) {
			DonarDto ngoDto = new DonarDto();
			BeanUtils.copyProperties(entity.get(), ngoDto);
			return ngoDto;
		} else {
			throw new DonarNotFoundException("Donar with id " + donarId + " not found");
		}
	}

	@Override
	public List<DonarDto> getAllDonars() {
		List<DonarEntity> list = donarRepository.findAll();
		List<DonarDto> dtos = new ArrayList<>();
		for (DonarEntity entity : list) {
			DonarDto dto = new DonarDto();
			BeanUtils.copyProperties(entity, dto);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<DonarDto> getDonarsRegisteredWithNgo(Long ngoId) {
		List<DonarEntity> list = donarRepository.findByNgoId(ngoId);
		List<DonarDto> dtos = new ArrayList<>();
		for (DonarEntity entity : list) {
			DonarDto dto = new DonarDto();
			BeanUtils.copyProperties(entity, dto);
			dtos.add(dto);
		}
		return dtos;
	}

}
