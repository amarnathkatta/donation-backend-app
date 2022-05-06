package com.wellsfargo.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellsfargo.test.entity.NgoEntity;

@Repository
public interface NgoRepository extends JpaRepository<NgoEntity, Long> {

}
