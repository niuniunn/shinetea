package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.AddressInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressInfoRepository extends JpaRepository<AddressInfo, Integer> {
    List<AddressInfo> findByMemberId(Integer memberId);
}
