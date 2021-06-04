package com.niuniu.shinetea.controller;

import com.niuniu.shinetea.VO.ResultVO;
import com.niuniu.shinetea.service.impl.PointRecordServiceImpl;
import com.niuniu.shinetea.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pointrecord")
@Slf4j
public class PointRecordController {

    @Autowired
    private PointRecordServiceImpl pointRecordService;

    @PostMapping("/list")
    public ResultVO list(@RequestParam("memberId") Integer memberId) {
        return ResultVOUtil.success(pointRecordService.findByMemberId(memberId));
    }
}
