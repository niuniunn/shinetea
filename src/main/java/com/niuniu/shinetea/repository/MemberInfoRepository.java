package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.MemberInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberInfoRepository extends JpaRepository<MemberInfo, Integer> {

    MemberInfo findByOpenid(String openid);

    @Query(
            value = "select * from member_info " +
                    "where nickname like concat('%',?1,'%') " +
                    "and phone_number like concat('%',?2,'%')",
            nativeQuery = true
    )
    Page<MemberInfo> findByNicknameAndPhoneNumber(String nickname, String phoneNumber, Pageable pageable);
}
