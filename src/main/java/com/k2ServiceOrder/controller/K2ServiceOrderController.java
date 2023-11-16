package com.k2ServiceOrder.controller;

import com.k2ServiceOrder.serviceOrder.ServiceOrder;
import com.k2ServiceOrder.serviceOrder.ServiceOrderRepository;
import com.k2ServiceOrder.serviceOrder.ServiceOrderRequestDTO;
import com.k2ServiceOrder.serviceOrder.ServiceOrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serviceorder")
public class K2ServiceOrderController {

    @Autowired
    private ServiceOrderRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ServiceOrderResponseDTO> getAll(){
        List<ServiceOrderResponseDTO> ordersList = repository.findAll().stream().map(ServiceOrderResponseDTO::new).toList();
        return ordersList;
    }

    //@CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveOrder(@RequestBody ServiceOrderRequestDTO data){
        ServiceOrder serviceOrderData = new ServiceOrder(data);
        repository.save(serviceOrderData);
    }
}