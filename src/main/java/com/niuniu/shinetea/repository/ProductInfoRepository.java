package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {

    @Query(
            value = "select * from product_info " +
                    "where product_name like concat('%',?1,'%') " +
                    "and category_type like concat('%',?2,'%') " +
                    "and product_status = ?3 ",
            nativeQuery = true
    )
    Page<ProductInfo> findByNameAndTypeAndStatus(String productName, Integer categoryType, Integer productStatus, Pageable pageable);

    @Query(
            value = "select * from product_info " +
                    "where product_name like concat('%',?1,'%') " +
                    "and category_type like concat('%',?2,'%') ",
            nativeQuery = true
    )
    Page<ProductInfo> findByNameAndType(String productName, Integer categoryType, Pageable pageable);


    List<ProductInfo> findByProductStatus(Integer productStatus);
}
