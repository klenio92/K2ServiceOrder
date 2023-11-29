package com.k2ServiceOrder.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k2ServiceOrder.serviceOrder.ServiceOrderRepository;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class ReportService {

	@Autowired
	private ServiceOrderRepository repository;

	public JasperPrint generateReport() throws JRException {
		Long maxServiceId = repository.findMaxServiceId();

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("maxServiceId", maxServiceId);

		// Carrega e preenche o relatório
		JasperReport jasperReport = JasperCompileManager.compileReport("/K2ServiceOrder/src/main/resources/k2report.jrxml");
		return JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
	}
}