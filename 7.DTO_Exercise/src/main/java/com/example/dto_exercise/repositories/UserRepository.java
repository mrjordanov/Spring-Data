package com.example.dto_exercise.repositories;

import com.example.dto_exercise.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    Optional<User> findByEmailAndPassword(String email, String password);
}
