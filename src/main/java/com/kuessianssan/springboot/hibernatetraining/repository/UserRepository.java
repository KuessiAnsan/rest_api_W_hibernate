package com.kuessianssan.springboot.hibernatetraining.repository;

import com.kuessianssan.springboot.hibernatetraining.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
