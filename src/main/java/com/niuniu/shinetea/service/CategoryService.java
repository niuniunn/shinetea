package com.niuniu.shinetea.service;

import com.niuniu.shinetea.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {

    ProductCategory findById(Integer categoryId);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

    List<ProductCategory> findAll();

    void deleteById(Integer categoryId);

}
