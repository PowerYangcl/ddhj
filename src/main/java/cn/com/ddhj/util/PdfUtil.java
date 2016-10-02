package cn.com.ddhj.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import cn.com.ddhj.annotation.Inject;
import cn.com.ddhj.base.BaseClass;
import cn.com.ddhj.model.TReportTemplate;
import cn.com.ddhj.service.ITReportTemplateService;

/**
 * 
 * 类: PdfUtil <br>
 * 描述: pdf文档处理类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 下午7:20:35
 */
public class PdfUtil extends BaseClass {

	@Inject
	private ITReportTemplateService service;

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

	public void createPDF() throws DocumentException, IOException {
		// 建立com.lowagie.text.Document对象的实例
		Document document = new Document(PageSize.A4, 36.0F, 36.0F, 36.0F, 36.0F);
		// 字体的定义：这里用的是自带的jar里面的字体
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

		Font titleFont = new Font(bfChinese, 12, Font.NORMAL);
		Font textFont = new Font(bfChinese, 8, Font.NORMAL);
		// 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
		PdfWriter.getInstance(document, new FileOutputStream(new File("D:\\report.pdf")));
		document.addTitle("环境质量报告");
		document.addSubject("环境质量报告");
		List<TReportTemplate> list = service.findReportTemplateAll();
		// 打开文档
		document.open();
		document.setPageCount(4);
		// 向文档中添加内容
		document.add(new Paragraph(getTemplateContent("report", list), textFont));
		document.add(new Paragraph("\n"));
		// 噪音
		document.add(new Paragraph("噪音", titleFont));
		Chunk noiseDesc = new Chunk(getTemplateContent("noise", list), textFont);
		document.add(noiseDesc);
		document.add(new Paragraph("\n"));
		// 水质
		document.add(new Paragraph("水质", titleFont));
		Chunk waterDesc = new Chunk(getTemplateContent("water", list), textFont);
		document.add(waterDesc);
		document.add(new Paragraph("\n"));
		// 空气质量
		document.add(new Paragraph("空气质量", titleFont));
		Chunk airDesc = new Chunk(getTemplateContent("air", list), textFont);
		document.add(airDesc);
		document.add(new Paragraph("\n"));
		// 土壤
		document.add(new Paragraph("土壤", titleFont));
		Chunk soilDesc = new Chunk(getTemplateContent("soil", list), textFont);
		document.add(soilDesc);
		document.add(new Paragraph("\n"));
		// 垃圾处理设施
		document.add(new Paragraph("垃圾处理设施", titleFont));
		Chunk rubbishDesc = new Chunk(getTemplateContent("rubbish", list), textFont);
		document.add(rubbishDesc);
		document.add(new Paragraph("\n"));
		// 绿地率
		document.add(new Paragraph("绿地率", titleFont));
		Chunk afforestDesc = new Chunk(getTemplateContent("afforest", list), textFont);
		document.add(afforestDesc);
		document.add(new Paragraph("\n"));
		// 容积率
		document.add(new Paragraph("容积率", titleFont));
		Chunk volumeDesc = new Chunk(getTemplateContent("volume", list), textFont);
		document.add(volumeDesc);
		document.add(new Paragraph("\n"));
		// 关闭文档
		document.close();
	}

	/**
	 * 
	 * 方法: getTemplateContent <br>
	 * 描述: 获取模板的内容 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月3日 上午12:24:32
	 * 
	 * @param type
	 * @param list
	 * @return
	 */
	private static String getTemplateContent(String type, List<TReportTemplate> list) {
		String content = "";
		if (list != null && list.size() > 0) {
			for (TReportTemplate model : list) {
				if (type.equals(model.getType())) {
					content = model.getContent();
					break;
				}
			}
		}
		return content;
	}
}
