package com.lib.utils;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class HtmlToWord {
	public synchronized static void HtmlToPdf(String str, String path) throws Exception {
		// System.out.println(str);
		// System.out.println(path);
		Document document = new Document();
		PdfWriter pdfwriter = PdfWriter.getInstance(document, new FileOutputStream(path));
		// System.out.println("---->" + pdfwriter);
		pdfwriter.setViewerPreferences(PdfWriter.HideToolbar);
		document.open();
		InputStream in = new ByteArrayInputStream(str.getBytes());
		XMLWorkerHelper.getInstance().parseXHtml(pdfwriter, document, in, Charset.forName("UTF-8"));
		document.close();
	}

	public static InputStream stringToInputStream(String str) {
		try {
			InputStream is = new ByteArrayInputStream(str.getBytes());

			int byteRead;
			while ((byteRead = is.read()) != -1) {
				System.out.print((char) byteRead);
			}
			is.close();
			return is;
		} catch (Exception e) {

			return null;
		}

	}
}
