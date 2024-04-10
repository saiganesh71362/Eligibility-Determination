package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.CasesEntity_Dc;

@Repository
public interface CasesRepository extends JpaRepository<CasesEntity_Dc, Long> {

}
