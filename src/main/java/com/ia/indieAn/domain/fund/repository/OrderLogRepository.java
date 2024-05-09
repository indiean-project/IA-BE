package com.ia.indieAn.domain.fund.repository;

import com.ia.indieAn.entity.fund.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLogRepository extends JpaRepository<OrderLog, Integer> {
}
