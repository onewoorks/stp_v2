package com.stp.auth.service;

import java.util.ArrayList;
import java.util.List;

import com.stp.auth.model.RefJawatan;
import com.stp.auth.model.RefRole;

public interface RefJawatanService {

	public List<RefJawatan> getAll();

	public RefJawatan findByRefRole(RefRole jb);

	public void save(RefJawatan refJawatan);
	
}
