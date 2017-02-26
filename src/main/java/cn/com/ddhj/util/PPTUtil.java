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
import org.apache.poi.hslf.usermodel.SlideShow;

/**
 * 
 * 类: PPTUtil <br>
 * 描述: ppt幻灯片操作类 <br>
 * 作者: zhy<br>
 * 时间: 2017年2月25日 下午3:48:44
 */
public class PPTUtil {

	private static PPTUtil self;

	private final static String TEMPLATE_FILE = "E:/report/ppt/template/report.ppt";
	private final static String OUT_REPORT_PATH = "E:/report/ppt/";

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
					String text = textRun.getRawText();

					if (StringUtils.substringsBetween(text, "${", "}") != null) {
						String[] keys = StringUtils.substringsBetween(text, "${", "}");
						for (String key : keys) {
							if (StringUtils.isNotBlank(key)) {
								String value = map.get(key);
								text = StringUtils.replace(text, "${" + key + "}", value);
							}
						}
					}
					textRun.setRawText(text);
				}
			}
			path = OUT_REPORT_PATH + reportName + ".ppt";
			out = new FileOutputStream(new File(path));
			_slideShow.write(out);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path;
	}
}