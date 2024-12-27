package com.nirz.assessment.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nirz.assessment.util.Level;
import com.nirz.assessment.util.Topics;

@Document(collection = "assessment")
public class Assessment {
    @Id
    private String assessmentId;
    private String email;
    private LocalDate bookingDate;
    private Level level;
    private Set<Topics> subjects;  // Change to Set for flexibility

    // Getters and Setters
    public String getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(String assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
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

    @Override
    public String toString() {
        return "Assessment [assessmentId=" + assessmentId + ", bookingDate=" + bookingDate + ", level=" + level
                + ", subjects=" + subjects + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assessment that = (Assessment) o;
        return level == that.level && Objects.equals(subjects, that.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, subjects);
    }
}
