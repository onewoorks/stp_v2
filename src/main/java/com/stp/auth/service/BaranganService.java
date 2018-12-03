package com.stp.auth.service;

import java.util.ArrayList;
import java.util.List;

import com.stp.auth.model.Barangan;
import com.stp.auth.model.Penerbangan;
//import com.stp.auth.model.Permohonan;
import com.stp.auth.model.Permohonan;

public interface BaranganService {

	List<Barangan> getAll();
	void save(Barangan baranganForm);
	void remove(Barangan baranganForm);
	List<Barangan> findByPermohonan(Permohonan permohonan);
}
