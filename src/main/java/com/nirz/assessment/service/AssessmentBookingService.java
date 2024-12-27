package com.nirz.assessment.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nirz.assessment.model.Assessment;
import com.nirz.assessment.model.User;
import com.nirz.assessment.repository.AssessmentRepository;
import com.nirz.assessment.repository.UserRepository;
import com.nirz.assessment.util.Level;
import com.nirz.assessment.util.Topics;

@Service
public class AssessmentBookingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Transactional // Ensures both user and assessment are saved atomically
    public String bookAssessment(String email, Level level, Set<Topics> set, LocalDate bookingDate) throws Exception {
        // 1. Check if the user exists
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("User not found for email: " + email);
        }

        User user = optionalUser.get();

        // 2. Check if selected subjects are exactly 4
        if (set.size() != 4) {
            throw new IllegalArgumentException("You must select exactly 4 subjects. You selected: " + set.size());
        }

        // 3. Check if the user has already booked the same combination within the last month
        LocalDate oneMonthBefore = bookingDate.minusMonths(1);
        LocalDate oneMonthAfter = bookingDate.plusMonths(1);
        List<Assessment> recentAssessments = assessmentRepository.findByEmailAndLevelAndDateRange(email, level, oneMonthBefore, oneMonthAfter);

        for (Assessment assessment : recentAssessments) {
            if (assessment.getSubjects().equals(set)) {
                throw new Exception("You cannot book this level of assessment with the same subjects within one month of your last booking.");
            }
        }

        // 4. Validate the booking date (optional but recommended)
        if (bookingDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Booking date cannot be in the past.");
        }

        // 5. Create and save the new assessment booking
        Assessment newAssessment = new Assessment();
        newAssessment.setAssessmentId(UUID.randomUUID().toString());
        newAssessment.setLevel(level);
        newAssessment.setSubjects(set);
        newAssessment.setBookingDate(bookingDate);  // Booking on a future date
        newAssessment.setEmail(email);
        user.getAssessments().add(newAssessment);  // Add the new assessment to the user's list

        // Save the assessment and the updated user
        assessmentRepository.save(newAssessment);
        userRepository.save(user);

        return "Assessment booked successfully for " + bookingDate;
    }

}
