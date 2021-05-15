package com.niuniu.shinetea.service;

import com.niuniu.shinetea.dataobject.MemberInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberInfoService {

    MemberInfo findByOpenid(String openid);

    Page<MemberInfo> findByNicknameAndPhoneNumber(String nickname, String phoneNumber, Pageable pageable);

    MemberInfo save(MemberInfo memberInfo);

    MemberInfo findById(Integer id);

    MemberInfo updatePoints(Integer memberId, Integer points);
}
