package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.CoTriggerEntity_Dc;

@Repository
public interface CoTriggerRepository extends JpaRepository<CoTriggerEntity_Dc, Integer>{

}
