package com.shool.ctp.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
/**
 * 字符串转换为日期的转换类
 * @author Administrator
 *
 */
public class StringToDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String text) {
		// TODO Auto-generated method stub
		Date date = null;
		if (StringUtils.hasLength(text)) {
			
			SimpleDateFormat sdf = null;
			int lenth = text.length();// 获取字符串的长度
			switch (lenth) {
			case 10:
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				break;
			case 19:
				sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				break;

			default:
				String msg = text + ",参数的长度不合法！";
				throw new IllegalArgumentException(msg);
			}

			try {
				date = sdf.parse(text);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return date;
	}

}
