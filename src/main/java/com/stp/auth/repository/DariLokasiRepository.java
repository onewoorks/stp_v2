package com.stp.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stp.auth.model.RefLokasi;

public interface DariLokasiRepository extends JpaRepository<RefLokasi, Long>  {

	List<RefLokasi> findByLokasiDesc(String lokasiDesc);

	RefLokasi findBylokasiId(Long lokasiId);

}
