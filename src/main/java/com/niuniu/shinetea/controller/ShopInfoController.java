package com.niuniu.shinetea.controller;

import com.niuniu.shinetea.VO.ResultVO;
import com.niuniu.shinetea.annotation.UserLoginToken;
import com.niuniu.shinetea.dataobject.ShopInfo;
import com.niuniu.shinetea.dto.PageDTO;
import com.niuniu.shinetea.enums.ResultEnum;
import com.niuniu.shinetea.exception.ShineTeaException;
import com.niuniu.shinetea.form.ShopForm;
import com.niuniu.shinetea.service.ShopInfoService;
import com.niuniu.shinetea.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shop")
@Slf4j
public class ShopInfoController {

    @Autowired
    private ShopInfoService shopInfoService;

    //根据门店名称 地址 状态 分页模糊查询门店
    @PostMapping("/page")
    public ResultVO<List<ShopInfo>> pageList(@RequestParam("name") String name,
                                         @RequestParam("address") String address,
                                         @RequestParam("isOpen") Integer isOpen,
                                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageRequest request = PageRequest.of(page-1, size);
        Page<ShopInfo> shopInfoPage;
        if(isOpen == -1) {
            //查询全部
            shopInfoPage = shopInfoService.findByNameAndAddress(name, address, request);
        } else {
            shopInfoPage = shopInfoService.findByNameAndAddressAndStatus(name, address, isOpen, request);
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setData(shopInfoPage.getContent());
        pageDTO.setPage(page);
        pageDTO.setSize(size);
        pageDTO.setTotal(shopInfoPage.getTotalElements());
        return ResultVOUtil.success(pageDTO);
    }

    @GetMapping("list")
    public ResultVO list() {
        List<ShopInfo> shopInfoList = shopInfoService.findAll();
        return ResultVOUtil.success(shopInfoList);
    }

    //新增门店信息
    @PostMapping("/create")
    public ResultVO create(@Valid ShopForm shopForm,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【创建门店】参数不正确，shopForm={}",shopForm);
            throw new ShineTeaException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setShopName(shopForm.getName());
        shopInfo.setPhoneNumber(shopForm.getTel());
        shopInfo.setAddress(shopForm.getAddress());
        shopInfo.setAddressDetail(shopForm.getDetail());
        shopInfo.setOpenTime(shopForm.getStartTime());
        shopInfo.setCloseTime(shopForm.getEndTime());
        shopInfo.setShopRemark(shopForm.getRemark());
        shopInfo.setLongitude(shopForm.getLongitude());
        shopInfo.setLatitude(shopForm.getLatitude());

        shopInfoService.save(shopInfo);
        return ResultVOUtil.success();
    }

    //编辑门店信息
    @PostMapping("/edit")
    public ResultVO edit(@Valid ShopForm shopForm,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【创建门店】参数不正确，shopForm={}",shopForm);
            throw new ShineTeaException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        if(shopInfoService.findById(shopForm.getId()) == null) {
            return ResultVOUtil.error(ResultEnum.SHOP_NOT_EXIST.getCode(), ResultEnum.SHOP_NOT_EXIST.getMessage());
        }

        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setShopId(shopForm.getId());
        shopInfo.setShopName(shopForm.getName());
        shopInfo.setPhoneNumber(shopForm.getTel());
        shopInfo.setAddress(shopForm.getAddress());
        shopInfo.setAddressDetail(shopForm.getDetail());
        shopInfo.setOpenTime(shopForm.getStartTime());
        shopInfo.setCloseTime(shopForm.getEndTime());
        shopInfo.setShopRemark(shopForm.getRemark());
        shopInfo.setLongitude(shopForm.getLongitude());
        shopInfo.setLatitude(shopForm.getLatitude());

        shopInfoService.save(shopInfo);
        return ResultVOUtil.success();
    }

    //删除门店
//    @UserLoginToken
    @PostMapping("/del")
    public ResultVO delete(@RequestParam("id") Integer id) {
        if(shopInfoService.findById(id) == null) {
            return ResultVOUtil.error(ResultEnum.SHOP_NOT_EXIST.getCode(), ResultEnum.SHOP_NOT_EXIST.getMessage());
        }
        shopInfoService.delete(id);
        return ResultVOUtil.success();
    }

    //修改门店状态
    @PostMapping("/status")
    public ResultVO changeStatus(@RequestParam("id") Integer id,
                                 @RequestParam("isOpen") Integer isOpen) {
        if(shopInfoService.findById(id) == null) {
            return ResultVOUtil.error(ResultEnum.SHOP_NOT_EXIST.getCode(), ResultEnum.SHOP_NOT_EXIST.getMessage());
        }
        shopInfoService.updateStatus(id,isOpen);
        return ResultVOUtil.success();
    }

    @PostMapping("/detail")
    public ResultVO detail(@RequestParam("id") Integer id) {
        ShopInfo shopInfo = shopInfoService.findById(id);
        if(shopInfo == null) {
            return ResultVOUtil.error(ResultEnum.SHOP_NOT_EXIST.getCode(), ResultEnum.SHOP_NOT_EXIST.getMessage());
        }
        return ResultVOUtil.success(shopInfo);
    }

}
