package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	public Users findByEmail(String email);

}
