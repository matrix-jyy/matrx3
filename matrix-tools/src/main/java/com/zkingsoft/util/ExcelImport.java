	package com.zkingsoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * excel导入（根据需求调用03/07版本方法导入）
* @ClassName: ExcelImport 
* @Description: TODO 
* @author 肖崇高  xiaochonggao@zkingsoft.com 
* @date 2016年8月2日 上午10:39:04 
*
 */
public class ExcelImport {
	/**
	 * 对外提供读取excel 的方法
	 */
	public static List<List<Object>> readExcel(File file, String fileName, Integer rowNum, Integer cellNum)
			throws IOException {
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return read2003Excel(file, rowNum, cellNum);
		} else if ("xlsx".equals(extension)) {
			return read2007Excel(file, rowNum, cellNum);
		} else {
			throw new IOException("不支持的文件类型");
		}
	}

	/**
	 * 
	* @Title: read2003Excel 
	* @Description: TODO 
	* @param @param file 待读取的文件
	* @param @param rowNum 行数
	* @param @param cellNum 列数
	* @param @return
	* @param @throws IOException    设定文件 
	* @return List<List<Object>>    返回类型  Object:单元格对象  List<Object>:行对象  List<List<Object>>:整个excel对象
	* @throws
	 */
	private static List<List<Object>> read2003Excel(File file, Integer rowNum, Integer cellNum) throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = hwb.getSheetAt(0);
		Object value = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		Integer rowsNum = 0;// 读取的行数
		Integer cellsNum = 0;// 读取的列数
		if (rowNum != null) {
			rowsNum = rowNum;
		} else {
			rowsNum = sheet.getPhysicalNumberOfRows();
		}
		for (int i = sheet.getFirstRowNum(); i <= rowsNum; i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			if (row.getFirstCellNum() < 0) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			if (cellNum != null) {
				cellsNum = cellNum;
			} else {
				cellsNum = (int) row.getLastCellNum();
			}
			for (int j = 0; j <= cellsNum; j++) {// row.getFirstCellNum()
				cell = row.getCell(j);
				if (cell == null) {
					value = null;
				} else {
					DecimalFormat df = new DecimalFormat("0");// 格式化 number
																// String
					// 字符
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
					DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
					switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case XSSFCell.CELL_TYPE_NUMERIC:
						if ("@".equals(cell.getCellStyle().getDataFormatString())) {
							value = df.format(cell.getNumericCellValue());
						} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
							value = nf.format(cell.getNumericCellValue());
						} else {
							value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
						}
						break;
					case XSSFCell.CELL_TYPE_BOOLEAN:
						value = cell.getBooleanCellValue();
						break;
					case XSSFCell.CELL_TYPE_BLANK:
						value = "";
						break;
					default:
						value = cell.toString();
					}
				}
				linked.add(value);
			}
			list.add(linked);
		}
		return list;
	}

	
	/**
	 * 
	* @Title: read2007Excel 
	* @Description: TODO 
	* @param @param file 待读取的文件
	* @param @param rowNum 行数
	* @param @param cellNum 列数
	* @param @return
	* @param @throws IOException    设定文件 
	* @return List<List<Object>>    返回类型  Object:单元格对象  List<Object>:行对象  List<List<Object>>:整个excel对象
	* @throws
	 */
	private static List<List<Object>> read2007Excel(File file, Integer rowNum, Integer cellNum) throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		Integer rowsNum = 0;// 读取的行数
		Integer cellsNum = 0;// 读取的列数
		if (rowNum != null) {
			rowsNum = rowNum;
		} else {
			rowsNum = sheet.getPhysicalNumberOfRows();
		}
		for (int i = sheet.getFirstRowNum(); i <= rowsNum; i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			if (row.getFirstCellNum() < 0) {
				continue;
			}
			if (cellNum != null) {
				cellsNum = cellNum;
			} else {
				cellsNum = (int) row.getLastCellNum();
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = 0; j <= cellsNum; j++) {
				cell = row.getCell(j);
				if (cell == null) {
					value = null;
				} else {
					DecimalFormat df = new DecimalFormat("0");// 格式化 number
																// String
					// 字符
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
					DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
					switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case XSSFCell.CELL_TYPE_NUMERIC:
						if ("@".equals(cell.getCellStyle().getDataFormatString())) {
							value = df.format(cell.getNumericCellValue());
						} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
							value = nf.format(cell.getNumericCellValue());
						} else {
							value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
						}
						break;
					case XSSFCell.CELL_TYPE_BOOLEAN:
						value = cell.getBooleanCellValue();
						break;
					case XSSFCell.CELL_TYPE_BLANK:
						value = "";
						break;
					default:
						value = cell.toString();
					}
				}
				linked.add(value);
			}
			list.add(linked);
		}
		return list;
	}
/**
 * 
* @Title: getCellValue 
* @Description: TODO 获取某个单元格数据
* @param @param cell
* @param @return
* @param @throws Exception    设定文件 
* @return Object    返回类型 
* @throws
 */
	public static Object getCellValue(HSSFCell cell) throws Exception {
		Object value = null;
		if (cell != null) {
			DecimalFormat df = new DecimalFormat("0");// 格式化
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
			DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
			switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;
			case XSSFCell.CELL_TYPE_NUMERIC:
				if ("@".equals(cell.getCellStyle().getDataFormatString())) {
					value = df.format(cell.getNumericCellValue());
				} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
					value = nf.format(cell.getNumericCellValue());
				} else {
					value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
				}
				break;
			case XSSFCell.CELL_TYPE_BOOLEAN:
				value = cell.getBooleanCellValue();
				break;
			case XSSFCell.CELL_TYPE_BLANK:
				value = "";
				break;
			default:
				value = cell.toString();
			}
		}
		return value;
	}

	public static void main(String[] args) throws IOException {
	}
}
