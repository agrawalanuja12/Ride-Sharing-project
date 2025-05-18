package com.rideshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rideshare.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

