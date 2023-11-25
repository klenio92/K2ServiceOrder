package com.k2ServiceOrder.serviceOrder;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
	
	@Query(value = "SELECT MAX(serviceId) FROM ServiceOrder")
	Long findMaxServiceId();
}