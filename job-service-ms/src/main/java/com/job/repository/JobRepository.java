package com.job.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.job.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long>{

	@Query("select p from Job p where p.id=:jobId")
	Job findByJobId(@Param("jobId") Long jobId);

}
