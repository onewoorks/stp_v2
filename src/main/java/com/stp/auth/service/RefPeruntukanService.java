package com.stp.auth.service;

import java.util.List;

import com.stp.auth.model.RefPeruntukan;

public interface RefPeruntukanService {
	
	public List<RefPeruntukan> getAll();

	public void save(RefPeruntukan userForm);

}
