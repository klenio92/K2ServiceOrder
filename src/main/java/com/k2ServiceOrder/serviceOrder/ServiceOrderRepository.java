package com.k2ServiceOrder.serviceOrder;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
	
	@Query(value = "SELECT * FROM service_order WHERE service_id = (SELECT MAX(service_id) FROM service_order)", nativeQuery = true)
	List<ServiceOrder> findMaxServiceId();
}