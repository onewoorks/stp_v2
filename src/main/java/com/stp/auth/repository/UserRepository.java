package com.stp.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stp.auth.model.Pengguna;
import com.stp.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

	List<Pengguna> findByNamaPengurus(User user);
}
