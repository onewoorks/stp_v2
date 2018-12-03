package com.stp.auth.service;

import java.util.List;

import com.stp.auth.model.RefCawangan;

public interface RefCawanganService {
	
	List<RefCawangan> getAll();

	void save(RefCawangan userForm);

}
