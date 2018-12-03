package com.stp.auth.service;

import java.util.List;

import com.stp.auth.model.Penerbangan;
import com.stp.auth.model.Permohonan;

public interface PenerbanganService {

	List<Penerbangan> getAll();
	List<Penerbangan> findByPenerbanganId(Long penerbanganId);
	List<Penerbangan> findByPermohonan(Permohonan permohonan);
	void save(Penerbangan penerbangan);
	void remove(Penerbangan penerbangan);
}