/*
 * 
 * 文件名：ExportExcelUtil.java
 * 版权：Copyright HNNE All Rights Reserved.
 * 描述：
 * 修改人：肖崇高
 * 修改时间：2016年8月1日
 * 修改内容：
 */

package com.zkingsoft.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

 
/**
 * @author 肖崇高
 * @version 1.0, 2016年8月1日
 */
public class ExcelUtil{

	Logger log = Logger.getLogger(this.getClass());
	
	// 2007 版本以上 最大支持1048576行
	public final static String EXCEl_FILE_2007 = "2007";
	// 2003 版本 最大支持65536 行
	public final static String EXCEL_FILE_2003 = "2003";

	/**
	 * <p>
	 * 导出无头部标题行Excel <br>
	 * 时间格式默认：yyyy-MM-dd hh:mm:ss <br>
	 * </p>
	 * 
	 * @param title
	 *            表格标题
	 * @param dataset
	 *            数据集合
	 * @param out
	 *            输出流
	 * @param version
	 *            2003 或者 2007，不传时默认生成2003版本
	 * @param hasNum 是否有序号
	 */
	public ByteArrayInputStream exportExcel(String title, Collection<?> dataset,
			OutputStream out, String version, boolean isMerg,
			List<String[]> mergParm,boolean hasNum) {
		if (StringUtils.isBlank(version)
				|| EXCEL_FILE_2003.equals(version.trim())) {
			return exportExcel2003(title, null, dataset, "yyyy-MM-dd hh:mm:ss",
					isMerg, mergParm,hasNum);
		} else {
			return exportExcel2007(title, null, dataset, "yyyy-MM-dd hh:mm:ss",
					isMerg, mergParm,null);
		}
	}

	/**
	 * <p>
	 * 导出带有头部标题行的Excel <br>
	 * 时间格式默认：yyyy-MM-dd hh:mm:ss <br>
	 * </p>
	 * 
	 * @param title
	 *            表格标题
	 * @param headers
	 *            头部标题集合
	 * @param dataset
	 *            数据集合
	 * @param out
	 *            输出流
	 * @param version
	 *            2003 或者 2007，不传时默认生成2003版本
	 * @param hasNum 是否有序号
	 */
	public ByteArrayInputStream exportExcel(String title, String[] headers,
			Collection<?> dataset, String version,
			boolean isMerg, List<String[]> mergParm,boolean hasNum) {
		if (StringUtils.isBlank(version)
				|| EXCEL_FILE_2003.equals(version.trim())) {
			return exportExcel2003(title, headers, dataset,
					"yyyy-MM-dd hh:mm:ss", isMerg, mergParm,hasNum);
		} else {
			return exportExcel2007(title, headers, dataset,
					"yyyy-MM-dd hh:mm:ss", isMerg, mergParm,null);
		}
	}

	/**
	 * <p>
	 * 通用Excel导出方法,利用反射机制遍历对象的所有字段，将数据写入Excel文件中 <br>
	 * 此版本生成2007以上版本的文件 (文件后缀：xlsx)
	 * </p>
	 * 
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格头部标题集合
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合JavaBean风格的类的对象。此方法支持的
	 *            JavaBean属性的数据类型有基本数据类型及String,Date
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyyy-MM-dd hh:mm:ss"
	 * @param isMerg
	 *            是否需要合并单元格
	 * @param mergParm
	 *            合并单元格参数
	 * @param cols 
	 * 			     要生成excel列数
	 * @param headerRows
	 * 			    要生成的excel表头的行数
	 * @param list
	 * 			  表头列字符串参数（Map<String, Integer[]>）String表头列名，Integer[]
	 */
//	@SuppressWarnings({ "rawtypes" })
	public static ByteArrayInputStream exportExcel2007(String title, String[] headers,
			Collection<?> dataset, String pattern,
			boolean isMerg, List<String[]> mergParm,List<Map<String, Integer[]>> list) {
		//List<Map<String, Integer[]>> lsit = null;
		// 声明一个工作薄
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 生成一个表格
		XSSFSheet sheet = workbook.createSheet(title);
		sheet.setDefaultColumnWidth(20);
		// 生成一个样式
		XSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(new XSSFColor(java.awt.Color.GRAY));
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		// 生成一个字体
		XSSFFont font = workbook.createFont();
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setColor(new XSSFColor(java.awt.Color.BLACK));
		font.setFontHeightInPoints((short) 11);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		XSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(new XSSFColor(java.awt.Color.WHITE));
		style2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		XSSFFont font2 = workbook.createFont();
		font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		if (isMerg && (mergParm!=null&&mergParm.size() > 0)) {//如果是需要合并单元格
			// 产生表格标题行
			XSSFRow row = sheet.createRow(0);
			XSSFCell cellHeader;
			for (int i = 0; i < headers.length; i++) {
				cellHeader = row.createCell(i);
				cellHeader.setCellStyle(style);
				cellHeader.setCellValue(new XSSFRichTextString(headers[i]));
			}
		} else {//不需要合并单元格

		}
		// 设置表格默认列宽度为20个字节
		

		// 产生表格标题行
		XSSFRow row = sheet.createRow(0);
		XSSFCell cellHeader;
		for (int i = 0; i < headers.length; i++) {
			cellHeader = row.createCell(i);
			cellHeader.setCellStyle(style);
			cellHeader.setCellValue(new XSSFRichTextString(headers[i]));
		}

		// 遍历集合数据，产生数据行
		Iterator<?> it = dataset.iterator();
		int index = 0;
		Object t;
		Field[] fields;
		Field field;
		XSSFRichTextString richString;
		Pattern p = Pattern.compile("^//d+(//.//d+)?$");
		Matcher matcher;
		String fieldName;
		String getMethodName;
		XSSFCell cell;
		Class<?> tCls;
		Method getMethod;
		Object value;
		String textValue;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			t = it.next();
			// 利用反射，根据JavaBean属性的先后顺序，动态调用getXxx()方法得到属性值
			fields = clearUnChekedFields(t.getClass().getDeclaredFields());
			for (int i = 0; i < fields.length; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(style2);
				field = fields[i];
				fieldName = field.getName();
				getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				try {
					tCls = t.getClass();
					getMethod = tCls.getMethod(getMethodName, new Class[] {});
					value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					textValue = null;
					if (value instanceof Integer) {
						cell.setCellValue((Integer) value);
					} else if (value instanceof Float) {
						textValue = String.valueOf((Float) value);
						cell.setCellValue(textValue);
					} else if (value instanceof Double) {
						textValue = String.valueOf((Double) value);
						cell.setCellValue(textValue);
					} else if (value instanceof Long) {
						cell.setCellValue((Long) value);
					} else if (value instanceof Boolean) {
						textValue = "是";
						if (!(Boolean) value) {
							textValue = "否";
						}
					} else if (value instanceof Date) {
						textValue = sdf.format((Date) value);
					} else {
						// 其它数据类型都当作字符串简单处理
						if (value != null) {
							textValue = value.toString();
						}
					}
					if (textValue != null) {
						matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							richString = new XSSFRichTextString(textValue);
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}
		}
		ByteArrayInputStream bais = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			workbook.write(bos);
			bais = new ByteArrayInputStream(bos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bais;
	}

	/**
	 * <p>
	 * 通用Excel导出方法,利用反射机制遍历对象的所有字段，将数据写入Excel文件中 <br>
	 * 此方法生成2003版本的excel,文件名后缀：xls <br>
	 * </p>
	 * 
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格头部标题集合
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合JavaBean风格的类的对象。此方法支持的
	 *            JavaBean属性的数据类型有基本数据类型及String,Date
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyyy-MM-dd hh:mm:ss"
	 *  
	 * @param hasNum 第一列是否为序列
	 */
//	@SuppressWarnings({ "rawtypes" })
	public ByteArrayInputStream exportExcel2003(String title, String[] headers,
			Collection<?> dataset, String pattern,
			boolean isMerg, List<String[]> mergParm,boolean hasNum) {
		
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(20);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 11);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		HSSFCell cellHeader;
		int numIndex = 0;
		if(hasNum){//如果需要序号，添加序号头
			cellHeader = row.createCell(numIndex);
			cellHeader.setCellStyle(style);
			cellHeader.setCellValue("序号");
			numIndex = 1;
		}
		for (int i = numIndex; i < headers.length + numIndex; i++) {
			cellHeader = row.createCell(i);
			cellHeader.setCellStyle(style);
			cellHeader.setCellValue(new HSSFRichTextString(headers[i-numIndex]));
		}

		// 遍历集合数据，产生数据行
		Iterator<?> it = dataset.iterator();
		int index = 0;
		Object t;
		Field[] fields;
		Field field;
		HSSFRichTextString richString;
		Pattern p = Pattern.compile("^//d+(//.//d+)?$");
		Matcher matcher;
		String fieldName;
		String getMethodName;
		HSSFCell cell;
		Class<?> tCls;
		Method getMethod;
		Object value;
		String textValue;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		int rowNum = 1;
		while (it.hasNext()) {
			int cnumIndex = 0;
			index++;
			row = sheet.createRow(index);
			if(hasNum){//如果需要序号，添加序号头
				cell = row.createCell(cnumIndex);
				cell.setCellStyle(style2);
				cell.setCellValue(rowNum++);
				cnumIndex = 1;
			}
			t = it.next();
			// 利用反射，根据JavaBean属性的先后顺序，动态调用getXxx()方法得到属性值
			//fields = t.getClass().getDeclaredFields();
			fields = clearUnChekedFields(t.getClass().getDeclaredFields());
			for (int i = cnumIndex; i < fields.length+cnumIndex; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(style2);
				field = fields[i - cnumIndex];
				fieldName = field.getName();
				getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				try {
					tCls = t.getClass();
					getMethod = tCls.getMethod(getMethodName, new Class[] {});
					value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					textValue = null;
					if (value instanceof Integer) {
						textValue = String.valueOf((Integer) value);
						//cell.setCellValue((Integer) value);
					} else if (value instanceof Float) {
						textValue = String.valueOf((Float) value);
						//cell.setCellValue(textValue);
					} else if (value instanceof Double) {
						textValue = String.valueOf((Double) value);
						//cell.setCellValue(textValue);
					} else if (value instanceof Long) {
						textValue = String.valueOf((Long) value);
						//cell.setCellValue((Long) value);
					}
					if (value instanceof Boolean) {
						textValue = "是";
						if (!(Boolean) value) {
							textValue = "否";
						}
					} else if (value instanceof Date) {
						textValue = sdf.format((Date) value);
					} else {
						// 其它数据类型都当作字符串简单处理
						if (value != null) {
							textValue = value.toString();
						}
					}
					if (textValue != null) {
						matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							richString = new HSSFRichTextString(StringUtils.getNull(textValue));
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}
		}
		
		ByteArrayInputStream bais = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			workbook.write(bos);
			bais = new ByteArrayInputStream(bos.toByteArray());  
			bos.close();
		} catch (IOException e) {
			log.debug("excelutil异常",e);
		}
		
		return bais; 
	}
	
	/**
	 * 只加入包含ExcelAnnotion uncheked = true的变量
	 * @param fields
	 * @return
	 */
	private static Field[] clearUnChekedFields(Field[] fields){
		List<Field> list = new ArrayList<Field>();
		for(int i=0;i<fields.length;i++){
			//获取方法上注解
			Annotation annotation = fields[i].getAnnotation(ExcelAnnotation.class);
			if(annotation instanceof ExcelAnnotation){
				ExcelAnnotation ea = (ExcelAnnotation)annotation;
				if(ea.checked()){//该标签为true的时候，加入导出行列
					list.add(fields[i]);
				}else{
					//忽略改变量
					continue;
				}
			}
		}
		//list to array
		return (Field[])list.toArray(new Field[list.size()]);
	}
	/**
	 * 
	* @Title: exportExcel 
	* @Description: TODO 
	* @param @param title 标题
	* @param @param headers 表头信息
	* @param @param dataset 数据集合(需要使用者在调用之前自己将数据拼串好)
	* @param @param pattern 时间格式（如果有时间类型数据）
	* @param @return    设定文件 
	* @return ByteArrayInputStream    返回类型 
	* @throws
	 */
	public ByteArrayInputStream exportExcel(String title, String[] headers,
			List<List<?>> dataset, String pattern) {
		//List<Map<String, Integer[]>> lsit = null;
		// 声明一个工作薄
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 生成一个表格
		XSSFSheet sheet = workbook.createSheet(title);
		sheet.setDefaultColumnWidth(20);
		// 生成一个样式
		XSSFCellStyle style = workbook.createCellStyle();
		// 设置excel样式
		style.setFillForegroundColor(new XSSFColor(java.awt.Color.GRAY));
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		// 生成字体样式
		XSSFFont font = workbook.createFont();
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setColor(new XSSFColor(java.awt.Color.BLACK));
		font.setFontHeightInPoints((short) 11);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		XSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(new XSSFColor(java.awt.Color.WHITE));
		style2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		XSSFFont font2 = workbook.createFont();
		font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		// 产生表格标题行
		XSSFRow row = sheet.createRow(0);
		XSSFCell cellHeader;
		for (int i = 0; i < headers.length; i++) {
			cellHeader = row.createCell(i);
			cellHeader.setCellStyle(style);
			cellHeader.setCellValue(new XSSFRichTextString(headers[i]));
		}
		// 遍历集合数据，产生数据行
		Iterator<List<?>> it = dataset.iterator();
		int index = 0;
		XSSFRichTextString richString;
		Pattern p = Pattern.compile("^//d+(//.//d+)?$");
		Matcher matcher;
		XSSFCell cell;
		Object value;
		String textValue;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		while (it.hasNext()) {
			List<?> t = new ArrayList<>();
			index++;
			row = sheet.createRow(index);
			t = it.next();
			// 利用反射，根据JavaBean属性的先后顺序，动态调用getXxx()方法得到属性值
			for (int i = 0; i < t.size(); i++) {
				cell = row.createCell(i);
				cell.setCellStyle(style2);
				try {
					value = t.get(i);
					// 判断值的类型后进行强制类型转换
					textValue = null;
					if (value instanceof Integer) {
						cell.setCellValue((Integer) value);
					} else if (value instanceof Float) {
						textValue = String.valueOf((Float) value);
						cell.setCellValue(textValue);
					} else if (value instanceof Double) {
						textValue = String.valueOf((Double) value);
						cell.setCellValue(textValue);
					} else if (value instanceof Long) {
						cell.setCellValue((Long) value);
					} else if (value instanceof Boolean) {
						textValue = "是";
						if (!(Boolean) value) {
							textValue = "否";
						}
					} else if (value instanceof Date) {
						textValue = sdf.format((Date) value);
					} else {
						// 其它数据类型都当作字符串简单处理
						if (value != null) {
							textValue = value.toString();
						}
					}
					if (textValue != null) {
						matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							richString = new XSSFRichTextString(textValue);
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}
		}
		ByteArrayInputStream bais = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			workbook.write(bos);
			bais = new ByteArrayInputStream(bos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bais;
	}
	
	
	public static HSSFWorkbook generateExcel(String title, String[] headers,List<List<Object>> list) {  
        HSSFWorkbook book = new HSSFWorkbook();  
        try{  
             HSSFSheet sheet = book.createSheet("Sheet1");  
            //sheet.autoSizeColumn(1, true);//自适应列宽度  
            //样式设置  
            HSSFCellStyle style = book.createCellStyle();  
            style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
              style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
              style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
              style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
              style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
              style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
              style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
              // 生成一个字体  
              HSSFFont font = book.createFont();  
              font.setColor(HSSFColor.VIOLET.index);  
              font.setFontHeightInPoints((short) 12);  
              font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
              // 把字体应用到当前的样式  
              style.setFont(font);  
              
                
              HSSFCellStyle style2 = book.createCellStyle();  
                  //设置上下左右边框  
                  style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
                  style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
                  style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
                  style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
                  style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
                   //设置单元格背景色
                style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
          		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
              //填充表头标题  
              int colSize = list.get(0).size();  
              System.out.println("size:" + colSize);  
              //合并单元格供标题使用(表名)  
              sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colSize-1));  
              HSSFRow firstRow = sheet.createRow(0);//第几行（从0开始）  
              HSSFCell firstCell = firstRow.createCell(0);  
              firstCell.setCellValue(title);  
              firstCell.setCellStyle(style);  
                
              //填充表头header  
              HSSFRow row = sheet.createRow(1);  
              for(int i=0; i< headers.length; i++) {  
            	  sheet.autoSizeColumn((short)i,true);
                  HSSFCell cell = row.createCell(i);  
                  cell.setCellValue(headers[i]);  
                  cell.setCellStyle(style2);  
              }  
              
              //填充表格内容  
              for(int i=0; i<list.size(); i++) { 
                  HSSFRow row2 = sheet.createRow(i+2);//index：第几行  
                  List<?> rowList = list.get(i);  
                  for(int j=0; j<rowList.size(); j++) {
                	  String textValue = "";  
                	  sheet.autoSizeColumn((short)j,true);
                      Object value = rowList.get(j);  
                      HSSFCell cell = row2.createCell(j);//第几列：从0开始  
                      if (value instanceof Integer) {
  						cell.setCellValue((Integer) value);
  					} else if (value instanceof Float) {
  						textValue = String.valueOf((Float) value);
  						cell.setCellValue(textValue);
  					} else if (value instanceof Double) {
  						textValue = String.valueOf((Double) value);
  						cell.setCellValue(textValue);
  					} else if (value instanceof Long) {
  						cell.setCellValue((Long) value);
  					} else if (value instanceof Boolean) {
  						textValue = "是";
  						if (!(Boolean) value) {
  							textValue = "否";
  						}
  						cell.setCellValue(textValue);
  					}else {
  						// 其它数据类型都当作字符串简单处理
  						if (value != null) {
  							textValue = value.toString();
  						}
  						cell.setCellValue(textValue);
  					}
                    cell.setCellStyle(style2);  
                  }  
              }  
        } catch(Exception ex) {  
            ex.printStackTrace();  
        }  
        return book;  
    } 
	
	
	/**一个excel含有多个表格
	 * @author guchunxia
	 * @param title excel标题
	 * @param headers 多个标题的二位数组
	 * @param list  多个表格的内容集合
	 * @return
	 */
	public static HSSFWorkbook generateExcelMore(String title, String[][] headers,List<List<List<Object>>> list) {  
        HSSFWorkbook book = new HSSFWorkbook();  
        try{  
             HSSFSheet sheet = book.createSheet("Sheet1");  
            //sheet.autoSizeColumn(1, true);//自适应列宽度  
            //样式设置  
            HSSFCellStyle style = book.createCellStyle();  
            style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
              style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
              style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
              style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
              style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
              style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
              style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
              // 生成一个字体  
              HSSFFont font = book.createFont();  
              font.setColor(HSSFColor.VIOLET.index);  
              font.setFontHeightInPoints((short) 12);  
              font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
              // 把字体应用到当前的样式  
              style.setFont(font);  
              HSSFCellStyle style2 = book.createCellStyle();  
              //设置上下左右边框  
              style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
              style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
              style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
              style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
              style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
                   //设置单元格背景色
	                style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
	          		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	                  //填充表头标题  ,找出长度最大的集合的长度，进行合并
	          		List<List<Object>> max=list.get(0);
	          		for (int j =1; j <list.size(); j++) {
						 max=(max.size()>=list.get(j).size())?max:list.get(j);
						}
	          		int colSize = max.size(); 
              System.out.println("size:" + colSize);  
              //合并单元格供标题使用(表名)  
              sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colSize-1));  
              HSSFRow firstRow = sheet.createRow(0);//第几行（从0开始）  
              HSSFCell firstCell = firstRow.createCell(0);  
              firstCell.setCellValue(title);  
              firstCell.setCellStyle(style);
              
              //countRow= 表名所占的一行1+标题行
             int countRow=0;
             for (int k = 0; k < headers.length; k++) {
			  String [] header=headers[k];
			  countRow+=1;
			  //不是首航则要空一行
			  if(k!=0){
				  countRow++;
			  }
              //填充表头header  
              HSSFRow row = sheet.createRow(countRow);  
              for(int i=0; i< header.length; i++) {  
            	  sheet.autoSizeColumn((short)i,true);
                  HSSFCell cell = row.createCell(i);  
                  cell.setCellValue(header[i]);  
                  cell.setCellStyle(style2);  
              }  
              
              //填充表格内容  
              List<List<Object>> contentList=list.get(k);
              
              for(int i=0; i<contentList.size(); i++) { 
            	  countRow++;
                  HSSFRow row2 = sheet.createRow(countRow);//index：第几行  
                  
                  List<?> rowList = contentList.get(i);  
                  for(int j=0; j<rowList.size(); j++) {
                	  String textValue = "";  
                	  sheet.autoSizeColumn((short)j,true);
                      Object value = rowList.get(j);  
                      HSSFCell cell = row2.createCell(j);//第几列：从0开始  
                      if (value instanceof Integer) {
  						cell.setCellValue((Integer) value);
  					} else if (value instanceof Float) {
  						textValue = String.valueOf((Float) value);
  						cell.setCellValue(textValue);
  					} else if (value instanceof Double) {
  						textValue = String.valueOf((Double) value);
  						cell.setCellValue(textValue);
  					} else if (value instanceof Long) {
  						cell.setCellValue((Long) value);
  					} else if (value instanceof Boolean) {
  						textValue = "是";
  						if (!(Boolean) value) {
  							textValue = "否";
  						}
  						cell.setCellValue(textValue);
  					}else {
  						// 其它数据类型都当作字符串简单处理
  						if (value != null) {
  							textValue = value.toString();
  						}
  						cell.setCellValue(textValue);
  					}
                    cell.setCellStyle(style2);  
                  }  
              }  
             }
        } catch(Exception ex) {  
            ex.printStackTrace();  
        }  
        return book;  
    } 
	public static class ViewExcel extends AbstractExcelView {  
	    @Override  
	    protected void buildExcelDocument(Map<String, Object> map,  
	            HSSFWorkbook book, HttpServletRequest request, HttpServletResponse response)  
	            throws Exception {  
	    } 
	    
	    public static void buildExcel(Map<String, Object> map,  
	            HSSFWorkbook book, HttpServletRequest request, HttpServletResponse response,String fileName)  
	            throws Exception {  
	            response.setContentType("application/vnd.ms-excel");         
	            response.setHeader("Content-disposition", "attachment;filename="+new String(fileName.getBytes(), "ISO-8859-1"));         
	            OutputStream ouputStream = response.getOutputStream();         
	            book.write(ouputStream);         
	            ouputStream.flush();         
	            ouputStream.close();     
	  
	    } 

	} 
}
