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
@Table(name = "CITIZEN_APPS_DC")
@Data
public class CitizenApiEntity_Dc
{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer AppId;
	
	private String fullName;
	
	private String email;
	
	private Long phNo;
	
	private String gender;
	
	private Long ssn;
	
	private String stateName;
	
	private LocalDate dob;
	
	// Audit Columns
	
	private String createdBy;
	private String updateBy;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createdDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updateDate;
	

}
