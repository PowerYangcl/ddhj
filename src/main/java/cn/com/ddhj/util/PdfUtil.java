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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
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

	public String createPDF(JSONArray array, String path) throws DocumentException, IOException {
		// 建立com.lowagie.text.Document对象的实例
		Document document = new Document(PageSize.A4, 36.0F, 36.0F, 36.0F, 36.0F);
		// 字体的定义：这里用的是自带的jar里面的字体
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

		Font titleFont = new Font(bfChinese, 12, Font.NORMAL);
		Font textFont = new Font(bfChinese, 8, Font.NORMAL);
		
		// 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.addTitle("环境质量报告");
		document.addSubject("环境质量报告");
		// 打开文档
		document.open();
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
}
