package com.uat.hbc.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ExportReport {
	
	private Properties props;

	public void generateReport(String jrxmlName, String type,
			ArrayList<?> arrList, Map<String, Object> parameters,
			HttpServletResponse response,Connection conn) throws IOException {
		
		try {
			props = Utils.readProperties("datasource.properties");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String sourceFileName = props.getProperty("server.path")+props.getProperty("report.path")+"/"+jrxmlName;
		//////System.out.println(sourceFileName);

		try {
			JasperDesign jasperDesign = JRXmlLoader.load(sourceFileName);
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					arrList);
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, beanColDataSource);
			// ByteArrayOutputStream out = new ByteArrayOutputStream();

			 int pages = jasperPrint.getPages().size();
			   
			   if(pages == 0){
				   generateBlankReport(parameters, response, conn);
			   }else{
				if ("html".equals(type)) {
					// JRXhtmlExporter exporter = new JRXhtmlExporter ();
					JRExporter exporter = new JRXhtmlExporter();
					exporter.setParameter(
							JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
					exporter.setParameter(JRExporterParameter.JASPER_PRINT,
							jasperPrint);
					exporter.setParameter(JRExporterParameter.OUTPUT_WRITER,
							response.getWriter());

					response.setCharacterEncoding("UTF-8");
					response.setContentType("text/html; charset=UTF-8");
					response.setHeader("Cache-Control", "no-store");
					response.setHeader("Cache-Control", "private");
					response.setHeader("Pragma", "no-store");

					exporter.exportReport();
				} else if ("pdf".equals(type)) {
					byte[] pdfReport = JasperExportManager
							.exportReportToPdf(jasperPrint);
					response.reset();
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/pdf");
					response.setHeader("Cache-Control", "no-store");
					response.setHeader("Cache-Control", "private");
					response.setHeader("Pragma", "no-store");
					response.setContentLength(pdfReport.length);
					response.getOutputStream().write(pdfReport);
					response.getOutputStream().flush();
					response.getOutputStream().close();
				} else if ("rtf".equals(type)) {

					JRRtfExporter exporterRTF = new JRRtfExporter();
					exporterRTF.setParameter(JRExporterParameter.JASPER_PRINT,
							jasperPrint);
					exporterRTF.setParameter(JRExporterParameter.OUTPUT_WRITER,
							response.getWriter());
					// exporterRTF.setParameter(JRExporterParameter.OUTPUT_STREAM,outputStream);
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/rtf");
					response.setHeader("Cache-Control", "no-store");
					response.setHeader("Cache-Control", "private");
					response.setHeader("Pragma", "no-store");
					exporterRTF.exportReport();

				}
			}
		} catch (JRException e) {
			generateBlankReport(parameters, response, conn);
			e.printStackTrace();
		}
	}
	
	public void generatePRCReport(String jrxmlName,
			Map<String, Object> parameters, HttpServletResponse response, Connection conn)
			throws IOException {
		
		try {
			props = Utils.readProperties("datasource.properties");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String sourceFileName = props.getProperty("server.path")+props.getProperty("report.path")+"/"+jrxmlName;
		
		try {
			   JRProperties.setProperty( JRQueryExecuterFactory.QUERY_EXECUTER_FACTORY_PREFIX+"plsql"
                       ,"com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
			 
			   JasperReport jasperReport = JasperCompileManager
						.compileReport(sourceFileName);
			//   ////System.out.println("compiling report..");
			 
			   jasperReport.setProperty( "net.sf.jasperreports.query.executer.factory.plsql"
			                            ,"com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
			 
			   JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
			   
			   int pages = jasperPrint.getPages().size();
			   
			   if(pages == 0){
				   generateBlankReport(parameters, response, conn);
			   }else{
				   byte[] pdfReport = JasperExportManager
							.exportReportToPdf(jasperPrint);
					response.reset();
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/pdf");
					response.setHeader("Cache-Control", "no-store");
					response.setHeader("Cache-Control", "private");
					response.setHeader("Pragma", "no-store");
					response.setContentLength(pdfReport.length);
					response.getOutputStream().write(pdfReport);
					response.getOutputStream().flush();
					response.getOutputStream().close();
			   }
			}
			catch (Exception ex) {
				generateBlankReport(parameters, response, conn);
			   ex.printStackTrace();
			}
	}
	
	public void generateBlankReport(Map<String, Object> parameters,HttpServletResponse response,Connection conn) throws IOException{
		try {
			props = Utils.readProperties("datasource.properties");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String sourceFileName = props.getProperty("server.path")+props.getProperty("report.path")+ "/CommonBlankReport.jrxml";
		//////System.out.println(sourceFileName);
		try {
			JasperReport jasperReport = JasperCompileManager
					.compileReport(sourceFileName);
			//////System.out.println("Filling report...");
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, conn);
			//////System.out.println("jasper print....."+jasperPrint.getPages().size());
			int pages = jasperPrint.getPages().size();//report1
			//////System.out.println("Filling pages..."+pages);
			
			byte[] pdfReport = JasperExportManager
						.exportReportToPdf(jasperPrint);
				response.reset();
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/pdf");
				response.setHeader("Cache-Control", "no-store");
				response.setHeader("Cache-Control", "private");
				response.setHeader("Pragma", "no-store");
				response.setContentLength(pdfReport.length);
			//	////System.out.println("pdfReport.length..."+pdfReport.length);
				response.getOutputStream().write(pdfReport);
				response.getOutputStream().flush();
				response.getOutputStream().close();
			
		}catch(JRException e){
			e.printStackTrace();
		}
	}

	public void generateCommonReport(String jrxmlName, String type,
			Map<String, Object> parameters, HttpServletResponse response,
			Connection conn) throws IOException {

		try {
			props = Utils.readProperties("datasource.properties");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String sourceFileName = props.getProperty("server.path")+props.getProperty("report.path")+ "/CommonBlankReport.jrxml";
		
		try {
			JasperReport jasperReport = JasperCompileManager
					.compileReport(sourceFileName);
			//////System.out.println("Filling report...");
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, conn);
		//	////System.out.println("jasper print....."+jasperPrint.getPages().size());
			int pages = jasperPrint.getPages().size();//report1
			//////System.out.println("Filling pages..."+pages);
			if(pages == 0){
				generateBlankReport(parameters, response, conn);
			}else{
				if (type.equals("pdf")) {
					byte[] pdfReport = JasperExportManager
							.exportReportToPdf(jasperPrint);
					response.reset();
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/pdf");
					response.setHeader("Cache-Control", "no-store");
					response.setHeader("Cache-Control", "private");
					response.setHeader("Pragma", "no-store");
					response.setContentLength(pdfReport.length);
					//////System.out.println("pdfReport.length..."+pdfReport.length);
					response.getOutputStream().write(pdfReport);
					response.getOutputStream().flush();
					response.getOutputStream().close();
				} else if (type.equals("rtf")) {
					JRRtfExporter exporterRTF = new JRRtfExporter();
					exporterRTF.setParameter(JRExporterParameter.JASPER_PRINT,
							jasperPrint);
					exporterRTF.setParameter(JRExporterParameter.OUTPUT_WRITER,
							response.getWriter());
					// exporterRTF.setParameter(JRExporterParameter.OUTPUT_STREAM,outputStream);
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/rtf");
					response.setHeader("Cache-Control", "no-store");
					response.setHeader("Cache-Control", "private");
					response.setHeader("Pragma", "no-store");
					exporterRTF.exportReport();
				} else if (type.equals("excel")) {
					JRXlsExporter exporter = new JRXlsExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT,
							jasperPrint);
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
					exporter.exportReport();
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition", "private");
					response.getOutputStream().write(os.toByteArray());
					response.flushBuffer();
				} else if (type.equals("html")) {
					JRExporter exporter = new JRXhtmlExporter();
					exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,
							"UTF-8");
					exporter.setParameter(JRExporterParameter.JASPER_PRINT,
							jasperPrint);
					exporter.setParameter(JRExporterParameter.OUTPUT_WRITER,
							response.getWriter());
					response.setCharacterEncoding("UTF-8");
					response.setContentType("text/html; charset=UTF-8");
					response.setHeader("Cache-Control", "no-store");
					response.setHeader("Cache-Control", "private");
					response.setHeader("Pragma", "no-store");
					exporter.exportReport();
				}
			}
			// OutputStream outStream = response.getOutputStream();
			// JasperExportManager.exportReportToPdfStream(jasperPrint,
			// outStream);
			/*
			 * JasperExportManager.exportReportToPdfFile(jasperPrint,"report.pdf"
			 * );
			 */
		} catch (JRException e) {
			generateBlankReport(parameters, response, conn);
			e.printStackTrace();
		}
	}
	public byte[] generateandSaveSanctionReport(String jrxmlName, String type,
			Map<String, Object> parameters, HttpServletResponse response,
			Connection conn){
		
		try {
			props = Utils.readProperties("datasource.properties");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String sourceFileName = props.getProperty("server.path")+props.getProperty("report.path")+ "/CommonBlankReport.jrxml";
		
		String status = "";
		byte[] pdfReportreturn = null;
//		File destFile = new File(destFileName);
		
		try{
			JasperReport jasperReport = JasperCompileManager
					.compileReport(sourceFileName);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, conn);

			int pages = jasperPrint.getPages().size();
			
			if(pages == 0){
				status = "failed";
				generateBlankReport(parameters, response, conn);
			}else{
				if (type.equals("pdf")) {
					byte[] pdfReport = JasperExportManager
							.exportReportToPdf(jasperPrint);
					response.reset();
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/pdf");
					response.setHeader("Cache-Control", "no-store");
					response.setHeader("Cache-Control", "private");
					response.setHeader("Pragma", "no-store");
					response.setContentLength(pdfReport.length);
					System.err.println("pdfReport.length..."+pdfReport.length);
					response.getOutputStream().write(pdfReport);
					pdfReportreturn = pdfReport;
//					FileOutputStream outStream = new FileOutputStream(destFile);
					
//					OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outStream);
//					outStream.write(pdfReport);
//					outStream.flush();
//					outStream.close();
//					outputStreamWriter.flush();
//					outputStreamWriter.close();
					response.getOutputStream().flush();
					response.getOutputStream().close();
				} else if (type.equals("rtf")) {
					JRRtfExporter exporterRTF = new JRRtfExporter();
					exporterRTF.setParameter(JRExporterParameter.JASPER_PRINT,
							jasperPrint);
					exporterRTF.setParameter(JRExporterParameter.OUTPUT_WRITER,
							response.getWriter());
					// exporterRTF.setParameter(JRExporterParameter.OUTPUT_STREAM,outputStream);
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/rtf");
					response.setHeader("Cache-Control", "no-store");
					response.setHeader("Cache-Control", "private");
					response.setHeader("Pragma", "no-store");
					exporterRTF.exportReport();
				} else if (type.equals("excel")) {
					JRXlsExporter exporter = new JRXlsExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT,
							jasperPrint);
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
					exporter.exportReport();
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition", "private");
					response.getOutputStream().write(os.toByteArray());
					response.flushBuffer();
				} else if (type.equals("html")) {
					JRExporter exporter = new JRXhtmlExporter();
					exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,
							"UTF-8");
					exporter.setParameter(JRExporterParameter.JASPER_PRINT,
							jasperPrint);
					exporter.setParameter(JRExporterParameter.OUTPUT_WRITER,
							response.getWriter());
					response.setCharacterEncoding("UTF-8");
					response.setContentType("text/html; charset=UTF-8");
					response.setHeader("Cache-Control", "no-store");
					response.setHeader("Cache-Control", "private");
					response.setHeader("Pragma", "no-store");
					exporter.exportReport();
				}
				status = "success";
			}
		}catch(JRException | IOException e){
			/*status = "failed";
			try {
				generateBlankReport(parameters, response, conn);
			} catch (IOException e1) {
				e1.printStackTrace();
			}*/
			e.printStackTrace();
		}
		
		
		return pdfReportreturn;
	}
}
