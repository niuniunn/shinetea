package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.ShopInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShopInfoRepository extends JpaRepository<ShopInfo, Integer> {

    @Query(
            value = "select * from shop_info " +
                    "where shop_name like concat('%',?1,'%') " +
                    "and address like concat('%',?2,'%') " +
                    "and is_open like concat('%',?3,'%') ",
            nativeQuery = true
    )
    Page<ShopInfo> findByNameAndAddressAndStatus(String shopName, String address, Integer isOpen, Pageable pageable);

    @Query(
            value = "select * from shop_info " +
                    "where shop_name like concat('%',?1,'%') " +
                    "and address like concat('%',?2,'%') ",
            nativeQuery = true
    )
    Page<ShopInfo> findByNameAndAddress(String shopName, String address, Pageable pageable);
}
