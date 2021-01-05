package com.aryaapps.metricsingestion.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aryaapps.metricsingestion.dto.SystemMetrics;

@Repository
public interface MetricsRepository extends JpaRepository<SystemMetrics, String>{
	
	@Transactional
	@Modifying
	@Query("update SystemMetrics metrics set metrics.maxCpu = ?1, metrics.maxMemory = ?2 where metrics.ip = ?3")
	void updateMetricsByIp(int maxCpu, int maxMemory, String ip);
}
