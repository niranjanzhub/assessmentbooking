package com.nirz.assessment.model;

import java.util.ArrayList;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user")
public class User {
@Id
private String uid;
private String email;
private String password;
@DBRef
private ArrayList<Assessment> assessments = new ArrayList<>();

public String getUid() {
	return uid;
}
public void setUid(String uid) {
	this.uid = uid;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public ArrayList<Assessment> getAssessments() {
	return assessments;
}
public void setAssessments(ArrayList<Assessment> assessments) {
	this.assessments = assessments;
}







}
