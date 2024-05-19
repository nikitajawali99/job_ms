package com.job.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.job.external.Review;

@FeignClient(name = "REVIEW-SERVICE")
public interface ReviewClient {
	
	@GetMapping("/review/findAllReviews/{companyId}")
	List<Review> getReviews(@PathVariable("companyId") Long companyId);

}
