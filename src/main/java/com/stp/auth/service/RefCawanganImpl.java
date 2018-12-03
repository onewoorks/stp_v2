package com.stp.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stp.auth.model.Permohonan;
import com.stp.auth.model.RefCawangan;
import com.stp.auth.repository.RefCawanganRepository;

@Service
public class RefCawanganImpl implements RefCawanganService {
	
	@Autowired
	private RefCawanganRepository refCawanganRepository;
	
	@Override
	public void save(RefCawangan refCawangan) {
		refCawanganRepository.save(refCawangan);
		
	}
	
	@Override
	public List<RefCawangan> getAll() {
		
		List<RefCawangan> refCawangan = new ArrayList<>();
		refCawanganRepository.findAll().forEach(refCawangan::add);
		return refCawangan;
	}

}
