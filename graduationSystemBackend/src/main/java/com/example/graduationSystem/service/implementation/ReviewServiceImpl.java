package com.example.graduationSystem.service.implementation;

import com.example.graduationSystem.dtos.CreateReviewDto;
import com.example.graduationSystem.dtos.ReviewDto;
import com.example.graduationSystem.entity.Professor;
import com.example.graduationSystem.entity.Review;
import com.example.graduationSystem.entity.Thesis;
import com.example.graduationSystem.mapper.EntityMapper;
import com.example.graduationSystem.repository.ReviewRepository;
import com.example.graduationSystem.service.ProfessorService;
import com.example.graduationSystem.service.ReviewService;
import com.example.graduationSystem.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ThesisService thesisService;
    @Autowired
    private ProfessorService professorService;

    @Override
    public ReviewDto addReview(CreateReviewDto review) {
        Professor professor = professorService.fetchProfessorById(review.getProfessorId());
        Thesis thesis = thesisService.fetchThesisById(review.getThesisId());
        return entityMapper.mapToReviewDto(reviewRepository.save(
                Review.builder()
                        .isApproved(review.getIsApproved())
                        .reviewText(review.getReviewText())
                        .professor(professor)
                        .thesis(thesis)
                        .uploadedDate(LocalDateTime.now())
                        .build()
                ));
    }
}
