package com.niuniu.shinetea.service.impl;

import com.niuniu.shinetea.dataobject.MemberInfo;
import com.niuniu.shinetea.repository.MemberInfoRepository;
import com.niuniu.shinetea.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberInfoImpl implements MemberInfoService {

    @Autowired
    private MemberInfoRepository repository;

    @Override
    public MemberInfo findByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }

    @Override
    public Page<MemberInfo> findByNicknameAndPhoneNumber(String nickname, String phoneNumber, Pageable pageable) {
        Page<MemberInfo> memberInfoPage = repository.findByNicknameAndPhoneNumber(nickname, phoneNumber, pageable);
        List<MemberInfo> memberInfoList = memberInfoPage.getContent();
        return new PageImpl<>(memberInfoList, pageable, memberInfoPage.getTotalElements());
    }

    @Override
    public MemberInfo save(MemberInfo memberInfo) {
        return repository.save(memberInfo);
    }

    @Override
    public MemberInfo findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
