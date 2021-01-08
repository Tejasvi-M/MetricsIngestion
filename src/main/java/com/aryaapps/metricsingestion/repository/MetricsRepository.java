package com.aryaapps.metricsingestion.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.aryaapps.metricsingestion.dto.SystemMetrics;

@Repository
public interface MetricsRepository extends JpaRepository<SystemMetrics, Integer>{
	
	
	@Query("SELECT new com.aryaapps.metricsingestion.dto.SystemMetrics(metrics.ip, MAX(metrics.maxCpu), MAX(metrics.maxMemory)) FROM SystemMetrics metrics GROUP BY metrics.ip")
	List<SystemMetrics> returnMaxCpuAndMaxMemoryOfHosts();
}
