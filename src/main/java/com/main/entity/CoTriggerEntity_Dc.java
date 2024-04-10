package com.main.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "CO_TRIGGERS_DC")
public class CoTriggerEntity_Dc
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer trgId;

	private Long caseNum;

	@Lob
	private byte[] coPdf;

	private String trgStatus;
	
	private String createdBy;
	private String updateBy;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate cretedDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updatedDate;

}
