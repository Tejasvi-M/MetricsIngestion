package com.aryaapps.metricsingestion.services;


import org.springframework.stereotype.Service;

@Service
public class ValidateRange {

	public boolean validate(float value) {
		if(0.0 <= value && value <= 100.0)
		    return true;
		else
			return false;
	}
	
}
