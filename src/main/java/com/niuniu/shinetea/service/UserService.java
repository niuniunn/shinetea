package com.niuniu.shinetea.service;

import com.niuniu.shinetea.dataobject.User;

public interface UserService {

    public User findById(String id);

    public User findByUsername(String username);

}
