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
		Document document = null;
		PdfWriter writer = null;
		try {
			document = new Document(PageSize.A4, 36.0F, 36.0F, 36.0F, 36.0F);
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
			writer = PdfWriter.getInstance(document, new FileOutputStream(file));
			writer.setPageEvent(new HeadFootInfoPdfPageEvent(writer, document, bfChinese, path));
			writer.setPageEvent(new PageBorderEvent(writer, document));
			// 添加边距
			document.setMargins(100, 100, 100, 100);
			// 打开文档
			document.open();
			// 添加报告名称
			for (int i = 0; i < 10; i++) {
				document.add(new Paragraph("\n"));
			}
			Paragraph reportTitle = new Paragraph("环境质量报告", reportTitleFont);
			reportTitle.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(reportTitle);
			for (int i = 0; i < 2; i++) {
				document.add(new Paragraph("\n"));
			}
			// 楼盘名称
			Paragraph lpTitle = new Paragraph(lpName, new Font(bfChinese, 14, Font.BOLD));
			lpTitle.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(lpTitle);
			for (int i = 0; i < 16; i++) {
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
			document.add(new Paragraph("\n"));
			document.add(new Paragraph(
					"环境质量报告根据所监测的各项环境数据，按一定的标准和方法对某区域范围内的环境质量进行说明、评定和预测。本报告包含以下环境指标：噪音、水质、空气质量、土壤、垃圾处理设施、绿地率（小区）、容积率（小区）。",
					textFont));
			document.add(new Paragraph("\n"));
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭文档
			if (document != null) {
				document.close();
			}
		}
		return "report/" + code + ".pdf";
	}

	/**
	 * 
	 * 方法: createWatermark <br>
	 * 描述: 为模板设置水印 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月26日 下午7:08:31
	 * 
	 * @param path
	 * @param code
	 */
	public void createWatermark(String path, String code) {
		PdfReader reader = null;
		PdfStamper stamper = null;
		try {
			reader = new PdfReader(path + "report/temp/" + code + ".pdf");
			stamper = new PdfStamper(reader, new FileOutputStream(path + "report/" + code + ".pdf"));
			PdfContentByte under;
			int total = reader.getNumberOfPages() + 1;
			for (int i = 1; i < total; i++) {
				under = stamper.getUnderContent(i);
				// 设置背景图
				Image bgImage = Image.getInstance(path + "resource/report/bg.png");
				// 设置图片的位置，参数Image.UNDERLYING是作为文字的背景显示。
				bgImage.setAlignment(Image.UNDERLYING);
				bgImage.setAbsolutePosition(0, 0);
				bgImage.scaleToFit(PageSize.A4);// 大小
				under.addImage(bgImage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stamper != null) {
					stamper.close();
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (reader != null) {
				reader.close();
			}
		}
	}
}
