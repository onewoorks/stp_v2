package com.stp.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stp.auth.model.Pengguna;
import com.stp.auth.model.RefJawatan;
import com.stp.auth.model.RefLokasi;
import com.stp.auth.model.RefRole;
import com.stp.auth.repository.RefJawatanRepository;

@Service
public class RefJawatanImpl implements RefJawatanService {
	
	@Autowired
	RefJawatanRepository refJawatanRepository;

	@Override
	public List<RefJawatan> getAll() {
		List<RefJawatan> refJawatan = new ArrayList<>();
		refJawatanRepository.findAll().forEach(refJawatan::add);
		return refJawatan;
	}
	
	public RefJawatan findByRefRole(RefRole jb) {
		// TODO Auto-generated method stub
		return refJawatanRepository.findByRefRole(jb);
	}
	
	@Override
	public void save(RefJawatan refJawatan) {
		refJawatanRepository.save(refJawatan);
		
	}
	
	

}
