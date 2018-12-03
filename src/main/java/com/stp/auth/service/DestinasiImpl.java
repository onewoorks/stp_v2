package com.stp.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stp.auth.model.Destinasi;
import com.stp.auth.repository.DestinasiRepository;

@Service
public class DestinasiImpl implements DestinasiService {
	
	@Autowired
	private DestinasiRepository destinasiRepo;
	
	@Override
	public List<Destinasi> getAll() {
		
		List<Destinasi> destinasi = new ArrayList<>();
		destinasiRepo.findAll().forEach(destinasi::add);
		return destinasi;
	}

}
