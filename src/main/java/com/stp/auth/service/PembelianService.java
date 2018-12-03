package com.stp.auth.service;

import java.util.ArrayList;
import java.util.List;

import com.stp.auth.model.Pembelian;
//import com.stp.auth.model.Permohonan;
import com.stp.auth.model.Penerbangan;

public interface PembelianService {
//	void savePembelian(Pembelian pembelian);
	
	List<Pembelian> getAll2();
	
	Pembelian findByUsername(String username);

	void save(Pembelian pembelian);

	List<Pembelian> findByPenerbangan(Penerbangan penerbangan);

//	void save(Pembelian userForm);
	
}
