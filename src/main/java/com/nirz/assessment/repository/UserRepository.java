package com.nirz.assessment.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nirz.assessment.model.User;



public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);
}
