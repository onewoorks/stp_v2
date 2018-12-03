package com.stp.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stp.auth.model.RefUnitBahagian;
import com.stp.auth.repository.RefUnitBahagianRepository;

@Service
public class RefUnitBahagianImpl implements RefUnitBahagianService {
	
	@Autowired
	private RefUnitBahagianRepository refUnitBahagianRepository;
	
	@Override
	public List<RefUnitBahagian> getAll(){
		
		List<RefUnitBahagian> refUnitBahagian = new ArrayList<>();
		refUnitBahagianRepository.findAll().forEach(refUnitBahagian::add);
		return refUnitBahagian;
	}

	@Override
	public void save(RefUnitBahagian userForm) {
		refUnitBahagianRepository.save(userForm);
		
	}

}
