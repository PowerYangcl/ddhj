package cn.com.ddhj.util.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class HeadFootInfoPdfPageEvent extends PdfPageEventHelper {

	private PdfWriter writer;

	private Document document;

	private BaseFont font;

	private String path;

	public HeadFootInfoPdfPageEvent(PdfWriter writer, Document document, BaseFont font, String path) {
		this.writer = writer;
		this.document = document;
		this.font = font;
		this.path = path;
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		try {
			PdfContentByte content = writer.getDirectContent();
			content.saveState();
			content.beginText();
			// 设置中文
			content.setFontAndSize(font, 10);
			// ================ 设置页眉 start ===============
			// 左侧图片
			float x = document.top() + 40;
			Image image = Image.getInstance(path + "/resource/report/icon.jpg");
			image.setAbsolutePosition(document.left(), x);
			// image.scaleAbsolute(120, 40);
			image.scaleToFit(120, 100);
			content.addImage(image);
			// 页头信息右侧
			content.showTextAligned(PdfContentByte.ALIGN_RIGHT, "亿科云版权所有 侵权必究", document.right(), x, 0);
			// =============== 设置页眉 end =====================
			// ================== 设置页脚 start ==============
			float y = document.bottom() - 40;
			PdfTemplate template = PdfTemplate.createTemplate(writer, 100, y);
			template.setColorFill(BaseColor.RED);
			template.setFontAndSize(font, 10);
			template.newlineShowText(document.getPageNumber() + "");
			content.addTemplate(template, document.left() + 100, y);
			content.showTextAligned(PdfContentByte.ALIGN_LEFT, "  北京亿科云科技有限公司", document.left() + 100, y, 0);
			// ================== 设置页脚 end ==============
			content.endText();
			content.restoreState();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public BaseFont getFont() {
		return font;
	}

	public void setFont(BaseFont font) {
		this.font = font;
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
