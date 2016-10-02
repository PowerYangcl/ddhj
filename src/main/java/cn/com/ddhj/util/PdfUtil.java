package cn.com.ddhj.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * 类: PdfUtil <br>
 * 描述: pdf文档处理类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 下午7:20:35
 */
public class PdfUtil {

	private static PdfUtil self;

	public static PdfUtil instance() {
		if (self == null) {
			self = new PdfUtil();
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
		// 打开文档
		document.open();
		document.setPageCount(4);
		// 向文档中添加内容
		document.add(new Paragraph("噪音", titleFont));
		// 噪音描述
		Chunk noiseDesc = new Chunk(
				"噪音，会影响人类的生活。从总体讲噪音是由物体振动产生，凡是妨碍人们正常休息、学习和工作的声音，以及对人们要听的声音产生干扰的声音。噪音污染主要来源于交通运输、车辆鸣笛、工业噪音、建筑施工、社会噪音如音乐厅、高音喇叭、早市和人的大声说话等。且随工业与交通的发展而日 趋严重，噪音污染是除大气污 染，水体污染外的城市第三大污染。",
				textFont);
		document.add(noiseDesc);
		// 分段
		document.add(new Paragraph("\n"));
		// 噪音报告
		Paragraph noisePara = new Paragraph();
		Chunk noistReportDesc = new Chunk(
				"目前您所处的地理区域噪音标准为：0-1类标准（昼间50-55分贝，夜间40-45分贝）：优，属于适宜居住噪声范围，适用于疗养区、高级别墅区、高级宾馆、居住、文教机关等区域；按照普通人的听力水平，50分贝相当于正常交谈的声音，30-40分贝是比较安静的正常环境。此类区域特别适合要求环境安静的人群。",
				textFont);
		noisePara.add(noistReportDesc);
		document.add(noisePara);
		// 关闭文档
		document.close();
	}

	public static void main(String[] args) {
		try {
			PdfUtil.instance().createPDF();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
