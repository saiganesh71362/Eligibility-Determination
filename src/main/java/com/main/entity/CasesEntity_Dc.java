package com.main.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "CASES_ENTITY_DC")
public class CasesEntity_Dc 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CaseNum;
	
	private Integer appId;
	
	private Integer planId;
	
	private String createdBy;
	private String updatedBy;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createdDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updatedDate;
	
	


}
