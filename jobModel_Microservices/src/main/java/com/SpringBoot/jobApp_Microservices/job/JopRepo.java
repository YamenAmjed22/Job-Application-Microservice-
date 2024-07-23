package com.SpringBoot.jobApp_Microservices.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JopRepo extends JpaRepository<Job,Long> {

}
