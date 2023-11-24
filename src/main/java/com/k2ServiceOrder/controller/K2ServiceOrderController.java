package com.k2ServiceOrder.controller;

import com.k2ServiceOrder.serviceOrder.ServiceOrder;
import com.k2ServiceOrder.serviceOrder.ServiceOrderRepository;
import com.k2ServiceOrder.serviceOrder.ServiceOrderRequestDTO;
import com.k2ServiceOrder.services.ServiceOrderReport;

import jakarta.servlet.http.HttpServletRequest;


import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serviceorder")
public class K2ServiceOrderController {

	@Autowired
	private ServiceOrderRepository repository;

	@Autowired
	private ServiceOrderReport serviceOrderReport;

	// @CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/build", produces = "application/text")
	public ResponseEntity<String> buildOrder(HttpServletRequest request) throws Exception {
		byte[] pdf = serviceOrderReport.buildReport("k2report", request.getServletContext());

		String pdfReport = "data:application/pdf;base64," + Base64.encodeBase64String(pdf);

		return new ResponseEntity<String>(pdfReport, HttpStatus.OK);
	}

	// @CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/save")
	public void saveOrder(@RequestBody ServiceOrderRequestDTO data) {
		ServiceOrder serviceOrderData = new ServiceOrder(data);
		repository.save(serviceOrderData);
	}
}