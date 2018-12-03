package com.stp.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stp.auth.model.Barangan;
import com.stp.auth.model.Permohonan;

public interface BaranganRepository extends JpaRepository<Barangan, Long> {

	List<Barangan> findByPermohonan(Permohonan permohonan);

}
