package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.EligiblityDlEntity_Dc;

@Repository
public interface EligiblityDlRepository extends JpaRepository<EligiblityDlEntity_Dc, Integer> 
{

}
