package com.SpringBoot.reviewModel_Microservices.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long> {
    List<Review> findByCompanyId(Long companyId);
    // select * from review where companyId = ?
}