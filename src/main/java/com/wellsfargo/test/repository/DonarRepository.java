package com.wellsfargo.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellsfargo.test.entity.DonarEntity;

@Repository
public interface DonarRepository extends JpaRepository<DonarEntity, Long> {

	List<DonarEntity> findByNgoId(Long ngoId);

}
