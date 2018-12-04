package com.stp.auth.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.stp.auth.model.Pengguna;
import com.stp.auth.model.RefJawatan;
import com.stp.auth.repository.DaftarPenggunaRepository;
import com.stp.auth.repository.RoleRepository;

@Service
public class DaftarPenggunaImpl implements DaftarPenggunaService {
    @Autowired
    private DaftarPenggunaRepository daftarPenggunaRepo;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(Pengguna pengguna) {
		pengguna.setPassword(bCryptPasswordEncoder.encode(pengguna.getPassword()));
//		pengguna.setRoles(new HashSet<>(roleRepository.findAll()));
		daftarPenggunaRepo.save(pengguna);
		
	}

	@Override
	public Pengguna findByUsername(String username) {
		// TODO Auto-generated method stub
		return daftarPenggunaRepo.findByUsername(username);
	}

	@Override
	public List<Pengguna> findAll() {
		// TODO Auto-generated method stub
		return daftarPenggunaRepo.findAll();
	}

	@Override
	public Pengguna findById(Long id) {
		// TODO Auto-generated method stub
		return daftarPenggunaRepo.findById(id);
	}
	
	@Override
	public List<Pengguna> findByJawatan(String jawatan){
		
		return daftarPenggunaRepo.findByJawatan(jawatan);
	}
	
	@Override
	public List<Pengguna> findByCawangan(String cawangan){
		
		return daftarPenggunaRepo.findByCawangan(cawangan);
	}

	@Override
	public void remove(Pengguna daftarPenggunaForm) {
		daftarPenggunaRepo.delete(daftarPenggunaForm.getId());
	}
	
	@Override
	public List<Pengguna> findByRefJawatan(RefJawatan ref) {
		// TODO Auto-generated method stub
		return daftarPenggunaRepo.findByRefJawatan(ref);
	}
	
	@Override
	public List<Pengguna> findByNoKP(String noKP) {
		// TODO Auto-generated method stub
		return daftarPenggunaRepo.findByNoKP(noKP);
	}
	
	
	
	
}
