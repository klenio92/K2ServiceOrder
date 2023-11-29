package com.k2ServiceOrder.controller;

import com.k2ServiceOrder.serviceOrder.ServiceOrder;
import com.k2ServiceOrder.serviceOrder.ServiceOrderRepository;
import com.k2ServiceOrder.serviceOrder.ServiceOrderRequestDTO;
import com.k2ServiceOrder.serviceOrder.ServiceOrderResponseDTO;
import com.k2ServiceOrder.services.ReportService;
import com.k2ServiceOrder.services.ReportService2;
import com.k2ServiceOrder.services.ServiceOrderReport;

import jakarta.servlet.http.HttpServletRequest;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serviceorder")
public class K2ServiceOrderController {

	@Autowired
	private ServiceOrderRepository repository;

	@Autowired
	private ServiceOrderReport serviceOrderReport;

	@Autowired
	private ReportService reportService;

	@Autowired
	private ReportService2 reportService2;

	@GetMapping("/report/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {

		return reportService2.exportReport(format);
	}

	@GetMapping(value = "/generate", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> generatePdfReport() {
		try {
			JasperPrint jasperPrint = reportService.generateReport();

			byte[] pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("attachment", "k2report.pdf");

			return ResponseEntity.ok().headers(headers).body(pdfReport);
		} catch (JRException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// @GetMapping("/getMaxServiceId")
	// public ResponseEntity<Long> getMaxServiceId() {
	// Long maxServiceId = repository.findMaxServiceId();
	// if (maxServiceId != null) {
	// return ResponseEntity.ok(maxServiceId);
	// } else {
	// return ResponseEntity.notFound().build();
	// }
	// }

	// @CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/build", produces = "application/text")
	public ResponseEntity<String> buildOrder(HttpServletRequest request) throws Exception {
		byte[] pdf = serviceOrderReport.buildReport("k2report", request.getServletContext());

		String pdfReport = "data:application/pdf;base64," + Base64.encodeBase64String(pdf);

		return new ResponseEntity<String>(pdfReport, HttpStatus.OK);
	}

	@GetMapping("/maxServiceOrder")
	public ResponseEntity<ServiceOrder> getMaxServiceOrder() {
		Long maxServiceId = repository.findMaxServiceId();
		if (maxServiceId != null) {
			Optional<ServiceOrder> maxServiceOrder = repository.findById(maxServiceId);
			if (maxServiceOrder.isPresent()) {
				return ResponseEntity.ok(maxServiceOrder.get());
			}
		}
		return ResponseEntity.notFound().build();
	}

	// @CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/save")
	public void saveOrder(@RequestBody ServiceOrderRequestDTO data) {
		ServiceOrder serviceOrderData = new ServiceOrder(data);
		repository.save(serviceOrderData);
	}
}