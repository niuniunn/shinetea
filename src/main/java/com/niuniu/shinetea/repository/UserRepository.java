package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
