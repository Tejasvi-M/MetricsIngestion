package com.aryaapps.metricsingestion.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SystemMetrics {
	
	private int id;
	private String ip;
	private int maxCpu;
	private int maxMemory;
	




	@Id
	@Column(name = "ip", nullable = false)
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Column(name = "percentage_cpu_used", nullable = false)
	public int getMaxCpu() {
		return maxCpu;
	}
	
	public void setMaxCpu(int maxCpu) {
		this.maxCpu = maxCpu;
	}
	
	
	@Column(name = "percentage_memory_used", nullable = false)
	public int getMaxMemory() {
		return maxMemory;
	}
	
	
	public void setMaxMemory(int maxMemory) {
		this.maxMemory = maxMemory;
	}
	
	
}
