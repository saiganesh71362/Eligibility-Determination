package com.main.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.CasesEntity_Dc;
import com.main.entity.ChildernEntity_Dc;
import com.main.entity.CitizenApiEntity_Dc;
import com.main.entity.CoTriggerEntity_Dc;
import com.main.entity.EducationEntity_Dc;
import com.main.entity.EligiblityDlEntity_Dc;
import com.main.entity.IncomeEntity_Dc;
import com.main.entity.PlanEntity_Dc;
import com.main.repository.CasesRepository;
import com.main.repository.ChildernRepository;
import com.main.repository.CitizenApiRepository;
import com.main.repository.CoTriggerRepository;
import com.main.repository.EducationRepository;
import com.main.repository.EligiblityDlRepository;
import com.main.repository.IncomeRepository;
import com.main.repository.PlanRepository;
import com.main.response.EligiblityResponse;

@Service
public class EligibilityDtImplementation implements EligiblityDtService 
{
	@Autowired
	private CasesRepository dccasesRepository;
	@Autowired
	private ChildernRepository childernRepository;
	@Autowired
	private CitizenApiRepository citizenApiRepository;
	@Autowired
	private CoTriggerRepository coTriggerRepository;
	@Autowired
	private EducationRepository educationRepository;
	@Autowired
	private EligiblityDlRepository eligibilityDlRepository;
	@Autowired
	private IncomeRepository incomeRepository;
	@Autowired
	private PlanRepository planRepository;

	@Override
	public EligiblityResponse determineEligibility(Long caseNum)
	{
	
		Optional<CasesEntity_Dc> caseEntity = dccasesRepository.findById(caseNum);
		Integer planId = null;
		String planName = null;
		Integer appId = null;

		if (caseEntity.isPresent()) {
			planId = caseEntity.get().getPlanId();
			appId = caseEntity.get().getAppId();
		}

		Optional<PlanEntity_Dc> planEntity = planRepository.findById(planId);
		if (planEntity.isPresent()) {
			PlanEntity_Dc plan = planEntity.get();
			planName = plan.getPlanName();
		}
		Optional<CitizenApiEntity_Dc> findById = citizenApiRepository.findById(appId);
		Integer age =0;
		CitizenApiEntity_Dc citizenApiEntity =null;
		if (findById.isPresent()) {
			citizenApiEntity = findById.get();
			LocalDate dob = citizenApiEntity.getDob();
			LocalDate now = LocalDate.now();
			age = Period.between(dob, now).getYears();
		}
		EligiblityResponse eligResponse = executePlanConditions(caseNum, planName, age);
		EligiblityDlEntity_Dc eligEntity = new EligiblityDlEntity_Dc();
		BeanUtils.copyProperties(eligResponse, eligEntity);

		eligEntity.setCaseNum(caseNum);
		eligEntity.setHolderName(citizenApiEntity.getFullName());
		eligEntity.setHolderSsn(citizenApiEntity.getSsn());
		eligibilityDlRepository.save(eligEntity);
		
		CoTriggerEntity_Dc coEntity = new CoTriggerEntity_Dc();
		coEntity.setCaseNum(caseNum);
		coEntity.setTrgStatus("Pending");
		
		coTriggerRepository.save(coEntity);
		return eligResponse;
	}

	private EligiblityResponse executePlanConditions(Long caseNum, String planName, Integer age) {
		EligiblityResponse response = new EligiblityResponse();
		response.setPlanName(planName);

		IncomeEntity_Dc income = incomeRepository.findByCaseNum(caseNum);
		if ("SNAP".equals(planName)) {
			Double empIncome = income.getEmpIncome();
			if (empIncome <= 300) {
				response.setPlanStatus("Approved");
			} else {
				response.setPlanStatus("Denied");
				response.setDenialReason("High Income");
			}

		} else if ("CCAP".equals(planName)) {

			boolean ageCondition = true;
			boolean kidsCountCondition = false;
			List<ChildernEntity_Dc> childs = childernRepository.findByCaseNum(caseNum);
			if (!childs.isEmpty()) {
				kidsCountCondition = true;
				for (ChildernEntity_Dc entity : childs) {
					Integer childAge = entity.getChildAge();
					if (childAge > 16) {
						ageCondition = false;
						break;
					}
				}

			}
			if (income.getEmpIncome() <= 300 && kidsCountCondition && ageCondition) {
				response.setPlanStatus("Approved");
			} else {
				response.setPlanStatus("Denied");
				response.setDenialReason("Not satisfied busines rules");
			}

		} else if ("Medicaid".equals(planName)) {
			Double empIncome = income.getEmpIncome();
			Double propertyIncome = income.getPropertyIncome();
			if (empIncome <= 300 && propertyIncome == 0) {
				response.setPlanStatus("Approved");
			} else {
				response.setPlanStatus("Denied");
				response.setDenialReason("High Income");
			}

		} else if ("Medicare".equals(planName)) {
			
				if (age >= 65) {
					response.setPlanStatus("Approved");
				} else {
					response.setPlanStatus("Denied");
					response.setDenialReason("Age not matched");
				}
			

		}else if ("NJW".equals(planName)) {
			EducationEntity_Dc educationEntity = educationRepository.findByCaseNum(caseNum);
			Integer graduationYear = educationEntity.getGraduationYear();
			int currentYear = LocalDate.now().getYear();
			if (income.getEmpIncome() <= 0 && graduationYear < currentYear) {
				response.setPlanStatus("Approved");
			} else {
				response.setPlanStatus("Denied");
				response.setDenialReason("Rules not satisfied");
			}
		}
		if (response.getPlanStatus() == "Approved") {

			response.setPlanStartDate(LocalDate.now());
			response.setPlanEndDate(LocalDate.now().plusMonths(6));
			response.setBenefitAmt(350.00);
		}

		return response;


	}

}
