package com.aryaapps.metricsingestion.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class SystemMetrics {
	
	private String ip;
	private int maxCpu;
	private int maxMemory;
	private int id;

	public SystemMetrics(){}
		
	public SystemMetrics(String ip,int maxCpu, int maxMemory){
		this.maxCpu=maxCpu;
		this.maxMemory=maxMemory;
		this.ip=ip;
	}
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "ip", nullable = false)
	@JsonProperty(value="ip")
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Column(name = "percentage_cpu_used", nullable = false)
	@Min(value=0,message="Usage is in percentage, range 0-100")
	@Max(value=100,message="Usage is in percentage, range 0-100")
	@JsonProperty(value="max_cpu")
	public int getMaxCpu() {
		return maxCpu;
	}
	
	public void setMaxCpu(int maxCpu) {
		this.maxCpu = maxCpu;
	}
	
	@Column(name = "percentage_memory_used", nullable = false)
	@Min(value=0,message="Usage is in percentage, range 0-100")
	@Max(value=100,message="Usage is in percentage, range 0-100")
	@JsonProperty(value="max_memory")
	public int getMaxMemory() {
		return maxMemory;
	}
	
	public void setMaxMemory(int maxMemory) {
		this.maxMemory = maxMemory;
	}
}
