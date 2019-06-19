package com.test.shiftapplication.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFHyperlink;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableRow;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;

public class GeneratePPTForWSR {
	public XMLSlideShow generatePPt(Map<String, List<String>> map) throws IOException {
		FileInputStream is = new FileInputStream("/Users/504001/Desktop/miscellaneous/weekly_status_1.pptx");
		// creating a new empty slide show
		XMLSlideShow ppt = new XMLSlideShow(is);
		is.close();
		List<XSLFSlide> slide = ppt.getSlides();
		for (XSLFShape shape : slide.get(3)) {
			shape.getAnchor();
			if (shape instanceof XSLFTable) {
				XSLFTable t = (XSLFTable) shape;
				List<XSLFTableRow> r = t.getRows();
				int numberOfCells = t.getNumberOfColumns();
				for (int i = 0; i < r.size() - 1; i++) {
					for (int j = 0; j < numberOfCells; j++) {
						String userName = r.get(i + 1).getCells().get(0).getText();
						r.get(i + 1).getCells().get(1).clearText();
						if (map.containsKey(userName)) {
							List<String> listOfJiras = map.get(userName);
							for (String jira : listOfJiras) {
								XSLFTextParagraph p1 = r.get(i + 1).getCells().get(1).addNewTextParagraph();
								p1.setBullet(true);
								p1.setIndent(15.);
								XSLFTextRun r1 = p1.addNewTextRun();
								r1.setText(jira);
								r1.setFontSize(8.0);
								r1.createHyperlink();
								r1.setFontFamily("Century Gothic");
								// creating the hyperlink
								XSLFHyperlink link = r1.createHyperlink();
								// setting the link address
								link.setAddress("http://www.google.com/");
							}
						}
					}
				}
			}
		}
		// creating an FileOutputStream object
		// File file = new File("/Users/504001/Desktop/miscellaneous/WSR.pptx");
		// FileOutputStream out = new FileOutputStream(file);
		// saving the changes to a file
		// ppt.write(out);
		System.out.println("Presentation created successfully");
		return ppt;
		// out.close();
	}
}
