package com.stp.auth.service;

import java.util.List;

import com.stp.auth.model.RefLokasi;

public interface RefLokasiService {
	List<RefLokasi> getAll();

	void save(RefLokasi userForm);
}
