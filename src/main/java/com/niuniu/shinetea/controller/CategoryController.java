package com.niuniu.shinetea.controller;

import com.niuniu.shinetea.VO.ResultVO;
import com.niuniu.shinetea.dataobject.ProductCategory;
import com.niuniu.shinetea.enums.ResultEnum;
import com.niuniu.shinetea.exception.ShineTeaException;
import com.niuniu.shinetea.form.CategoryForm;
import com.niuniu.shinetea.service.CategoryService;
import com.niuniu.shinetea.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //查询所有分类信息
    @GetMapping("/list")
    public ResultVO list() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        return ResultVOUtil.success(productCategoryList);
    }

    //新增分类
    @PostMapping("/create")
    public ResultVO create(@Valid CategoryForm categoryForm,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【创建分类】参数不正确，categoryForm={}",categoryForm);
            throw new ShineTeaException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(categoryForm.getCategoryType());
        productCategory.setCategoryName(categoryForm.getCategoryName());
        productCategory.setCategoryIcon(categoryForm.getCategoryIcon());
        categoryService.save(productCategory);
        return ResultVOUtil.success();
    }

    //根据id删除分类
    @PostMapping("/del")
    public ResultVO delete(@RequestParam("categoryId") Integer categoryId) {
        if(categoryService.findById(categoryId) != null) {
            categoryService.deleteById(categoryId);
            return ResultVOUtil.success();
        }
        else {
            return ResultVOUtil.error(ResultEnum.CATEGORY_NOT_EXIST.getCode(),ResultEnum.CATEGORY_NOT_EXIST.getMessage());
        }
    }

    //编辑分类
    @PostMapping("/edit")
    public ResultVO edit(@Valid CategoryForm categoryForm,
                         BindingResult bindingResult) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(categoryForm.getCategoryId());
        productCategory.setCategoryType(categoryForm.getCategoryType());
        productCategory.setCategoryName(categoryForm.getCategoryName());
        productCategory.setCategoryIcon(categoryForm.getCategoryIcon());
        categoryService.save(productCategory);
        return ResultVOUtil.success();
    }
}
