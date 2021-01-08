package com.aryaapps.metricsingestion.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="system_metrics")
public class SystemMetrics {
	
	private int id;
	private String ip;
	private float maxCpu;
	private float maxMemory;
	
	public SystemMetrics(){}
	
	public SystemMetrics(String ip,float maxCpu, float maxMemory){
		this.maxCpu=maxCpu;
		this.maxMemory=maxMemory;
		this.ip=ip;
	}
	
	


	@Id
	@JsonIgnore
	@Column(name="id",updatable = false, nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id =id;
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
	public float getMaxCpu() {
		return maxCpu;
	}
	
	public void setMaxCpu(float maxCpu) {
		this.maxCpu = maxCpu;
	}
	
	@Column(name = "percentage_memory_used", nullable = false)
	@Min(value=0,message="Usage is in percentage, range 0-100")
	@Max(value=100,message="Usage is in percentage, range 0-100")
	@JsonProperty(value="max_memory")
	public float getMaxMemory() {
		return maxMemory;
	}
	
	public void setMaxMemory(float maxMemory) {
		this.maxMemory = maxMemory;
	}
}
