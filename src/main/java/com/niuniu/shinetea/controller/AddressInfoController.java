package com.niuniu.shinetea.controller;

import com.niuniu.shinetea.VO.ResultVO;
import com.niuniu.shinetea.dataobject.AddressInfo;
import com.niuniu.shinetea.enums.ResultEnum;
import com.niuniu.shinetea.exception.ShineTeaException;
import com.niuniu.shinetea.form.AddressInfoForm;
import com.niuniu.shinetea.service.impl.AddressInfoServiceImpl;
import com.niuniu.shinetea.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressInfoController {
    @Autowired
    private AddressInfoServiceImpl addressInfoService;

    @PostMapping("/create")
    public ResultVO create(@Valid AddressInfoForm addressInfoForm,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【创建收货地址】参数不正确，addressInfoForm={}",addressInfoForm);
            throw new ShineTeaException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setMemberId(addressInfoForm.getMemberId());
        addressInfo.setName(addressInfoForm.getName());
        addressInfo.setGender(addressInfoForm.getGender());
        addressInfo.setAddressDetail(addressInfoForm.getAddressDetail());
        addressInfo.setLatitude(addressInfoForm.getLatitude());
        addressInfo.setLongitude(addressInfoForm.getLongitude());
        addressInfo.setPhoneNumber(addressInfoForm.getPhoneNumber());

        addressInfoService.save(addressInfo);
        return ResultVOUtil.success();
    }

    @PostMapping("/list")
    public ResultVO list(@RequestParam("memberId") Integer memberId) {
        return ResultVOUtil.success(addressInfoService.findByMemberId(memberId));
    }

    @PostMapping("/edit")
    public ResultVO edit(@Valid AddressInfoForm addressInfoForm,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【修改收货地址】参数不正确，addressInfoForm={}",addressInfoForm);
            throw new ShineTeaException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        if(addressInfoService.findById(addressInfoForm.getAddressId()) == null) {
            return ResultVOUtil.error(ResultEnum.ADDRESS_NOT_EXIST.getCode(), ResultEnum.ADDRESS_NOT_EXIST.getMessage());
        }

        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setAddressId(addressInfoForm.getAddressId());
        addressInfo.setMemberId(addressInfoForm.getMemberId());
        addressInfo.setName(addressInfoForm.getName());
        addressInfo.setGender(addressInfoForm.getGender());
        addressInfo.setAddressDetail(addressInfoForm.getAddressDetail());
        addressInfo.setLatitude(addressInfoForm.getLatitude());
        addressInfo.setLongitude(addressInfoForm.getLongitude());
        addressInfo.setPhoneNumber(addressInfoForm.getPhoneNumber());

        addressInfoService.save(addressInfo);
        return ResultVOUtil.success();
    }

    @PostMapping("/del")
    public ResultVO delete(@RequestParam Integer addressId) {
        if(addressInfoService.findById(addressId) == null) {
            return ResultVOUtil.error(ResultEnum.ADDRESS_NOT_EXIST.getCode(), ResultEnum.ADDRESS_NOT_EXIST.getMessage());
        }
        addressInfoService.delete(addressId);
        return ResultVOUtil.success();
    }

}
