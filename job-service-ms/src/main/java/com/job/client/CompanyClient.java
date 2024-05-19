package com.job.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.job.external.Company;

@FeignClient(name = "company-service")
public interface CompanyClient {

	@GetMapping("/company/findCompanyById/{companyId}")
	Company getCompany(@PathVariable("companyId") Long companyId);

}
