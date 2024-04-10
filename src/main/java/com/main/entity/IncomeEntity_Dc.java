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
@Table(name = "INCOME_ENTITY_DC")
public class IncomeEntity_Dc
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer incomeId;
	
	private Long caseNum;
	
	private Double empIncome;
	
	private Double propertyIncome;
	
	private Double rentIncome;
	
	private String createdBy;
	private String updateBy;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate cretedDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updatedDate;


}
