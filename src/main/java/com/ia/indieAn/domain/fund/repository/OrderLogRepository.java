package com.ia.indieAn.domain.fund.repository;

import com.ia.indieAn.entity.fund.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderLogRepository extends JpaRepository<OrderLog, Integer> {

    @Query(
            value = "select sum(total_price) as total_price from order_log \n"
                    + "where fund_no = :no",
            nativeQuery = true
    )
    int fundTotalRevenue(@Param("no") int fundNo);
}
