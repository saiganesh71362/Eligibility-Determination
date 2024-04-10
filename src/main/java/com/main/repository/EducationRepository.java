package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.EducationEntity_Dc;

@Repository
public interface EducationRepository extends JpaRepository<EducationEntity_Dc, Integer> {
	public EducationEntity_Dc findByCaseNum(Long caseNum);


}
