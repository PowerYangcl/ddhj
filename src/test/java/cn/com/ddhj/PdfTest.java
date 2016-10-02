package cn.com.ddhj;

import java.io.IOException;

import org.junit.Test;

import com.itextpdf.text.DocumentException;

import cn.com.ddhj.util.PdfUtil;

public class PdfTest {

	@Test
	public void createPDF() {
		try {
			PdfUtil.instance().createPDF();
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
	}
}