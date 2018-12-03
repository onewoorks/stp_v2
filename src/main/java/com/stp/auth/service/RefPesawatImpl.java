package com.stp.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stp.auth.model.RefPesawat;
import com.stp.auth.repository.RefPesawatRepository;

@Service
public class RefPesawatImpl implements RefPesawatService {
	
	@Autowired
	private RefPesawatRepository refPesawatRepository;

	@Override
	public List<RefPesawat> getAll() {
		List<RefPesawat> refPesawat = new ArrayList<>();
		refPesawatRepository.findAll().forEach(refPesawat::add);
		return refPesawat;
	}

	@Override
	public void save(RefPesawat userForm) {
		refPesawatRepository.save(userForm);
		
	}
	
	

}
