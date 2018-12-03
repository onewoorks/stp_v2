package com.stp.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stp.auth.model.RefLokasi;
import com.stp.auth.repository.RefLokasiRepository;

@Service
public class RefLokasiImpl implements RefLokasiService {
	
	@Autowired
	private RefLokasiRepository refLokasiRepository;
	
	@Override
	public void save(RefLokasi refCawangan) {
		refLokasiRepository.save(refCawangan);
		
	}
	
	@Override
	public List<RefLokasi> getAll() {
		
		List<RefLokasi> refCawangan = new ArrayList<>();
		refLokasiRepository.findAll().forEach(refCawangan::add);
		return refCawangan;
	}
}
