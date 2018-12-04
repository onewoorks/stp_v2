package com.stp.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stp.auth.model.RefPesawat;
import com.stp.auth.model.RefRole;
import com.stp.auth.repository.RefRoleRepository;

@Service
public class RefRoleImpl implements RefRoleService{
	
	@Autowired
	private RefRoleRepository refRoleRepository;

	@Override
	public List<RefRole> getAll() {
		List<RefRole> refRole = new ArrayList<>();
		refRoleRepository.findAll().forEach(refRole::add);
		return refRole;
	}

}
