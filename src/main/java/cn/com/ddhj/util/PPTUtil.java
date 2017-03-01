package cn.com.ddhj.util;

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

import cn.com.ddhj.base.BaseClass;

/**
 * 
 * 类: PPTUtil <br>
 * 描述: ppt幻灯片操作类 <br>
 * 作者: zhy<br>
 * 时间: 2017年2月25日 下午3:48:44
 */
public class PPTUtil extends BaseClass {

	private static PPTUtil self;

	private final static String TEMPLATE_FILE = "/opt/ddhj/report/ppt/template/report.ppt";
	private final static String OUT_REPORT_PPT_PATH = "/opt/ddhj/report/ppt/";
	private final static String OUT_REPORT_PDF_PATH = "/opt/ddhj/report/pdf/";

//	private final static String TEMPLATE_FILE = "E:/report/ppt//template/report.ppt";
//	private final static String OUT_REPORT_PPT_PATH = "E:/report/ppt/";
//	private final static String OUT_REPORT_PDF_PATH = "E:/report/pdf/";

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
		getLogger().logInfo("开始生成报告，报告名称:" + reportName);
		long start = System.currentTimeMillis();
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
			pptToPdf(path, reportName);
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
		long end = System.currentTimeMillis();
		getLogger().logInfo("生成报告结束，结束时间:" + ((end - start) / 1000) + "s");
		return path;
	}

	public boolean pptToPdf(String sourceFile, String reportName) {
		boolean flag = false;
		OpenOfficeConnection connection = null;
		String path = OUT_REPORT_PDF_PATH + reportName + ".pdf";
		try {
			File inputFile = new File(sourceFile);
			if (!inputFile.exists()) {
				return false;// 找不到源文件, 则返回-1
			}
			// 如果目标路径不存在, 则新建该路径
			File outputFile = new File(path);
			if (!outputFile.getParentFile().exists()) {
				outputFile.getParentFile().mkdirs();
			}
			// 这里是OpenOffice的安装目录
//			String OpenOffice_HOME = "C:\\Program Files (x86)\\OpenOffice 4";
			// 如果从文件中读取的URL地址最后一个字符不是 '\'，则添加'\'
//			if (OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '\\') {
//				OpenOffice_HOME += "\\";
//			}
			String OpenOffice_HOME = "/opt/openoffice4/program/ ";
			// 启动OpenOffice的服务
//			String command = OpenOffice_HOME
//					+ "program\\soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";
			String command = OpenOffice_HOME + "soffice.bin -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";
			Process pro = Runtime.getRuntime().exec(command);
			// connect to an OpenOffice.org instance running on port 8100
			connection = new SocketOpenOfficeConnection(8100);
			connection.connect();
			// convert
			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
			converter.convert(inputFile, outputFile);
			// close the connection
			pro.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				connection.disconnect();
		}
		return flag;
	}
}