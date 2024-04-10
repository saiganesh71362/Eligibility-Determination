package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.IncomeEntity_Dc;

@Repository
public interface IncomeRepository  extends JpaRepository<IncomeEntity_Dc, Integer>{
	public IncomeEntity_Dc findByCaseNum(Long caseNum);

}
