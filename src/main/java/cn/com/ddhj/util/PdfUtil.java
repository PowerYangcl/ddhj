package cn.com.ddhj.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import cn.com.ddhj.base.BaseClass;

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

	public String createPDF(String lpName, String reportLevel, JSONArray array, String path)
			throws DocumentException, IOException {
		// 建立com.lowagie.text.Document对象的实例
		Document document = new Document(PageSize.A4, 36.0F, 36.0F, 36.0F, 36.0F);
		// 字体的定义：这里用的是自带的jar里面的字体
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

		Font nameFont = new Font(bfChinese, 16, Font.BOLD);
		Font titleFont = new Font(bfChinese, 12, Font.NORMAL);
		Font textFont = new Font(bfChinese, 8, Font.NORMAL);

		// 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		PdfWriter.getInstance(document, new FileOutputStream(file));
		// 打开文档
		document.open();
		// 添加报告名称
		Paragraph reportTitle = new Paragraph(lpName + "环境测评报告", nameFont);
		reportTitle.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(reportTitle);
		// 添加报告等级
		Paragraph levelP = new Paragraph("(" + reportLevel + ")", titleFont);
		levelP.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(levelP);
		document.add(new Paragraph(new Chunk("\n")));
		// 向文档中添加内容
		if (array != null && array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj = array.getJSONObject(i);
				document.add(new Paragraph(obj.getString("title"), titleFont));
				Chunk content = new Chunk(obj.getString("content"), textFont);
				document.add(content);
				document.add(new Paragraph("\n"));
				Chunk level = new Chunk(obj.getString("level"), textFont);
				document.add(level);
				document.add(new Paragraph("\n"));
			}
		}
		// 关闭文档
		document.close();
		return path;
	}

	/**
	 * 
	 * 方法: getHead <br>
	 * 描述: 设置页眉 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月25日 下午6:00:39
	 * 
	 * @param writer
	 * @param document
	 * @return
	 */
	private static PdfContentByte getHeadAndFoot(PdfWriter writer, Document document, BaseFont font) {
		PdfContentByte headAndFootPdfContent = writer.getDirectContent();
		try {
			headAndFootPdfContent.saveState();
			headAndFootPdfContent.beginText();
			// 设置中文
			headAndFootPdfContent.setFontAndSize(font, 12);
			// ================ 设置页眉 start ===============
			// 左侧图片
			float x = document.top(-5);
			Image image = Image.getInstance("");
			image.setAbsolutePosition(document.left() + 100, x);
			// 页头信息右侧
			headAndFootPdfContent.showTextAligned(PdfContentByte.ALIGN_RIGHT, "亿科云版权所有 侵权必究",
					(document.right() - 100) / 2, x, 0);
			// =============== 设置页眉 end =====================
			// ================== 设置页脚 start ==============
			float y = document.bottom(-35);
			headAndFootPdfContent.showTextAligned(PdfContentByte.ALIGN_LEFT, document.getPageNumber() + "  北京亿科云科技有限公司",
					document.left() + 100, y, 0);
			// ================== 设置页脚 end ==============
			headAndFootPdfContent.endText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return headAndFootPdfContent;
	}
}
