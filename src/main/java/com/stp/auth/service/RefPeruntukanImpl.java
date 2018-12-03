package com.stp.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stp.auth.model.RefLokasi;
import com.stp.auth.model.RefPeruntukan;
import com.stp.auth.repository.RefPeruntukanRepository;

@Service
public class RefPeruntukanImpl implements RefPeruntukanService  {
	
	@Autowired
	private RefPeruntukanRepository refPeruntukanRepository;
	
	@Override
	public List<RefPeruntukan> getAll() {
		
		List<RefPeruntukan> refPeuntukan = new ArrayList<>();
		refPeruntukanRepository.findAll().forEach(refPeuntukan::add);
		return refPeuntukan;
	}
	
	@Override
	public void save(RefPeruntukan refPeruntukan) {
		refPeruntukanRepository.save(refPeruntukan);
		
	}

}
