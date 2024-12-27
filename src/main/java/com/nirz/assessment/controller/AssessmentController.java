package com.nirz.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nirz.assessment.service.AssessmentBookingService;
import com.nirz.assessment.dto.AssessmentBookingRequest;
@CrossOrigin
@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    @Autowired
    private AssessmentBookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<String> bookAssessment(@RequestBody AssessmentBookingRequest bookingRequest) {
        try {
            String response = bookingService.bookAssessment(
                    bookingRequest.getEmail(),
                    bookingRequest.getLevel(),
                    bookingRequest.getSubjects(),
                    bookingRequest.getBookingDate()
            );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
