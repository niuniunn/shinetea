package com.niuniu.shinetea.service.impl;

import com.niuniu.shinetea.dataobject.ProductCategory;
import com.niuniu.shinetea.enums.ResultEnum;
import com.niuniu.shinetea.exception.ShineTeaException;
import com.niuniu.shinetea.repository.ProductCategoryRepository;
import com.niuniu.shinetea.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findById(Integer categoryId) {
        return repository.findById(categoryId).orElse(null);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Integer categoryId) {
        ProductCategory productCategory = repository.findById(categoryId).orElse(null);
        if(productCategory == null) {
            throw new ShineTeaException(ResultEnum.CATEGORY_NOT_EXIST);
        }
        repository.deleteById(categoryId);
    }
}
