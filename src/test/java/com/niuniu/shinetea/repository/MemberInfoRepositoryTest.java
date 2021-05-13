package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.MemberInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberInfoRepositoryTest {

    @Autowired
    private MemberInfoRepository repository;

    @Test
    public void findByOpenid() {
        MemberInfo memberInfo = repository.findByOpenid("opUoK43Uf01PD6udLVMO7NGCtDkc");
        System.out.println(memberInfo);
        Assert.assertNotNull(memberInfo);
    }

    @Test
    public void findByNicknameAndTel() {
        PageRequest request = PageRequest.of(0,1);
        Page<MemberInfo> memberInfoPage = repository.findByNicknameAndPhoneNumber("","",request);
        System.out.println(memberInfoPage.getTotalElements());
        Assert.assertNotEquals(0,memberInfoPage.getTotalElements());
    }
}