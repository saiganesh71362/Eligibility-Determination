package com.main.service;

import com.main.response.EligiblityResponse;

public interface EligiblityDtService 
{

	public EligiblityResponse determineEligibility(Long caseNum);

}
