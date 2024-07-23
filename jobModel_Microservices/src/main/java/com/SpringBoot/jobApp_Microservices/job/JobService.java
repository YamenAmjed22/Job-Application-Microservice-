package com.SpringBoot.jobApp_Microservices.job;

import com.SpringBoot.jobApp_Microservices.job.dto.JobWithCompanyDTO;
import com.SpringBoot.jobApp_Microservices.job.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    //private List<Job> jobs = new ArrayList<>();
    @Autowired
    private JopRepo jopRepo;
    public JobService(JopRepo jopRepo) {
        this.jopRepo = jopRepo;
    }

    /*private Long nextId = 1L;*/
    public List<JobWithCompanyDTO> findAllJobs(){
        List<Job> jobs = jopRepo.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOS = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        for (Job job:jobs){
            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            jobWithCompanyDTO.setJob(job);
            Company company = restTemplate.getForObject(
                    "http://localhost:8081/companyById/"+job.getCompanyId(), Company.class);
            jobWithCompanyDTO.setCompany(company);
            jobWithCompanyDTOS.add(jobWithCompanyDTO);
        }
        return jobWithCompanyDTOS;
    }
    public void addJob(Job job){
        /*job.setId(nextId++);*/
        jopRepo.save(job);
    }

    public Job findJobById(Long id) {
        return jopRepo.findById(id).orElse(null);
    }

    public Boolean deleteJobById(Long id) {
        try {
            jopRepo.deleteById(id);
            return true;
        }catch (Exception e ){
            return false;
        }

    }

    public boolean updateById(Long id ,Job updated ) {
        Optional<Job> jobOptional = jopRepo.findById(id);
        if (jobOptional.isPresent()){
            Job j = jobOptional.get();
            j.setTitle(updated.getTitle());
            j.setLocation(updated.getLocation());
            j.setDescription(updated.getDescription());
            j.setMinSalary(updated.getMinSalary());
            j.setMaxSalary(updated.getMaxSalary());
            jopRepo.save(j);
            return true;
            }

        return false;
    }
}
