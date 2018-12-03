package com.stp.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stp.auth.model.Pembelian;
import com.stp.auth.model.Penerbangan;
import com.stp.auth.repository.PembelianRepository;

@Service
public class PembelianImpl implements PembelianService {
	@Autowired
	private PembelianRepository pembelianRepo;
	
	@Override
	public void save(Pembelian pembelian) {
		pembelianRepo.save(pembelian);
		
	}

	@Override
	public List<Pembelian> getAll2() {
		
		List<Pembelian> pembelian = new ArrayList<>();
		pembelianRepo.findAll().forEach(pembelian::add);
		return pembelian;
	}
	
	public List<Pembelian> findByPenerbangan(Penerbangan penerbangan){
		return pembelianRepo.findByPenerbangan(penerbangan);
	}

	@Override
	public Pembelian findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
