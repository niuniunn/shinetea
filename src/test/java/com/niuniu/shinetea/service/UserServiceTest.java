package com.niuniu.shinetea.service;

import com.niuniu.shinetea.dataobject.User;
import com.niuniu.shinetea.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void findByUsername() {
        User user = repository.findByUsername("admin");
        Assert.assertNotNull(user);
    }
}