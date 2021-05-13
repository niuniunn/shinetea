package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

    @Query(
            value = "select * from order_master " +
                    "where order_id like concat('%',?1,'%') " +
                    "and order_type = ?2 " +
                    "and order_status = ?3 " +
                    "and create_time between ?4 and ?5 ",
            nativeQuery = true
    )
    Page<OrderMaster> findByConditions(String orderId, Integer orderType, Integer orderStatus,
                                       Date startTime, Date endTime, Pageable pageable);
}
