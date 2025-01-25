package com.example.graduationSystem.service.implementation;

import com.example.graduationSystem.dtos.CreateReviewDto;
import com.example.graduationSystem.dtos.ReviewDto;
import com.example.graduationSystem.entity.Review;
import com.example.graduationSystem.mapper.EntityMapper;
import com.example.graduationSystem.repository.ProfessorRepository;
import com.example.graduationSystem.repository.ReviewRepository;
import com.example.graduationSystem.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public ReviewDto addReview(CreateReviewDto review) {
        if(!professorRepository.existsById(review.getProfessorId())) {
            throw new EntityNotFoundException("Professor with Id " + review.getProfessorId() + " is not found");
        }
        return null;
    }
}
