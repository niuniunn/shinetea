package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.PointRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRecordRepository extends JpaRepository<PointRecord, Integer> {
    List<PointRecord> findByMemberId(Integer memberId);
}
