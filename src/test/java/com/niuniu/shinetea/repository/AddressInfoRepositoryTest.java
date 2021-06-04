package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.AddressInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressInfoRepositoryTest {

    @Autowired
    private AddressInfoRepository repository;

    @Test
    public void create() {
        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setMemberId(1);
        addressInfo.setName("牛牛");
        addressInfo.setGender(2);
        addressInfo.setAddressDetail("12栋419");
        addressInfo.setLatitude("30.57173");
        addressInfo.setLongitude("103.987235");
        addressInfo.setPhoneNumber("18030565437");
        AddressInfo result = repository.save(addressInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByMemberId() {
        List<AddressInfo> addressInfoList = repository.findByMemberId(1);
        Assert.assertNotNull(addressInfoList);
    }
}