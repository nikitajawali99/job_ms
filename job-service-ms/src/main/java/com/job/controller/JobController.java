package com.job.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.dto.JobDTO;
import com.job.entity.Job;
import com.job.serviceImpl.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {

	private JobService jobservice;

	public JobController(JobService jobservice) {
		super();
		this.jobservice = jobservice;
	}

	@GetMapping("/findAllJobs")
	public ResponseEntity<List<JobDTO>> findAll() {
		return ResponseEntity.ok(jobservice.findAll());

	}

	@DeleteMapping("/deleteJobById/{jobId}")
	public ResponseEntity<String> deleteJobById(@PathVariable("jobId") Long jobId) {
		String deleteJobById = jobservice.deleteJobById(jobId);
		if (deleteJobById != null)
			return new ResponseEntity<>(deleteJobById, HttpStatus.OK);
		else
			return new ResponseEntity<>(deleteJobById, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/findJobById/{jobId}")
	public ResponseEntity<JobDTO> findJobById(@PathVariable("jobId") Long jobId) {
		JobDTO job = jobservice.findJobById(jobId);
		if (job != null)
			return new ResponseEntity<>(job, HttpStatus.OK);
		return new ResponseEntity<>(job, HttpStatus.NOT_FOUND);
	}

	@PostMapping("/createJob")
	public ResponseEntity<Job> createJob(@RequestBody Job job) {

		Job job1 = jobservice.createJob(job);

		return new ResponseEntity<>(job1, HttpStatus.CREATED);

	}

}
