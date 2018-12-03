package com.stp.auth.service;

import java.util.List;

import com.stp.auth.model.RefLokasi;

public interface DariLokasiService {

	public List<RefLokasi> getAll();
	
	public RefLokasi findBylokasiId(Long lokasiId);
}
