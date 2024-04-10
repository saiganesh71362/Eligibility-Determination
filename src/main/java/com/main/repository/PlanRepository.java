package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.PlanEntity_Dc;

@Repository
public interface PlanRepository  extends JpaRepository<PlanEntity_Dc, Integer>{

}
