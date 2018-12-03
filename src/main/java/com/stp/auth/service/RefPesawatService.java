package com.stp.auth.service;

import java.util.List;

import com.stp.auth.model.RefPesawat;

public interface RefPesawatService {
	
	public List<RefPesawat> getAll();

	public void save(RefPesawat userForm);

}
