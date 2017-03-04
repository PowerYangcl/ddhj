package cn.com.ddhj.util;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hslf.usermodel.HSLFShape;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFTextBox;
import org.apache.poi.sl.usermodel.AutoShape;
import org.apache.poi.sl.usermodel.Line;
import org.apache.poi.ss.usermodel.Picture;

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

//	private final static String TEMPLATE_FILE = "/opt/ddhj/report/ppt/template/report.ppt";
//	private final static String OUT_REPORT_PPT_PATH = "/opt/ddhj/report/ppt/";
//	private final static String OUT_REPORT_PDF_PATH = "/opt/ddhj/report/pdf/";
//	private final static String OpenOffice_HOME = "/opt/openoffice4/program/soffice.bin";

	private final static String TEMPLATE_FILE = "D:/report/ppt/template/report.ppt";
	private final static String OUT_REPORT_PPT_PATH = "D:/report/ppt/";
	private final static String OUT_REPORT_PDF_PATH = "D:/report/pdf/";
	private final static String OpenOffice_HOME = "D:/app/OpenOffice413/program/soffice.exe ";
	
	private static Process process;
	 
	public static PPTUtil instance() {
		if (self == null) {
			synchronized (PPTUtil.class) {
				if (self == null)
					self = new PPTUtil();
				
				String command = OpenOffice_HOME + " -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";
				try {
					process = Runtime.getRuntime().exec(command);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
//			SlideShow _slideShow = new SlideShow(_hslf);
			List<HSLFSlide> slideList = _hslf.getSlides();
			for(HSLFSlide slide: slideList) {
				List<HSLFShape> shapeList = slide.getShapes();
				for(HSLFShape shape : shapeList) {
					shape.getAnchor().getWidth();
					shape.getShapeName();
					if (shape instanceof Line) {  
	                    Line line = (Line) shape;  
	                    // work with Line  
	                } else if (shape instanceof HSLFTextBox) {  
	                	HSLFTextBox tb = (HSLFTextBox) shape;  
						String text = tb.getText();
						if (StringUtils.substringsBetween(text, "${", "}") != null) {
							String[] keys = StringUtils.substringsBetween(text, "${", "}");
							for (String key : keys) {
								if (StringUtils.isNotBlank(key)) {
									String value = map.get(key);
									text = StringUtils.replace(text, "${" + key + "}", value);
									if(key.equals("air.level") || key.equals("water.level") 
											|| key.equals("soil.level") || key.equals("noise.level")) {
										text += "级";
									}
									tb.setText(text);
								}
							}
						} else if(text.equals("fp1")) {
							//空气百分比
	                    	double percent = Double.parseDouble(map.get("air.level.percent"));
	                    	String percentStr = (int)(percent * 100) + "%";
	                    	tb.setText(percentStr);
						} else if(text.equals("fp2")) {
							//水质百分比
							double percent = Double.parseDouble(map.get("water.level.percent"));
	                    	String percentStr = (int)(percent * 100) + "%";
	                    	tb.setText(percentStr);
						} else if(text.equals("fp3")) {
							//噪音百分比
							double percent = Double.parseDouble(map.get("noise.level.percent"));
	                    	String percentStr = (int)(percent * 100) + "%";
	                    	tb.setText(percentStr);
						} else if(text.equals("fp4")) {
							//土壤
							double percent = Double.parseDouble(map.get("soil.level.percent"));
	                    	String percentStr = (int)(percent * 100) + "%";
	                    	tb.setText(percentStr);
						} else if(text.equals("fp5")) {
							//污染源
							double percent = Double.parseDouble(map.get("sourceOfPollution.level.percent"));
	                    	String percentStr = (int)(percent * 100) + "%";
	                    	tb.setText(percentStr);
						} else if(text.equals("fp6")) {
							//辐射源
							double percent = Double.parseDouble(map.get("sourceOfRadiation.level.percent"));
	                    	String percentStr = (int)(percent * 100) + "%";
	                    	tb.setText(percentStr);
						} else if(text.equals("fp7")) {
							//容积率
							double percent = Double.parseDouble(map.get("volume.level.percent"));
	                    	String percentStr = (int)(percent * 100) + "%";
	                    	tb.setText(percentStr);
						} else if(text.equals("fp8")) {
							//绿地率
							double percent = Double.parseDouble(map.get("afforest.level.percent"));
	                    	String percentStr = (int)(percent * 100) + "%";
	                    	tb.setText(percentStr);
						}  
	                } else if (shape instanceof Picture) {  
	                    Picture pic = (Picture) shape;  
	                    // work with Picture  
	                } else if (shape instanceof AutoShape) {  
	                    AutoShape as = (AutoShape) shape; 
	                    String name = as.getText();
	                    double x = as.getAnchor().getX();
                    	double y = as.getAnchor().getY();
                    	double width = as.getAnchor().getWidth();
                    	double height = as.getAnchor().getHeight();
                    	System.out.println(name);
	                    if(name.equals("f1")) {
	                    	//空气
	                    	double percent = Double.parseDouble(map.get("air.level.percent"));
	                    	width = percent * width;
	                    	as.setText("");
	                    } else if(name.equals("f2")) {
	                    	//水质
	                    	double percent = Double.parseDouble(map.get("water.level.percent"));
	                    	width = percent * width;
	                    	as.setText("");
	                    } else if(name.equals("f3")) {
	                    	//噪音
	                    	double percent = Double.parseDouble(map.get("noise.level.percent"));
	                    	width = percent * width;
	                    	as.setText("");
	                    } else if(name.equals("f4")) {
	                    	//土壤
	                    	double percent = Double.parseDouble(map.get("soil.level.percent"));
	                    	width = percent * width;
	                    	as.setText("");
	                    } else if(name.equals("f5")) {
	                    	//污染源
	                    	double percent = Double.parseDouble(map.get("sourceOfPollution.level.percent"));
	                    	width = percent * width;
	                    	as.setText("");
	                    } else if(name.equals("f6")) {
	                    	//辐射源
	                    	double percent = Double.parseDouble(map.get("sourceOfRadiation.level.percent"));
	                    	width = percent * width;
	                    	as.setText("");
	                    } else if(name.equals("f7")) {
	                    	//容积率
	                    	double percent = Double.parseDouble(map.get("volume.level.percent"));
	                    	width = percent * width;
	                    	as.setText("");
	                    } else if(name.equals("f8")) {
	                    	//绿地率
	                    	double percent = Double.parseDouble(map.get("afforest.level.percent"));
	                    	width = percent * width;
	                    	as.setText("");
	                    }
	                    
	                    Rectangle r = new Rectangle();
                    	r.setRect(x, y, width, height);
                    	as.setAnchor(r);
	                }  
					
				}
				
			}

			path = OUT_REPORT_PPT_PATH + reportName + ".ppt";
			out = new FileOutputStream(new File(path));
			_hslf.write(out);
			out.close();
			pptToPdf(path, reportName);
			
//			File file = new File(TEMPLATE_FILE);
//			HSLFSlideShow _hslf = new HSLFSlideShow(new FileInputStream(file));
//			SlideShow _slideShow = new SlideShow(_hslf);
//			Slide[] slides = _slideShow.getSlides();
//			for (Slide slide : slides) {
//				Shape[] shapes = slide.getShapes();
//				for(Shape s : shapes) {
//					Dimension d =s.getAnchor().getSize();
//					System.out.println(d);
//				}
//				TextRun[] textRuns = slide.getTextRuns();
//				for (TextRun textRun : textRuns) {
//					RichTextRun[] richs = textRun.getRichTextRuns();
//					for (RichTextRun richTextRun : richs) {
//						String text = richTextRun.getRawText();
//						if (StringUtils.substringsBetween(text, "${", "}") != null) {
//							String[] keys = StringUtils.substringsBetween(text, "${", "}");
//							for (String key : keys) {
//								if (StringUtils.isNotBlank(key)) {
//									String value = map.get(key);
//									text = StringUtils.replace(text, "${" + key + "}", value);
//									richTextRun.setText(text);
//								}
//							}
//						}
//					}
//
//				}
//			}
//			path = OUT_REPORT_PPT_PATH + reportName + ".ppt";
//			out = new FileOutputStream(new File(path));
//			_slideShow.write(out);
//			out.close();
//			pptToPdf(path, reportName);
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
			// convert
			connection = new SocketOpenOfficeConnection(8100);
			connection.connect();
			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
			converter.convert(inputFile, outputFile);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				connection.disconnect();
		}
		return flag;
	}
	
	public void release() {
		if(process != null)
			process.destroy();
	}
}