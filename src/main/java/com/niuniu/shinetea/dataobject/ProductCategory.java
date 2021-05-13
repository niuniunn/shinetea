package com.niuniu.shinetea.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DynamicUpdate
@Data
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   /*自增类型*/
    private Integer categoryId;
    /*类目名字*/
    private String categoryName;
    /*类目编号*/
    private Integer categoryType;

    private String categoryIcon;

    public ProductCategory(String categoryName, Integer categoryType, String categoryIcon) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.categoryIcon = categoryIcon;
    }

    public ProductCategory() {

    }
}
