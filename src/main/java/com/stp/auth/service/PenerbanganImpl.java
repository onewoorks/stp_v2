package com.stp.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stp.auth.model.Penerbangan;
import com.stp.auth.model.Permohonan;
import com.stp.auth.repository.PenerbanganRepository;

@Service
public class PenerbanganImpl implements PenerbanganService{
	
	@Autowired
    private PenerbanganRepository penerbanganRepo;
	
	@Override
	public void save(Penerbangan penerbangan) {
		penerbanganRepo.save(penerbangan);
		
	}
	
	public List<Penerbangan> findByPermohonan(Permohonan permohonan) {
		// TODO Auto-generated method stub
		return penerbanganRepo.findByPermohonan(permohonan);
	}
	
	public List<Penerbangan> findByPenerbanganId(Long penerbanganId){
		return penerbanganRepo.findByPenerbanganId(penerbanganId);
	}

	@Override
	public List<Penerbangan> getAll() {
		
		List<Penerbangan> penerbangan = new ArrayList<>();
		penerbanganRepo.findAll().forEach(penerbangan::add);
		return penerbangan;
	}

	@Override
	public void remove(Penerbangan penerbangan) {
		penerbanganRepo.delete(penerbangan.getPenerbanganId());
		
	}
	
//	@Autowired
//	private PenerbanganRepository penerbanganRepo;
//
//	@Override
//	public List<Penerbangan> getAll() {
//		List<Penerbangan> penerbangan = new ArrayList<>();
//		penerbanganRepo.findAll().forEach(penerbangan::add);
//		return penerbangan;
//	}
//
//	@Override
//	public void remove(Penerbangan penerbanganForm) {
//		penerbanganRepo.delete(penerbanganForm.getPenerbanganId());
//	}

}