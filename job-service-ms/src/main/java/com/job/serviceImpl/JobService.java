package com.job.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;
//
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.job.client.CompanyClient;
import com.job.client.ReviewClient;
import com.job.dto.JobDTO;
import com.job.entity.Job;
import com.job.external.Company;
import com.job.external.Review;
import com.job.mapper.JobMapper;
import com.job.repository.JobRepository;

@Service
public class JobService {

	private JobRepository jobRepository;
	private RestTemplate restTemplate;
	private CompanyClient companyClient;
	private ReviewClient reviewClient;

	private JobDTO convertToDto(Job job) {

		// - > old using restTemplate
//		Company company = restTemplate.getForObject(
//				"http://company-service:8084/company/findCompanyById/" + job.getCompanyId(), Company.class);

//		ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
//				"http://review-service:8083/review/findAllReviews/" + job.getCompanyId(), HttpMethod.GET, null,
//				new ParameterizedTypeReference<List<Review>>() {
//				});
		//List<Review> reviews = reviewResponse.getBody();
		
		
		// -> new using openfeign client
		Company company = companyClient.getCompany(job.getCompanyId());
		List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
		
		JobDTO dto = JobMapper.mapToJobWithCompanyDTO(job, company,reviews);
		//dto.setCompany(company);

		return dto;
	}

	public List<JobDTO> findAll() {

		List<Job> jobs = jobRepository.findAll();
		// List<JobWithCompanyDTO> jobWithCompanyDTOs = new ArrayList<>();

		return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public JobDTO findJobById(Long jobId) {

		Job job = jobRepository.findByJobId(jobId);
		return convertToDto(job);
	}

	public Job createJob(Job job) {

		return jobRepository.save(job);
	}

	public JobService(JobRepository jobRepository, RestTemplate restTemplate,
			CompanyClient companyClient,ReviewClient reviewClient) {
		super();
		this.jobRepository = jobRepository;
		this.restTemplate = restTemplate;
		this.companyClient=companyClient;
		this.reviewClient=reviewClient;
	}

	public String deleteJobById(Long jobId) {
		// TODO Auto-generated method stub
		jobRepository.deleteById(jobId);
		return "Job deleted successfully ";
	}

}
