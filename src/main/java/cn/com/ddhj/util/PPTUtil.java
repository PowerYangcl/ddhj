package cn.com.ddhj.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * 
 * 类: PPTUtil <br>
 * 描述: ppt幻灯片操作类 <br>
 * 作者: zhy<br>
 * 时间: 2017年2月25日 下午3:48:44
 */
public class PPTUtil {

	private static PPTUtil self;

	private final static String TEMPLATE_FILE = "/opt/ddhj/report/ppt/template/report.ppt";
	private final static String OUT_REPORT_PPT_PATH = "/opt/ddhj/report/ppt/";
	private final static String OUT_REPORT_PDF_PATH = "/opt/ddhj/report/pdf/";

	public static PPTUtil instance() {
		if (self == null) {
			synchronized (PPTUtil.class) {
				if (self == null)
					self = new PPTUtil();
			}
		}
		return self;
	}

	public String createReport(Map<String, String> map, String reportName) {
		String path = "";
		FileOutputStream out = null;
		try {
			File file = new File(TEMPLATE_FILE);
			HSLFSlideShow _hslf = new HSLFSlideShow(new FileInputStream(file));
			SlideShow _slideShow = new SlideShow(_hslf);
			Slide[] slides = _slideShow.getSlides();
			for (Slide slide : slides) {
				TextRun[] textRuns = slide.getTextRuns();
				for (TextRun textRun : textRuns) {
					RichTextRun[] richs = textRun.getRichTextRuns();
					for (RichTextRun richTextRun : richs) {
						String text = richTextRun.getRawText();
						if (StringUtils.substringsBetween(text, "${", "}") != null) {
							String[] keys = StringUtils.substringsBetween(text, "${", "}");
							for (String key : keys) {
								if (StringUtils.isNotBlank(key)) {
									String value = map.get(key);
									text = StringUtils.replace(text, "${" + key + "}", value);
									richTextRun.setText(text);
								}
							}
						}
					}

				}
			}
			path = OUT_REPORT_PPT_PATH + reportName + ".ppt";
			out = new FileOutputStream(new File(path));
			_slideShow.write(out);
			out.close();
			//pptToPdf(path, reportName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path;
	}

	public static void convertPPTToPDF(String reportName, String filePath) throws Exception {
		String path = OUT_REPORT_PDF_PATH + reportName + ".pdf";
		FileInputStream inputStream = new FileInputStream(filePath);
		double zoom = 2;
		AffineTransform at = new AffineTransform();
		at.setToScale(zoom, zoom);
		Document pdfDocument = new Document();
		PdfWriter pdfWriter = PdfWriter.getInstance(pdfDocument, new FileOutputStream(new File(path)));
		PdfPTable table = new PdfPTable(1);
		pdfWriter.open();
		pdfDocument.open();
		Dimension pgsize = null;
		Image slideImage = null;
		BufferedImage img = null;
		SlideShow ppt = new SlideShow(inputStream);
		inputStream.close();
		pgsize = ppt.getPageSize();
		Slide slide[] = ppt.getSlides();
		pdfDocument.setPageSize(new Rectangle((float) pgsize.getWidth(), (float) pgsize.getHeight()));
		pdfWriter.open();
		pdfDocument.open();
		for (int i = 0; i < slide.length; i++) {
			img = new BufferedImage((int) Math.ceil(pgsize.width * zoom), (int) Math.ceil(pgsize.height * zoom),
					BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = img.createGraphics();
			graphics.setTransform(at);
			graphics.setPaint(Color.white);
			graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
			slide[i].draw(graphics);
			graphics.getPaint();
			slideImage = Image.getInstance(img, null);
			table.addCell(new PdfPCell(slideImage, true));
		}
		pdfDocument.add(table);
		pdfDocument.close();
		pdfWriter.close();
		System.out.println("Powerpoint file converted to PDF successfully");
	}

	public boolean pptToPdf(String sourceFile, String reportName) {
		boolean flag = false;
		OpenOfficeConnection connection = null;
		String path = OUT_REPORT_PDF_PATH + reportName + ".pdf";
		try {
			File inputFile = new File(sourceFile);
			if (!inputFile.exists()) {
				return flag;
			}
			// 如果目标路径不存在, 则新建该路径
			File outputFile = new File(path);
			if (!outputFile.getParentFile().exists()) {
				outputFile.getParentFile().mkdirs();
			}
			// 这里是OpenOffice的安装目录
//			String OpenOffice_HOME = "C:\\Program Files (x86)\\OpenOffice 4";
			String OpenOffice_HOME = "/opt/openoffice4/";
			// 如果从文件中读取的URL地址最后一个字符不是 '\'，则添加'\'
			if (OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '\\') {
				OpenOffice_HOME += "\\";
			}
			// 启动OpenOffice的服务
//			String command = OpenOffice_HOME
//					+ "program\\soffice.exe -headless-accept=\"socket,host=127.0.0.1,port=8100;urp;\"";
			String command = OpenOffice_HOME+"soffice -headless-accept=\"socket,host=127.0.0.1,port=8100;urp;\"-nofirststartwizard";
			Runtime.getRuntime().exec(command);
			// connect to an OpenOffice.org instance running on port 8100
			connection = new SocketOpenOfficeConnection(8100);
			connection.connect();
			// convert
			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
			converter.convert(inputFile, outputFile);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				connection.disconnect();
		}
		return flag;
	}
}