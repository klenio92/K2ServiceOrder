package com.k2ServiceOrder.services;

import jakarta.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ServiceOrderReport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public byte[] buildReport(String reportName, ServletContext servletContext) throws Exception {
		// servlet context esta com biblioteca diferente pois nao existe javax.servlet

		Connection connection = jdbcTemplate.getDataSource().getConnection(); // conexao com o banco

		String jasperPath = servletContext
				.getRealPath("/k2report.jasper") + File.separator + reportName + ".jasper";
		// package reports

		JasperPrint print = JasperFillManager.fillReport(jasperPath, new HashMap<>(), connection);
		// Gera relatorio

		byte[] returnn = JasperExportManager.exportReportToPdf(print);
		connection.close();

		return returnn;
		// exporta para byte[] em pdf/html
	}
}