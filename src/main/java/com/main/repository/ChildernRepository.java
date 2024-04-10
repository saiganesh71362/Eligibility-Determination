package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.ChildernEntity_Dc;

@Repository
public interface ChildernRepository extends JpaRepository<ChildernEntity_Dc, Integer>
{
	public List<ChildernEntity_Dc> findByCaseNum(Long caseNum);

}