package com.job.mapper;

import java.util.List;

import com.job.dto.JobDTO;
import com.job.entity.Job;
import com.job.external.Company;
import com.job.external.Review;

public class JobMapper {
	public static JobDTO mapToJobWithCompanyDTO(Job job, Company company, List<Review> reviews) {

		JobDTO dto = new JobDTO();
		dto.setId(job.getId());
		dto.setTitle(job.getTitle());
		dto.setDescription(job.getDescription());
		dto.setLocation(job.getLocation());
		dto.setMaxSalary(job.getMaxSalary());
		dto.setMinSalary(job.getMinSalary());
		dto.setCompany(company);
		dto.setReview(reviews);
		return dto;

	}

}
