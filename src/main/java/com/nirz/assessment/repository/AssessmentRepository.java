package com.nirz.assessment.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.nirz.assessment.model.Assessment;
import com.nirz.assessment.util.Level;

public interface AssessmentRepository extends MongoRepository<Assessment, String> {
	@Query("{ 'email': ?0, 'level': ?1, 'bookingDate': { $gte: ?2, $lte: ?3 } }")
	List<Assessment> findByEmailAndLevelAndDateRange(
	        String email,
	        Level level,
	        LocalDate startDate,
	        LocalDate endDate
	);

}
