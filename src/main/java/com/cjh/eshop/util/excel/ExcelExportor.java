package com.cjh.eshop.util.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelExportor {

	private DataAdapter adapter;

	public ExcelExportor(DataAdapter adapter) {
		this.adapter = adapter;
	}

	public void export(OutputStream fOut) throws IOException {

		HSSFWorkbook workbook = new HSSFWorkbook(); // 工作簿
		HSSFCellStyle setBorder = workbook.createCellStyle();
		// 一、设置背景色：
		setBorder.setFillForegroundColor((short) 13);// 设置背景色
		setBorder.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// 二、设置边框:
		setBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		setBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		setBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		setBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 三、设置居中:
		setBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		// 四、设置字体:
		HSSFFont font = workbook.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 16);// 设置字体大小
		HSSFFont font2 = workbook.createFont();
		font2.setFontName("仿宋_GB2312");
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		font2.setFontHeightInPoints((short) 12);
		setBorder.setFont(font);// 选择需要用到的字体格式

		// 产生工作表对象
		HSSFSheet sheet = workbook.createSheet();
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);
		// 五、设置列宽:
		sheet.setColumnWidth(0, 3766); // 第一个参数代表列id(从0开始),第2个参数代表宽度值

		HSSFCellStyle headCellStyle = getHeadStyle(workbook); // 标题单元格样式

		// 输出标题
		HSSFRow rowTitle = sheet.createRow((int) 0); // 创建标题行
		
		rowTitle.setHeightInPoints(20); // 设置行高为60px;
		for (int i = 0, length = adapter.getColumnCount(); i < length; i++) {
			HSSFCell cell = rowTitle.createCell((int) i); // 创建一列
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellStyle(headCellStyle);// 设置单元格的样式
			// firstrow.createCell(j).setCellValue(new
			// HSSFRichTextString(names[j]));
			cell.setCellValue(adapter.getTitleString(i));
		}

		HSSFCellStyle contentCellStyle = getContentStyle(workbook);
		
		for (int k = 0; k < adapter.getRowCount(); k++) {
			HSSFRow row = sheet.createRow((int) k + 1);// 创建一行
			row.setHeightInPoints(15);
			for (int i = 0; i < adapter.getColumnCount(); i++) {
				HSSFCell cell = row.createCell((int) i);// 创建一列

				cell.setCellStyle(contentCellStyle);

				Object obj = adapter.getValueAt(k, i);
				setCellTypeAndValue(cell, obj);
			}
		}

		workbook.write(fOut);
	}

	public void setCellTypeAndValue(HSSFCell cell, Object value) {
		

		if (value instanceof Float) {
			Float fValue = (Float) value;
			// TODO 保留2位小数
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(fValue);
		} else if (value instanceof Integer) {
			Integer iValue = (Integer) value;
			
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(iValue);
		} else if (value instanceof Boolean) {
			boolean bValue = (Boolean) value;
			String text = bValue ? "是" : "否";
			
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(text);
		} else if (value instanceof Date) {
			Date date = (Date) value;
			SimpleDateFormat sdf = new SimpleDateFormat(adapter.getTimePattern());
			String text = sdf.format(date);
			cell.setCellValue(text);
			
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(text);
		} /*else if (value instanceof byte[]) {
			// 有图片时，设置行高为60px;
			row.setHeightInPoints(60);
			// 设置图片所在列宽度为80px,注意这里单位的一个换算
			sheet.setColumnWidth(i, (short) (35.7 * 80));
			// sheet.autoSizeColumn(i);
			byte[] bsValue = (byte[]) value;
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255,
					(short) 6, index, (short) 6, index);
			anchor.setAnchorType(2);

		} */else {
			// 其它数据类型都当作字符串简单处理
			String text = value.toString();
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(text);
		}
	}

	private HSSFCellStyle getHeadStyle(HSSFWorkbook workbook) {
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index); // 标题栏的背景为天蓝色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.WHITE.index); // 标题栏的字体
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);

		return style;
	}

	private HSSFCellStyle getContentStyle(HSSFWorkbook workbook) {
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index); // 非标题的背景色为白色
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setColor(HSSFColor.BLACK.index);
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);

		return style2;
	}
}
