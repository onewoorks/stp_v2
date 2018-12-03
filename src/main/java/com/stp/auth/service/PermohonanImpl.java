package com.stp.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stp.auth.model.Pengguna;
import com.stp.auth.model.Permohonan;
import com.stp.auth.repository.DaftarPenggunaRepository;
import com.stp.auth.repository.PermohonanRepository;

@Service
public class PermohonanImpl implements PermohonanService {
    @Autowired
    private PermohonanRepository permohonanRepo;


	@Override
	public void save(Permohonan permohonanView) {
		permohonanRepo.save(permohonanView);
		
	}

	public List<Permohonan> findByNama(String nama) {
		// TODO Auto-generated method stub
		return permohonanRepo.findByNama(nama);	}
	
	public List<Permohonan> findByStatusPermohonan(String statusPermohonan) {
		// TODO Auto-generated method stub
		return permohonanRepo.findByStatusPermohonan(statusPermohonan);	}
	
	
	
	public Permohonan findById(Long id) {
		// TODO Auto-generated method stub
		return permohonanRepo.findById(id);
	}
	
	@Override
	public void remove(Permohonan permohonanForm) {
		permohonanRepo.delete(permohonanForm.getId());
	}

	@Override
	public List<Permohonan> getAll() {
		
		List<Permohonan> pemohon = new ArrayList<>();
		permohonanRepo.findAll().forEach(pemohon::add);
		return pemohon;
	}

	@Override
	public List<Permohonan> findByNamaPelulus(String namaPelulus) {
		// TODO Auto-generated method stub
		return permohonanRepo.findByNamaPelulus(namaPelulus);
	}
}
