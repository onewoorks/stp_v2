package com.stp.auth.service;

import java.util.List;

import com.stp.auth.model.Pengguna;
import com.stp.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

	List<Pengguna> findByNamaPengurus(User user);
}
