package com.niuniu.shinetea.controller;

import com.niuniu.shinetea.VO.ResultVO;
import com.niuniu.shinetea.dataobject.MemberInfo;
import com.niuniu.shinetea.dto.PageDTO;
import com.niuniu.shinetea.enums.ResultEnum;
import com.niuniu.shinetea.exception.ShineTeaException;
import com.niuniu.shinetea.form.MemberForm;
import com.niuniu.shinetea.service.MemberInfoService;
import com.niuniu.shinetea.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/member")
@Slf4j
public class MemberInfoController {

    @Autowired
    private MemberInfoService memberInfoService;

    //注册登录
    @PostMapping("/create")
    public ResultVO create(@Valid MemberForm memberForm,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【注册用户】参数不正确，memberForm={}", memberForm);
            throw new ShineTeaException(ResultEnum.PARAM_ERROR.getCode(),
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        MemberInfo member = memberInfoService.findByOpenid(memberForm.getOpenid());
        if(member != null) {
            return ResultVOUtil.success(member);
        } else {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setOpenid(memberForm.getOpenid());
            memberInfo.setNickname(memberForm.getNickname());
            memberInfo.setGender(memberForm.getGender());
            MemberInfo result = memberInfoService.save(memberInfo);
            return ResultVOUtil.success(result);
        }
    }

    @PostMapping("/edit")
    public ResultVO edit(@Valid MemberForm memberForm,
                         BindingResult bindingResult) throws ParseException {
        if(bindingResult.hasErrors()) {
            log.error("【编辑资料】参数不正确，memberForm={}", memberForm);
            throw new ShineTeaException(ResultEnum.PARAM_ERROR.getCode(),
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        MemberInfo member = memberInfoService.findByOpenid(memberForm.getOpenid());
        if(member == null) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST.getCode(), ResultEnum.USER_NOT_EXIST.getMessage());
        } else {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setOpenid(memberForm.getOpenid());
            memberInfo.setNickname(memberForm.getNickname());
            memberInfo.setGender(memberForm.getGender());
            memberInfo.setMemberId(memberForm.getId());
            memberInfo.setBirthday(memberForm.getBirthday());
            memberInfo.setPhoneNumber(memberForm.getTel());
            MemberInfo result = memberInfoService.save(memberInfo);
            return ResultVOUtil.success(result);
        }
    }

    @PostMapping("/page")
    public ResultVO<List<MemberInfo>> pageList(@RequestParam("nickname") String nickname,
                                               @RequestParam("tel") String tel,
                                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageRequest request = PageRequest.of(page-1, size);
        Page<MemberInfo> memberInfoPage = memberInfoService.findByNicknameAndPhoneNumber(nickname,tel,request);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setSize(size);
        pageDTO.setData(memberInfoPage.getContent());
        pageDTO.setTotal(memberInfoPage.getTotalElements());

        return ResultVOUtil.success(pageDTO);
    }
}
