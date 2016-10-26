package cn.com.ddhj.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import cn.com.ddhj.base.BaseClass;
import cn.com.ddhj.util.pdf.HeadFootInfoPdfPageEvent;
import cn.com.ddhj.util.pdf.PageBorderEvent;

/**
 * 
 * 类: PdfUtil <br>
 * 描述: pdf文档处理类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 下午7:20:35
 */
public class PdfUtil extends BaseClass {

	private static PdfUtil self;

	public static PdfUtil instance() {
		if (self == null) {
			synchronized (PdfUtil.class) {
				if (self == null)
					self = new PdfUtil();
			}
		}
		return self;
	}

	public String createPDF(String lpName, String reportLevel, JSONArray array, String path, String code)
			throws DocumentException, IOException {
		// 建立com.lowagie.text.Document对象的实例
		Document document = new Document(PageSize.A4, 36.0F, 36.0F, 36.0F, 36.0F);
		// 字体的定义：这里用的是自带的jar里面的字体
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

		Font reportTitleFont = new Font(bfChinese, 32, Font.BOLD);
		Font titleFont = new Font(bfChinese, 14, Font.BOLD);
		titleFont.setColor(218, 112, 214);
		Font titleFont2 = new Font(bfChinese, 20, Font.BOLD);
		titleFont2.setColor(218, 112, 214);
		Font textFont = new Font(bfChinese, 11, Font.NORMAL);

		// 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
		File file = new File(path + "report/temp/" + code + ".pdf");
		if (!file.exists()) {
			file.createNewFile();
		}
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
		writer.setPageEvent(new HeadFootInfoPdfPageEvent(writer, document, bfChinese, path));
		writer.setPageEvent(new PageBorderEvent(writer, document));
		// 添加边距
		document.setMargins(100, 100, 100, 100);
		// 打开文档
		document.open();
		// 添加报告名称

		Paragraph reportTitle = new Paragraph("环境质量报告", reportTitleFont);
		reportTitle.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(reportTitle);
		// 添加报告等级
		// Paragraph levelP = new Paragraph("(" + reportLevel + ")", new
		// Font(bfChinese, 14, Font.BOLD));
		// levelP.setAlignment(Paragraph.ALIGN_CENTER);
		// document.add(levelP);
		for (int i = 0; i < 30; i++) {
			document.add(new Paragraph("\n"));
		}
		// 添加公司名称
		Font companyFont = new Font(bfChinese, 16, Font.BOLD);
		companyFont.setColor(BaseColor.GRAY);
		Paragraph companyName = new Paragraph("北京亿科云科技有限公司", companyFont);
		companyName.setAlignment(Paragraph.ALIGN_CENTER);
		companyName.setPaddingTop(document.bottom());
		document.add(companyName);
		document.newPage();
		// 向文档添加空气质量评分
		Paragraph areaAir = new Paragraph("所测区域环境质量", titleFont2);
		areaAir.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(areaAir);
		if (array != null && array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj = array.getJSONObject(i);
				document.add(new Paragraph(obj.getString("title"), titleFont));
				document.add(new Paragraph("\n"));
				Chunk level = new Chunk(obj.getString("level"), textFont);
				document.add(level);
				document.add(new Paragraph("\n"));
			}
		}
		// 向文档中添加内容
		if (array != null && array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj = array.getJSONObject(i);
				document.add(new Paragraph(obj.getString("title"), titleFont));
				document.add(new Paragraph("\n"));
				// 添加图标
				if (StringUtils.isNotBlank(obj.getString("pic"))) {
					Image image = Image.getInstance(path + obj.getString("pic"));
					image.setAlignment(Image.ALIGN_CENTER);
					image.scaleToFit(200, 110);
					document.add(image);
				}
				document.add(new Paragraph("\n"));
				Chunk content = new Chunk(obj.getString("content"), textFont);
				document.add(content);
				document.add(new Paragraph("\n"));
			}
		}
		// 关闭文档
		document.close();
		if (!document.isOpen()) {
			createWatermark(path, code);
		}

		return path + "/report/" + code + ".pdf";
	}

	public static void createWatermark(String path, String code) {
		PdfReader reader = null;
		PdfStamper stamper = null;
		try {
			reader = new PdfReader(path + "report/temp/" + code + ".pdf");
			stamper = new PdfStamper(reader, new FileOutputStream(path + "report/" + code + ".pdf"));

			PdfContentByte under;
			int total = reader.getNumberOfPages() + 1;
			for (int i = 1; i < total; i++) {
				under = stamper.getUnderContent(i);
				float left = under.getPdfDocument().left();
				float right = under.getPdfDocument().right();
				for (int x = -3; x < 3; x++) {
					for (int y = -3; y < 3; y++) {
						// 添加水印图片
						Image image = Image.getInstance(path + "resource/report/watermark.png");
						image.setGrayFill(20);
						image.scaleToFit(100, 100);
						image.setAbsolutePosition(left + (x * 200), right + (y * 200));
						under.addImage(image);
					}
				}
			}
			stamper.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			File file = new File(path + "report/temp/" + code + ".pdf");
			file.delete();
		}
	}
}
