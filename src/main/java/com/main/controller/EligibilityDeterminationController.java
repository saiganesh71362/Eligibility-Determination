package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.main.response.EligiblityResponse;
import com.main.service.EligiblityDtService;

@RestController
public class EligibilityDeterminationController
{
	@Autowired
	private EligiblityDtService eligDtService;
	
    @GetMapping("/eligibility/{caseNum}")
    public ResponseEntity<EligiblityResponse> determineEligibility(@PathVariable Long caseNum) {
        EligiblityResponse eligibilityResponse = eligDtService.determineEligibility(caseNum);
        return new ResponseEntity<>(eligibilityResponse, HttpStatus.OK);
    }

}
