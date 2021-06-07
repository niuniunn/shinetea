package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    Page<OrderMaster> findByBuyerOpenidOrderByCreateTimeDesc(String buyerOpenid, Pageable pageable);

    @Query(
            value = "select * from order_master " +
                    "where order_id like concat('%',?1,'%') " +
                    "and order_status = ?2 " +
                    "and create_time between ?3 and ?4 " +
                    "order by create_time desc ",
            nativeQuery = true
    )
    Page<OrderMaster> findByConditions(String orderId, Integer orderStatus,
                                       Date startTime, Date endTime, Pageable pageable);

    @Query(
            value = "select * from order_master " +
                    "where order_id like concat('%',?1,'%') " +
                    "and create_time between ?2 and ?3 " +
                    "order by create_time desc",
            nativeQuery = true
    )
    Page<OrderMaster> findByConditionsExceptOrderStatus(String orderId, Date startTime, Date endTime, Pageable pageable);
}
