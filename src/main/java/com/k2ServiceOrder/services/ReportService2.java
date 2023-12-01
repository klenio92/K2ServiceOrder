package com.k2ServiceOrder.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.k2ServiceOrder.serviceOrder.ServiceOrder;
import com.k2ServiceOrder.serviceOrder.ServiceOrderRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService2 {

	@Autowired
	private ServiceOrderRepository repository;

	public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
		String path = "C:\\reports";
		List<ServiceOrder> serviceOrders = repository.findMaxServiceId();

		File file = ResourceUtils.getFile("classpath:k2report.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(serviceOrders);
		Map<String, Object> parameters = new HashMap<>();
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\k2report.html");
		}
		if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\k2report.pdf");
		}
		return path;
	}
}