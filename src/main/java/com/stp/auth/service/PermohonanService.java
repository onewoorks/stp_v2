package com.stp.auth.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.stp.auth.model.Pengguna;
import com.stp.auth.model.Permohonan;
import com.stp.auth.model.User;

public interface PermohonanService {
    void save(Permohonan permohonanView);
    
    List<Permohonan> getAll();
    List<Permohonan> findByStatusPermohonan(String statusPermohonan);
    List<Permohonan> findByNama(String nama);
    List<Permohonan> findByNamaPelulus(String namaPelulus);
   	Permohonan findById(Long id);
   	void remove(Permohonan permohonanForm);

   	List<Map<String, Object>> report();
   	
}
