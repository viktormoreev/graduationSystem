package com.example.graduationSystem.service;

import com.example.graduationSystem.dtos.CreateReviewDto;
import com.example.graduationSystem.dtos.ReviewDto;

public interface ReviewService {
    ReviewDto addReview (CreateReviewDto review);
}
