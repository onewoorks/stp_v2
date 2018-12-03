package com.stp.auth.service;

import com.stp.auth.model.Penerbangan;
import com.stp.auth.model.Pengguna;
import com.stp.auth.model.Permohonan;
import com.stp.auth.model.User;
import com.stp.auth.repository.RoleRepository;
import com.stp.auth.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public List<Pengguna> findByNamaPengurus(User user) {
		// TODO Auto-generated method stub
		return userRepository.findByNamaPengurus(user);
	}
    
    
}
