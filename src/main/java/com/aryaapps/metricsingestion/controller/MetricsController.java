package com.aryaapps.metricsingestion.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aryaapps.metricsingestion.dto.SystemMetrics;
import com.aryaapps.metricsingestion.repository.MetricsRepository;

@RestController
public class MetricsController {

	@Autowired
	MetricsRepository metricsRepository;
	
	@Autowired
	HttpServletRequest httpServletRequest;
	
	
	
	@PostMapping("/metrics")
	public ResponseEntity<?> insertMetrics(@RequestBody Map<String,Integer>userData) {
		SystemMetrics systemMetrics=new SystemMetrics();
		String clientIP=httpServletRequest.getRemoteAddr();
		systemMetrics.setIp(clientIP);
		systemMetrics.setMaxCpu(userData.get("percentage_cpu_used"));
		systemMetrics.setMaxMemory(userData.get("percentage_memory_used"));
		try{
			if(!metricsRepository.existsById((clientIP)))
				metricsRepository.save(systemMetrics);
			else
			    metricsRepository.updateMetricsByIp(userData.get("percentage_cpu_used"), userData.get("percentage_memory_used"), clientIP);	
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception ex) {
			System.out.print(ex.getStackTrace()+ "\n"+ ex.getCause());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@GetMapping("/report")
	public ResponseEntity<?> getAllMetrics(){
		try {
			return  ResponseEntity.status(HttpStatus.OK).body(metricsRepository.findAll());	
		}catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
