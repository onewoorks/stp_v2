package com.stp.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stp.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
