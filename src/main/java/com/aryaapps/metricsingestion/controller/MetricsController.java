package com.aryaapps.metricsingestion.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aryaapps.metricsingestion.dto.SystemMetrics;
import com.aryaapps.metricsingestion.repository.MetricsRepository;
import com.aryaapps.metricsingestion.services.ValidateRange;

@RestController
public class MetricsController {

	@Autowired
	MetricsRepository metricsRepository;
	
	@Autowired
	HttpServletRequest httpServletRequest;
	
	@Autowired
	ValidateRange validateRange;
	
	
	
	@PostMapping("/metrics")
	public ResponseEntity<?> insertMetrics(@Valid @RequestBody Map<String,Integer> userData) {
		
		SystemMetrics systemMetrics=new SystemMetrics();
		String clientIP=httpServletRequest.getRemoteAddr();
		int maxCpu=userData.get("percentage_cpu_used");
		int maxMemory=userData.get("percentage_memory_used");
		
		if(!validateRange.validate(maxCpu)||!validateRange.validate(maxMemory)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Range of Used CPU or Memory must be invalid. The correct range is 0-100");
		}
		
		
		systemMetrics.setIp(clientIP);
		systemMetrics.setMaxCpu(maxCpu);
		systemMetrics.setMaxMemory(maxMemory);
		
		try{
				metricsRepository.save(systemMetrics);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception ex) {
			System.out.println(ex.getCause());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	
	@GetMapping("/report")
	public ResponseEntity<?> getAllMetrics(){
		try {
			return  ResponseEntity.status(HttpStatus.OK).body(metricsRepository.returnMaxCpuAndMaxMemoryOfHosts());	
		}catch(Exception ex) {
			return new ResponseEntity<>(ex.getCause(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
