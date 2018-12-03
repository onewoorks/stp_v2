package com.stp.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stp.auth.model.Pengguna;

public interface DaftarPenggunaRepository extends JpaRepository<Pengguna, Long> {
	
	public Pengguna findById(Long id);

	public List<Pengguna> findByJawatan(String jawatan);

	public List<Pengguna> findByCawangan(String cawangan);

	public Pengguna findByUsername(String username);
}
