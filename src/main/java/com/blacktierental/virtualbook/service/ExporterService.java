package com.blacktierental.virtualbook.service;

import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;

public interface ExporterService {

	HttpServletResponse export(String type, JasperPrint jp, 
			HttpServletResponse response, ByteArrayOutputStream baos);
	
	void exportPdf(JasperPrint jp, ByteArrayOutputStream baos);
}
