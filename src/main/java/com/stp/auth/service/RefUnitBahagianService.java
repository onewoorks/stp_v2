package com.stp.auth.service;

import java.util.List;

import com.stp.auth.model.RefUnitBahagian;

public interface RefUnitBahagianService {

	public List<RefUnitBahagian> getAll();

	public void save(RefUnitBahagian userForm);
}
