package com.stp.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stp.auth.model.Pembelian;
import com.stp.auth.model.Penerbangan;

public interface PembelianRepository extends JpaRepository<Pembelian, Long> {

	List<Pembelian> findByPenerbangan(Penerbangan penerbangan);


}
