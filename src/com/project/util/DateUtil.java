package com.project.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	public static Date convertToDate(String strdate) {
		//자바의 월은 MM, 분은 mm
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh24:mi");
		java.util.Date d = null; //Util
		Date d2 = null;			 //SQL
		try {
			d = sdf.parse(strdate);
			d2 = new Date(d.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d2;
	}
}
