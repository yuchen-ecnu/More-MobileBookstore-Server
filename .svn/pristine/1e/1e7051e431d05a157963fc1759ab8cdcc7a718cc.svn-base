package com.common.util;

import java.awt.Color;
import java.io.File;
import java.text.DecimalFormat;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;

/**
 * 类描述：PDF报表基类的工具类，提供与pdf报表相关的部件
 */
public class PdfUtil {

	/**
	 * 功能描述：获取中文简体宋体字体
	 * 
	 * @param fontSize
	 *            字体大小
	 * @return
	 * @throws Exception
	 */
	public static Font getChineseFont_STSong(float fontSize) throws Exception {
		Font font = null;
		try {
			BaseFont bfChinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.EMBEDDED);
			font = new Font(bfChinese);
			font.setSize(fontSize);
		} catch (Exception e) {
			throw new Exception("PDF font STSong-Light create error:"
					+ e.getMessage());
		}
		return font;
	}

	/**
	 * 功能描述：获取中文TrueType字体
	 * 
	 * @param ttfFontFileName
	 *            TrueType字体名称
	 * @param fontSize字体大小
	 * @return
	 * @throws Exception
	 */
	public static Font getChineseFont_TrueType(String ttfFontFileName,
			float fontSize) throws Exception {
		Font font = null;
		try {
			// 中文必须使用IDENTITY_H这个参数
			BaseFont bfChinese = BaseFont.createFont(ttfFontFileName,
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			font = new Font(bfChinese);
			font.setSize(fontSize);
		} catch (Exception e) {
			throw new Exception("PDF TrueType Font " + ttfFontFileName
					+ " create error:" + e.getMessage());
		}
		return font;
	}

	/**
	 * 功能描述：获取普通字体
	 * 
	 * @param normalFontName
	 *            普通字体，可以使用一下参数 COURIER, COURIER_BOLD, COURIER_OBLIQUE,
	 *            COURIER_BOLDOBLIQUE, HELVETICA, HELVETICA_BOLD,
	 *            HELVETICA_OBLIQUE, HELVETICA_BOLDOBLIQUE等
	 * @param fontSize
	 *            字体大小
	 * @return Font
	 */
	public static Font getNormalFont(String normalFontName, float fontSize) {
		Font font = FontFactory.getFont("normalFontName");
		font.setSize(fontSize);
		return font;
	}

	/**
	 * 功能描述：获取数字字体，通常为TimesRoman字体
	 * 
	 * @param fontSize
	 *            字体大小
	 * @return
	 */
	public static Font getNumericFont(float fontSize) {
		Font font = FontFactory.getFont("TIMES_ROMAN");
		font.setSize(fontSize);
		return font;
	}

	/**
	 * 功能描述：设置字体大小
	 * 
	 * @param font
	 *            被设置的字体
	 * @param fontSize
	 *            需要设置的大小
	 * @return Font
	 * @throws Exception
	 */
	public static Font setFontSize(Font font, float fontSize) throws Exception {
		font.setSize(fontSize);
		return font;
	}

	/**
	 * 功能描述：设置字体的样式
	 * 
	 * @param font
	 *            被设置的字体
	 * @param fontSytle
	 *            需要设置的样式，可以使用Font.[NORMAL, BOLD, UNDERLINE, STRIKETHRU,
	 *            BOLDITALIC]
	 * @return Font
	 * @throws Exception
	 */
	public static Font setFontStyle(Font font, String fontSytle)
			throws Exception {
		font.setStyle(fontSytle);
		return font;
	}

	/**
	 * 功能描述：设置字体的颜色
	 * 
	 * @param font
	 *            被设置的字体
	 * @param fontColor
	 *            需要设置的颜色
	 * @return Font
	 * @throws Exception
	 */
	public static Font setFontColor(Font font, Color fontColor)
			throws Exception {
		font.setColor(fontColor);
		return font;
	}

	/**
	 * 功能描述：设置字体的颜色
	 * 
	 * @param font
	 *            被设置的字体
	 * @param red
	 *            红色数字
	 * @param green
	 *            绿色数字
	 * @param blue
	 *            蓝色数字
	 * @return Font
	 * @throws Exception
	 */
	public static Font setFontColor(Font font, int red, int green, int blue)
			throws Exception {
		font.setColor(red, green, blue);
		return font;
	}

	/**
	 * 功能描述：创建一个单元格，同时设置对应的文字，字体，对齐方式，是否换行
	 * 
	 * @param content
	 *            单元格中的文字,不可缺少
	 * @param font
	 *            文字的字体，不可缺少
	 * @param align
	 *            对齐方式[left, right, center],如果为null则默认为居中
	 * @param wrap
	 *            单元格是否换行，[wrap, nowrap]，如果为null则默认为不换行
	 * @return Cell
	 * @throws Exception
	 */
	public static Cell createCell(String content, Font font, String align,
			String wrap) throws Exception {
		if (content == null){
			content = "";
		}
		Cell c = new Cell(new Paragraph(content,font));
		c.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直对齐方式，默认居中
		if (align != null && align.equalsIgnoreCase("left")) { // 设置水平对齐方式，默认居中
			c.setHorizontalAlignment(Cell.ALIGN_LEFT);
		} else if (align != null && align.equalsIgnoreCase("right")) {
			c.setHorizontalAlignment(Cell.ALIGN_RIGHT);
		} else
			c.setHorizontalAlignment(Cell.ALIGN_CENTER);
//		if (wrap != null && wrap.equalsIgnoreCase("nowrap")) // 设置是否换行，默认不换行
//			c.setNoWrap(false);
		c.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
		return c;
	}

	public static Cell createCell(String content, Font font, int rowspan,
			int colspan, String align, String wrap) throws Exception {
		if (content == null) {
			content = "";
		}
		Cell c = new Cell(new Chunk(content, font));
		c.setVerticalAlignment(Cell.ALIGN_CENTER); // 设置垂直对齐方式，默认居中
		if (align != null && align.equalsIgnoreCase("left")) { // 设置水平对齐方式，默认居中
			c.setHorizontalAlignment(Cell.ALIGN_LEFT);
		} else if (align != null && align.equalsIgnoreCase("right")) {
			c.setHorizontalAlignment(Cell.ALIGN_RIGHT);
		} else
			c.setHorizontalAlignment(Cell.ALIGN_CENTER);
//		if (wrap != null && wrap.equalsIgnoreCase("nowrap")) // 设置是否换行，默认不换行
//			c.setNoWrap(false);

		if (rowspan > 1) {
			c.setRowspan(rowspan);
		}
		if (colspan > 1) {
			c.setColspan(colspan);
		}
		return c;
	}

	/**
	 * 功能描述：创建一个单元格，同时设置对应的文字、字体、对齐方式、是否换行
	 * 
	 * @param content
	 *            单元格的文字，不可缺少
	 * @param font
	 *            单元格的字体，不可缺少
	 * @param border
	 *            单元格的边框，0-15，0表示无
	 * @param borderWidth
	 *            单元格的边框宽度
	 * @param borderColor
	 *            单元格的边框颜色、可以为null
	 * @param align
	 *            单元格的对齐方式[left, right, center]，如果为null，默认居中
	 * @param wrap
	 *            单元格是否换行[wrap, nowrap],如果为null则默认为不换行
	 * @return Cell
	 * @throws Exception
	 */
	public static Cell createCell(String content, Font font, int border,
			float borderWidth, Color borderColor, String align, String wrap)
			throws Exception {
		if (content == null) {
			content = "";
		}
		Cell c = new Cell(new Chunk(content, font));
		c.setVerticalAlignment(Cell.ALIGN_MIDDLE);
		if (align != null && align.equalsIgnoreCase("left")) {
			c.setHorizontalAlignment(Cell.ALIGN_LEFT);
		} else if (align != null && align.equalsIgnoreCase("right")) {
			c.setHorizontalAlignment(Cell.ALIGN_RIGHT);
		} else
			c.setHorizontalAlignment(Cell.ALIGN_CENTER);
		if (border >= 0 && border <= 15)
			c.setBorder(border);
		else
			throw new Exception("Cell`s border argument must between 0 to 15");
		c.setBorderWidth(borderWidth);
		if (borderColor != null) {
			c.setBorderColor(borderColor);
		}
//		if (wrap != null && wrap.equalsIgnoreCase("nowrap"))
//			c.setNoWrap(false);
		return c;
	}

	public static Cell createCell(Element e, String align, String wrap)
			throws Exception {
		Cell c = new Cell(e);
		c.setVerticalAlignment(Cell.ALIGN_MIDDLE);
		if (align != null && align.equalsIgnoreCase("left")) {
			c.setHorizontalAlignment(Cell.ALIGN_LEFT);
		} else if (align != null && align.equalsIgnoreCase("right")) {
			c.setHorizontalAlignment(Cell.ALIGN_RIGHT);
		} else
			c.setHorizontalAlignment(Cell.ALIGN_CENTER);
//		if (wrap != null && wrap.equalsIgnoreCase("nowrap"))
//			c.setNoWrap(false);
		return c;
	}

	public static Cell createCell(Element e, int border, float borderWidth,
			Color borderColor, String align, String wrap) throws Exception {
		Cell c = new Cell(e);
		c.setVerticalAlignment(Cell.ALIGN_MIDDLE);
		if (align != null && align.equalsIgnoreCase("left")) {
			c.setHorizontalAlignment(Cell.ALIGN_LEFT);
		} else if (align != null && align.equalsIgnoreCase("right")) {
			c.setHorizontalAlignment(Cell.ALIGN_RIGHT);
		} else
			c.setHorizontalAlignment(Cell.ALIGN_CENTER);

		if (border >= 0 && border <= 15)
			c.setBorder(border);
		else
			throw new Exception("Cell`s border argument must between 0 to 15");
		c.setBorderWidth(borderWidth);
		if (borderColor != null)
			c.setBorderColor(borderColor);
//		if (wrap != null && wrap.equalsIgnoreCase("nowrap"))
//			c.setNoWrap(false);
		return c;
	}

	/**
	 * 功能描述：删除错误的文件
	 * 
	 * @param filePath
	 *            错误文件的路径
	 */
	public static void deleteErrorFile(String filePath) {
		File f = new File(filePath);
		if (f.exists()) {
			f.delete();
		}
	}

	/**
	 * 功能描述：生成指定数目的空格，用于修改报表格式
	 * 
	 * @param num
	 *            空格的数目
	 * @return 将空格以字符串的方式返回
	 */
	public static String getSpace(int num) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num * 2; i++) {
			sb.append(" ");
		}

		return sb.toString();
	}

	/**
	 * 转换发报日期格式 如：输入2012-03-11 返回 20120311
	 */
	public static String transDate(String date) {
		if (date.indexOf("-", 0) != -1) {
			String tmps = "";
			for (int i = 0; i < date.length(); i++) {
				if (!date.substring(i, i + 1).equals("-"))
					tmps = tmps + date.substring(i, i + 1);
			}
			date = tmps;
		}
		return date;
	}

	/**
	 * 金额值格式化
	 * @param s
	 * @return 
	 * @throws Exception
	 */
	public static String checkFolatToStr(String s) throws Exception {
		DecimalFormat decimalformat = new DecimalFormat(
				"#,###,###,###,###,##0.00");
		DecimalFormat decimalformat1 = new DecimalFormat("###############0.00");
		String s1 = null;
		if (s == null)
			s1 = "";
		else if (s.trim().equals("0.00") || s.trim().equals("0.0")
				|| s.trim().equals("0.") || s.trim().equals("0")) {
			s1 = "";
		} else {
			DecimalFormat decimalformat2 = new DecimalFormat(
					"0.###################E000000");
			s = s.replace('e', 'E');
			Number number;
			if (s.indexOf("E") > 0) {
				if (s.indexOf("E+") > 0) {
					int i = s.indexOf("E+");
					s = s.substring(0, i + 1) + s.substring(i + 2, s.length());
				}
				number = decimalformat2.parse(s);
			} else {
				number = decimalformat1.parse(s);
			}
			s1 = decimalformat.format(number);
		}
		return s1;
	}

}
