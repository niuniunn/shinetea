package com.niuniu.shinetea.service;

import com.niuniu.shinetea.dataobject.PointRecord;

import java.util.List;

public interface PointRecordService {

    PointRecord save(PointRecord pointRecord);

    List<PointRecord> findByMemberId(Integer memberId);
}
