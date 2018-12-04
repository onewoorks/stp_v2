package com.stp.auth.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stp.auth.model.RefJawatan;
import com.stp.auth.model.RefRole;

public interface RefJawatanRepository extends JpaRepository<RefJawatan, Long> {

	RefJawatan findByRefRole(RefRole jb);

}
