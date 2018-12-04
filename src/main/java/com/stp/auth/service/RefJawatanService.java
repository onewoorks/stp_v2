package com.stp.auth.service;

import java.util.ArrayList;
import java.util.List;

import com.stp.auth.model.RefJawatan;
import com.stp.auth.model.RefRole;

public interface RefJawatanService {

	List<RefJawatan> getAll();

	RefJawatan findByRefRole(RefRole jb);
	
}
