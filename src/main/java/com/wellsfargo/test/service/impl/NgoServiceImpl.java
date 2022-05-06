package com.wellsfargo.test.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.test.dto.NgoDto;
import com.wellsfargo.test.entity.NgoEntity;
import com.wellsfargo.test.repository.NgoRepository;
import com.wellsfargo.test.service.NgoService;

@Service
public class NgoServiceImpl implements NgoService {

	@Autowired
	private NgoRepository ngoRepository;

	@Override
	public NgoDto registerNgo(NgoDto ngoDto) {
		NgoEntity entity = new NgoEntity();
		BeanUtils.copyProperties(ngoDto, entity);
		ngoRepository.save(entity);
		return ngoDto;
	}

}
