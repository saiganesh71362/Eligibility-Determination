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
@Table(name = "ELIGIBILITY_DL_ENTITY_DC")
public class EligiblityDlEntity_Dc 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer edTraceId;

	private Long caseNum;

	private String holderName;

	private Long holderSsn;

	private String planName;

	private String planStatus;

	private LocalDate planStartDate;

	private LocalDate planEndDate;

	private Double benefitAmt;

	private String denialReason;
	
	private String createdBy;
	private String updatedBy;
	@CreationTimestamp
	@Column(updatable = false )
	private LocalDate createdDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updatedDate;


}
