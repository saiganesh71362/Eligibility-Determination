package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.CitizenApiEntity_Dc;

@Repository
public interface CitizenApiRepository extends JpaRepository<CitizenApiEntity_Dc, Integer> {

}
