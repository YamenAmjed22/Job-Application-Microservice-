package com.SpringBoot.reviewModel_Microservices.review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private final ReviewRepo reviewRepo;


    public ReviewService(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }


    public List<Review> getAllReviewsForCompany(Long companyId){
        List<Review> reviews = reviewRepo.findByCompanyId(companyId);
        return reviews ;
    }
    // select * from review where companyId = ?
    public Boolean addReviewsForCompany(Review review , Long companyId){
        if (companyId!=null && review!=null) {
            review.setCompanyId(companyId);
            reviewRepo.save(review);
            return true;
        }
        else
            {
                return false ;
            }

    }
    public Review getReviewByIdForCompany(Long reviewId ){
        return reviewRepo.findById(reviewId).orElse(null);
    }
    public Boolean updateReviewById(Long reviewId,Review updatedReview){
        Review review = reviewRepo.findById(reviewId).orElse(null);
        if (reviewId!=null){
            review.setTitle((updatedReview.getTitle()));
            review.setDescription((updatedReview.getDescription()));
            review.setRating(updatedReview.getRating());
            reviewRepo.save(review);
            return true ;
        }
        return false ;

    }
    public Boolean deleteReview(Long reviewId){
        Review review = reviewRepo.findById(reviewId).orElse(null);

        if (review!=null){
            reviewRepo.delete(review);
            return  true ;

        }
        return false;

    }



}
