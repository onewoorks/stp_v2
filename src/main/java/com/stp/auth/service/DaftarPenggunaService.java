package com.stp.auth.service;

import java.util.ArrayList;
import java.util.List;

import com.stp.auth.model.Pengguna;
import com.stp.auth.model.RefJawatan;

public interface DaftarPenggunaService {
    void save(Pengguna pengguna);
    Pengguna findById(Long id);
    Pengguna findByUsername(String username);
    List<Pengguna> findAll();
	void remove(Pengguna daftarPenggunaForm);
	List<Pengguna> findByJawatan(String jawatan);
	List<Pengguna> findByCawangan(String cawangan);
	List<Pengguna> findByRefJawatan(RefJawatan ref);
	List<Pengguna> findByNoKP(String noKP);
}
