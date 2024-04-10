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
@Table(name = "CHILDREN_ENTITY_DC")
public class ChildernEntity_Dc 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer childId;
	
	private String childName;
	
	private Integer childAge;
	
	private Long ssn;
	
	private Long caseNum;
	
	// Audit Columns
	
	private String  createdBy;
	private String updateBy;
	@CreationTimestamp
	@Column(updatable  = false)
	private LocalDate createdDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updateDate;


}
