package com.aryaapps.metricsingestion.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aryaapps.metricsingestion.dto.SystemMetrics;

@Repository
public interface MetricsRepository extends JpaRepository<SystemMetrics, Integer>{
	
	@Transactional
	@Modifying
	@Query("update SystemMetrics metrics set metrics.maxCpu = ?1, metrics.maxMemory = ?2 where metrics.ip = ?3")
	void updateMetricsByIp(int maxCpu, int maxMemory, String ip);
	@Query("SELECT new com.aryaapps.metricsingestion.dto.SystemMetrics(metrics.ip, MAX(metrics.maxCpu), MAX(metrics.maxMemory)) FROM SystemMetrics metrics GROUP BY metrics.ip")
	List<SystemMetrics> returnMaxCpuAndMaxMemoryOfHosts();
}
