package com.stp.auth.service;

import java.util.List;

import com.stp.auth.model.Pengguna;

public interface DaftarPenggunaService {
    void save(Pengguna pengguna);
    Pengguna findById(Long id);
    Pengguna findByUsername(String username);
    List<Pengguna> findAll();
	void remove(Pengguna daftarPenggunaForm);
	List<Pengguna> findByJawatan(String jawatan);
	List<Pengguna> findByCawangan(String cawangan);
}
