package cn.com.ddhj.util.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * 类: PageBorderEvent <br>
 * 描述: 添加页面边框 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月26日 下午3:56:23
 */
public class PageBorderEvent extends PdfPageEventHelper {
	private PdfWriter writer;
	private Document document;

	public PageBorderEvent(PdfWriter writer, Document document) {
		this.writer = writer;
		this.document = document;
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		try {
			float x = document.top() + 90;
			float y = document.bottom() - 80;
			PdfContentByte content = writer.getDirectContent();
			content.setLineWidth(0.5f);
			// 上横线
			content.moveTo(document.left() - 60, x);
			content.lineTo(document.right() + 60, x);
			// 左竖线
			content.moveTo(document.left() - 60, x);
			content.lineTo(document.left() - 60, y);
			// 下横线
			content.moveTo(document.left() - 60, y);
			content.lineTo(document.right() + 60, y);
			// 右竖线
			content.moveTo(document.right() + 60, x);
			content.lineTo(document.right() + 60, y);
			content.stroke();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PdfWriter getWriter() {
		return writer;
	}

	public void setWriter(PdfWriter writer) {
		this.writer = writer;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}
