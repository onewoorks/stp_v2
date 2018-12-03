package com.stp.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stp.auth.model.RefLokasi;
import com.stp.auth.model.Destinasi;
import com.stp.auth.repository.DariLokasiRepository;
import com.stp.auth.repository.DestinasiRepository;

@Service
public class DariLokasiImpl implements DariLokasiService {
	
	@Autowired
	private DariLokasiRepository dariLokasiRepo;
	
	@Override
	public List<RefLokasi> getAll() {
		
		List<RefLokasi> dariLokasi = new ArrayList<>();
		dariLokasiRepo.findAll().forEach(dariLokasi::add);
		return dariLokasi;
	}
	
	public RefLokasi findBylokasiId(Long lokasiId){
		
		return dariLokasiRepo.findBylokasiId(lokasiId);
		
	}

}
