package com.SpringBoot.jobApp_Microservices.job.dto;

import com.SpringBoot.jobApp_Microservices.job.Job;
import com.SpringBoot.jobApp_Microservices.job.external.Company;

public class JobWithCompanyDTO {
    private Job job ;
    private Company company;

    // setter and getter

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
