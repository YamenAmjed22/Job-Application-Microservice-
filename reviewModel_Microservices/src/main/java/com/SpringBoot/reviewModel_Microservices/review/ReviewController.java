package com.SpringBoot.reviewModel_Microservices.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviewsByCompanyId(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviewsForCompany(companyId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addReviewForCompany(@RequestBody Review review , @RequestParam Long companyId  ){
            boolean wasReviewSaved  = reviewService.addReviewsForCompany(review,companyId);
            if (wasReviewSaved) {
                return new ResponseEntity<>("Review saved successfully ", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Review not saved successfully ", HttpStatus.NOT_FOUND);

            }
    }
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId){
        return  new ResponseEntity<>(reviewService.getReviewByIdForCompany(reviewId),HttpStatus.OK);
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReviewForCompany(@PathVariable Long reviewId , @RequestBody Review review){
        boolean isReviewed = reviewService.updateReviewById(reviewId, review);
        if (isReviewed) {
            return new ResponseEntity<>("Review updated successfully ", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Review not updated successfully ", HttpStatus.NOT_FOUND);

        }
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
            Boolean isDeleted = reviewService.deleteReview(reviewId);
            if (isDeleted){
                return new ResponseEntity<>("Review  deleted successfully ", HttpStatus.OK);

            }
                return new ResponseEntity<>("Review not deleted successfully ", HttpStatus.NOT_FOUND);

    }
    }



