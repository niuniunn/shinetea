package com.niuniu.shinetea.service.impl;

import com.niuniu.shinetea.dataobject.PointRecord;
import com.niuniu.shinetea.repository.PointRecordRepository;
import com.niuniu.shinetea.service.PointRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointRecordServiceImpl implements PointRecordService {

    @Autowired
    private PointRecordRepository pointRecordRepository;

    @Override
    public PointRecord save(PointRecord pointRecord) {
        return pointRecordRepository.save(pointRecord);
    }

    @Override
    public List<PointRecord> findByMemberId(Integer memberId) {
        return pointRecordRepository.findByMemberId(memberId);
    }
}
