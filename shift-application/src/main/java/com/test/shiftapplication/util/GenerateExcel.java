package com.test.shiftapplication.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.json.JsonParseException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.shiftapplication.model.MonthlyShiftModel;
import com.test.shiftapplication.model.Shift;

public class GenerateExcel {

	public Workbook convertToJSON(List<MonthlyShiftModel> individualShiftModel)
			throws JSONException, JsonParseException, JsonMappingException, IOException {
		List<Map<Integer, Shift>> list = new ArrayList<>();
		for (int i = 0; i < individualShiftModel.size(); i++) {
			Map<Integer, Shift> map = new TreeMap<>();
			JSONObject jsonObject = new JSONObject(individualShiftModel.get(i).getIndividualShiftModel());
			ObjectMapper mapper = new ObjectMapper();
			Iterator<String> keys = jsonObject.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				Shift student = mapper.readValue(jsonObject.getJSONObject(key).toString(), Shift.class);
				map.put(Integer.parseInt(key), student);
			}
			list.add(map);
		}
		return writeToExcel(list, individualShiftModel);
	}

	private static Workbook writeToExcel(List<Map<Integer, Shift>> list, List<MonthlyShiftModel> shiftModel)
			throws IOException {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("Shift Plan");

		HSSFPalette palette = ((HSSFWorkbook) workbook).getCustomPalette();

		// weeklyoff color index
		palette.setColorAtIndex(HSSFColor.RED.index, (byte) 255, // RGB red (0-255)
				(byte) 71, // RGB green
				(byte) 26 // RGB blue
		);
		// afternoon color index
		palette.setColorAtIndex(HSSFColor.DARK_YELLOW.index, (byte) 255, (byte) 235, (byte) 204);
		// night color index
		palette.setColorAtIndex(HSSFColor.AQUA.index, (byte) 179, (byte) 204, (byte) 255);
		// morning color index
		palette.setColorAtIndex(HSSFColor.BRIGHT_GREEN.index, (byte) 72, (byte) 201, (byte) 29);
		// name headers
		palette.setColorAtIndex(HSSFColor.DARK_GREEN.index, (byte) 95, (byte) 155, (byte) 75);

		HSSFCellStyle lastUpdatedHeader = (HSSFCellStyle) workbook.createCellStyle();
		lastUpdatedHeader.setRotation((short) 90);
		lastUpdatedHeader.setFillForegroundColor(HSSFColor.DARK_GREEN.index);
		lastUpdatedHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		Font font = workbook.createFont();
		font.setColor(HSSFColor.WHITE.index);
		lastUpdatedHeader.setFont(font);
		sheet.setColumnWidth(0, 5000);

		HSSFCellStyle nameHeader = (HSSFCellStyle) workbook.createCellStyle();
		nameHeader.setFillForegroundColor(HSSFColor.DARK_GREEN.index);
		nameHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		font.setColor(HSSFColor.WHITE.index);
		nameHeader.setFont(font);
		nameHeader.setBorderBottom(CellStyle.BORDER_HAIR);
		nameHeader.setBorderLeft(CellStyle.BORDER_HAIR);
		nameHeader.setBorderRight(CellStyle.BORDER_HAIR);
		nameHeader.setBorderTop(CellStyle.BORDER_HAIR);
		sheet.setColumnWidth(0, 5000);

		HSSFCellStyle dayHeader = (HSSFCellStyle) workbook.createCellStyle();
		dayHeader.setRotation((short) 90);
		dayHeader.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		dayHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		Font dayHeaderFont = workbook.createFont();
		dayHeaderFont.setColor(HSSFColor.BLACK.index);
		dayHeader.setFont(dayHeaderFont);
		dayHeader.setBorderBottom(CellStyle.BORDER_HAIR);
		dayHeader.setBorderLeft(CellStyle.BORDER_HAIR);
		dayHeader.setBorderRight(CellStyle.BORDER_HAIR);
		dayHeader.setBorderTop(CellStyle.BORDER_HAIR);

		HSSFCellStyle dayHeaderWeekends = (HSSFCellStyle) workbook.createCellStyle();
		dayHeaderWeekends.setRotation((short) 90);
		dayHeaderWeekends.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		dayHeaderWeekends.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		Font dayHeaderWeekendsFont = workbook.createFont();
		dayHeaderWeekendsFont.setColor(HSSFColor.RED.index);
		dayHeaderWeekends.setFont(dayHeaderWeekendsFont);
		dayHeaderWeekends.setBorderBottom(CellStyle.BORDER_HAIR);
		dayHeaderWeekends.setBorderLeft(CellStyle.BORDER_HAIR);
		dayHeaderWeekends.setBorderRight(CellStyle.BORDER_HAIR);
		dayHeaderWeekends.setBorderTop(CellStyle.BORDER_HAIR);

		HSSFCellStyle userName = (HSSFCellStyle) workbook.createCellStyle();
		userName.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		userName.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		userName.setBorderBottom(CellStyle.BORDER_HAIR);
		userName.setBorderLeft(CellStyle.BORDER_HAIR);
		userName.setBorderRight(CellStyle.BORDER_HAIR);
		userName.setBorderTop(CellStyle.BORDER_HAIR);

		HSSFCellStyle weeklyOff = (HSSFCellStyle) workbook.createCellStyle();
		weeklyOff.setFillForegroundColor(HSSFColor.RED.index);
		weeklyOff.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		weeklyOff.setBorderBottom(CellStyle.BORDER_HAIR);
		weeklyOff.setBorderLeft(CellStyle.BORDER_HAIR);
		weeklyOff.setBorderRight(CellStyle.BORDER_HAIR);
		weeklyOff.setBorderTop(CellStyle.BORDER_HAIR);

		HSSFCellStyle morning = (HSSFCellStyle) workbook.createCellStyle();
		morning.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);
		morning.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		morning.setBorderBottom(CellStyle.BORDER_HAIR);
		morning.setBorderLeft(CellStyle.BORDER_HAIR);
		morning.setBorderRight(CellStyle.BORDER_HAIR);
		morning.setBorderTop(CellStyle.BORDER_HAIR);

		HSSFCellStyle night = (HSSFCellStyle) workbook.createCellStyle();
		night.setFillForegroundColor(HSSFColor.AQUA.index);
		night.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		night.setBorderBottom(CellStyle.BORDER_HAIR);
		night.setBorderLeft(CellStyle.BORDER_HAIR);
		night.setBorderRight(CellStyle.BORDER_HAIR);
		night.setBorderTop(CellStyle.BORDER_HAIR);

		HSSFCellStyle afternoon = (HSSFCellStyle) workbook.createCellStyle();
		afternoon.setFillForegroundColor(HSSFColor.DARK_YELLOW.index);
		afternoon.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		afternoon.setBorderBottom(CellStyle.BORDER_HAIR);
		afternoon.setBorderLeft(CellStyle.BORDER_HAIR);
		afternoon.setBorderRight(CellStyle.BORDER_HAIR);
		afternoon.setBorderTop(CellStyle.BORDER_HAIR);

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");

		int rowIndex = 4;
		Row header = sheet.createRow(rowIndex++);
		Cell cell0 = header.createCell(0);
		cell0.setCellValue("Last Updated at : " + dateFormat.format(date));
		cell0.setCellStyle(lastUpdatedHeader);
		for (Map.Entry<Integer, Shift> map : list.get(0).entrySet()) {
			sheet.autoSizeColumn(map.getKey());
			Cell cell = header.createCell(map.getKey());
			cell.setCellValue(map.getValue().getDate() + "-" + shiftModel.get(0).getMonth().substring(0, 3) + "-"
					+ shiftModel.get(0).getYear().substring(2, 4));
			if (map.getValue().getDayOfTheWeek().contains("Sat") || map.getValue().getDayOfTheWeek().contains("Sun")) {
				header.getCell(map.getKey()).setCellStyle(dayHeaderWeekends);
			} else {
				header.getCell(map.getKey()).setCellStyle(dayHeader);
			}
		}
		Row name = sheet.createRow(rowIndex++);
		Cell nameCell = name.createCell(0);
		nameCell.setCellValue("Name");
		nameCell.setCellStyle(nameHeader);
		for (Map.Entry<Integer, Shift> map : list.get(0).entrySet()) {
			sheet.autoSizeColumn(map.getKey());
			Cell cell = name.createCell(map.getKey());
			cell.setCellValue(map.getValue().getDayOfTheWeek());
			cell.setCellStyle(dayHeader);
		}

		for (int i = 0; i < list.size(); i++) {
			Row row = sheet.createRow(rowIndex++);
			for (Map.Entry<Integer, Shift> map : list.get(i).entrySet()) {
				Cell userNameCell = row.createCell(0);
				userNameCell.setCellValue(shiftModel.get(i).getName());
				userNameCell.setCellStyle(userName);
				Cell cell = row.createCell(map.getKey());
				String shiftType = map.getValue().getShift();
				cell.setCellValue(shiftType);
				if (shiftType.contains("M")) {
					row.getCell(map.getKey()).setCellStyle(morning);
				} else if (shiftType.contains("N")) {
					row.getCell(map.getKey()).setCellStyle(night);
				} else if (shiftType.contains("A")) {
					row.getCell(map.getKey()).setCellStyle(afternoon);
				} else if (shiftType.contains("W")) {
					row.getCell(map.getKey()).setCellStyle(weeklyOff);
				}
			}
		}
		String fileName = "/Users/504001/Desktop/miscellaneous/Shift.xls";
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		fos.close();
		System.out.println(fileName + " written successfully");
		return workbook;
	}
}
