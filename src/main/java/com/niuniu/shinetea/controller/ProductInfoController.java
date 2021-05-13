package com.niuniu.shinetea.controller;

import com.niuniu.shinetea.VO.ProductInfoVO;
import com.niuniu.shinetea.VO.ProductVO;
import com.niuniu.shinetea.VO.ResultVO;
import com.niuniu.shinetea.dataobject.ProductCategory;
import com.niuniu.shinetea.dataobject.ProductInfo;
import com.niuniu.shinetea.dto.PageDTO;
import com.niuniu.shinetea.enums.ResultEnum;
import com.niuniu.shinetea.exception.ShineTeaException;
import com.niuniu.shinetea.form.ProductForm;
import com.niuniu.shinetea.service.impl.CategoryServiceImpl;
import com.niuniu.shinetea.service.impl.ProductInfoServiceImpl;
import com.niuniu.shinetea.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductInfoController {

    @Autowired
    ProductInfoServiceImpl productInfoService;

    @Autowired
    CategoryServiceImpl categoryService;

    @PostMapping("/page")
    public ResultVO<List<ProductInfo>> pageList(@RequestParam("name") String name,
                                                @RequestParam("type") Integer type,
                                                @RequestParam("status") Integer status,
                                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageRequest request = PageRequest.of(page - 1,size);
        Page<ProductInfo> productInfoPage;
        //-1查询所有状态
        if(status == -1) {
            productInfoPage = productInfoService.findByNameAndType(name,type,request);
        } else {
            productInfoPage = productInfoService.findByNameAndTypeAndStatus(name,type,status,request);
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setSize(size);
        pageDTO.setData(productInfoPage.getContent());
        pageDTO.setTotal(productInfoPage.getTotalElements());
        return ResultVOUtil.success(pageDTO);
    }

    @PostMapping("/create")
    public ResultVO create(@Valid ProductForm productForm,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【创建商品】参数不正确，productForm={}",productForm);
            throw new ShineTeaException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductName(productForm.getName());
        productInfo.setProductPrice(productForm.getPrice());
        productInfo.setProductDescription(productForm.getDesc());
        productInfo.setProductPicture(productForm.getPicture());
        productInfo.setProductSpecification(productForm.getSpecification());
        productInfo.setCategoryType(productForm.getType());

        productInfoService.save(productInfo);
        return ResultVOUtil.success();
    }

    @PostMapping("/edit")
    public ResultVO edit(@Valid ProductForm productForm,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【编辑商品】参数不正确，productForm={}",productForm);
            throw new ShineTeaException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        if(productInfoService.findById(productForm.getId()) == null) {
            return ResultVOUtil.error(ResultEnum.PRODUCT_NOT_EXIST.getCode(), ResultEnum.PRODUCT_NOT_EXIST.getMessage());
        }

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(productForm.getId());
        productInfo.setProductName(productForm.getName());
        productInfo.setProductPrice(productForm.getPrice());
        productInfo.setProductDescription(productForm.getDesc());
        productInfo.setProductPicture(productForm.getPicture());
        productInfo.setProductSpecification(productForm.getSpecification());
        productInfo.setCategoryType(productForm.getType());

        productInfoService.save(productInfo);
        return ResultVOUtil.success();
    }

    @PostMapping("/detail")
    public ResultVO detail(@RequestParam("id") Integer id) {
        ProductInfo productInfo = productInfoService.findById(id);
        if(productInfo == null) {
            return ResultVOUtil.error(ResultEnum.PRODUCT_NOT_EXIST.getCode(), ResultEnum.PRODUCT_NOT_EXIST.getMessage());
        }
        return ResultVOUtil.success(productInfo);
    }

    @PostMapping("/del")
    public ResultVO delete(@RequestParam("id") Integer id) {
        if(productInfoService.findById(id) == null) {
            return ResultVOUtil.error(ResultEnum.PRODUCT_NOT_EXIST.getCode(), ResultEnum.PRODUCT_NOT_EXIST.getMessage());
        }
        productInfoService.delete(id);
        return ResultVOUtil.success();
    }

    @PostMapping("/status")
    public ResultVO changeStatus(@RequestParam("id") Integer id,
                                 @RequestParam("status") Integer status) {
        if(productInfoService.findById(id) == null) {
            return ResultVOUtil.error(ResultEnum.PRODUCT_NOT_EXIST.getCode(), ResultEnum.PRODUCT_NOT_EXIST.getMessage());
        }
        productInfoService.updateStatus(id,status);
        return ResultVOUtil.success();
    }

    //小程序端获取在架商品
    @GetMapping("/list")
    public ResultVO list() {
        //查询所有在架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        //查询类目
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryIcon(productCategory.getCategoryIcon());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}
