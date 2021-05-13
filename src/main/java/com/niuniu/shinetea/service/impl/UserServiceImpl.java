package com.niuniu.shinetea.service.impl;

import com.niuniu.shinetea.dataobject.User;
import com.niuniu.shinetea.repository.UserRepository;
import com.niuniu.shinetea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
