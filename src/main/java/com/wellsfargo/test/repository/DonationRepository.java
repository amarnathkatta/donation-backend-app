package com.wellsfargo.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellsfargo.test.entity.DonationEntity;

@Repository
public interface DonationRepository extends JpaRepository<DonationEntity, Long> {

	List<DonationEntity> findByDonarId(Long donarId);

}
