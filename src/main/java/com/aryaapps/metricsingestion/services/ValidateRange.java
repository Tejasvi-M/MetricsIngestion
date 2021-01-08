package com.aryaapps.metricsingestion.services;

import java.time.temporal.ValueRange;

import org.springframework.stereotype.Service;

@Service
public class ValidateRange {

	public boolean validate(int value) {
		ValueRange range = java.time.temporal.ValueRange.of(0, 100);
		return range.isValidIntValue(value);
	}
	
}
