package com.nirz.assessment.dto;

import java.time.LocalDate;
import java.util.Set;

import com.nirz.assessment.util.Level;
import com.nirz.assessment.util.Topics;

public class AssessmentBookingRequest {
    private String email;
    private Level level;
    private Set<Topics> subjects;  // Use Set for flexibility
    private LocalDate bookingDate;

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Set<Topics> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Topics> subjects) {
        this.subjects = subjects;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
}
