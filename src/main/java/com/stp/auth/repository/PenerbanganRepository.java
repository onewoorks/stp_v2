package com.stp.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stp.auth.model.Penerbangan;
import com.stp.auth.model.Permohonan;

public interface PenerbanganRepository extends JpaRepository<Penerbangan, Long> {

	List<Penerbangan> findByPermohonan(Permohonan permohonan);

	List<Penerbangan> findByPenerbanganId(Long penerbanganId);

//	public Penerbangan findById(Long penerbanganId);
}