package com.bsoft.ckb.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类 除了私信使用getMessageTime外，其他都使用getDateTime方法
 * 
 * 传过来的long都是UTC时间
 * 
 * @author
 */
public class DateUtil {

	public static String tmepReturnStr = "不祥";
	public static DateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static DateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日");
	public static DateFormat format2 = new SimpleDateFormat("yyyy年MM月");
	public static DateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat format4 = new SimpleDateFormat("MM-dd");
	public static DateFormat format5= new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static Calendar cal = Calendar.getInstance();

	public static String getDateTime(long d) {
		String result = "";
		if (d > 0) {
			result = getString(new Date(d));
		}
		return result;
	}
	

	public static int getAge(long bityhday) {
		Date d1 = new Date(bityhday + cal.get(Calendar.ZONE_OFFSET));
		Date d2 = new Date();
		return d2.getYear() - d1.getYear();
	}

	public static long getTime(String s) {
		try {
			Date d1 = format3.parse(s);
			return d1.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 无法选择未来时间，≤now
	public static boolean check(String d) {
		try {
			Date d1 = format3.parse(d);
			Date d2 = format3.parse(format3.format(new Date()));
			return d2.getTime() >= d1.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}


	public static String getDateTime1(long d) {
		String result = "";
		if (d > 0) {
			result = getString(new Date(d));
		}
		return result;
	}

	// 传过来的不是UTC时间
	public static String getNewHongDongDay(long d) {
		return format1.format(new Date(d));
	}

	// 返回当前的UTC时间
	public static long getNowTime() {
		return System.currentTimeMillis()
				- cal.get(Calendar.ZONE_OFFSET);
	}

	public static long getUtcTimeByStr(String str) {
		try {
			return format3.parse(str).getTime()
					- cal.get(Calendar.ZONE_OFFSET);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String getBirthDateTime(long d) {
		return format3.format(new Date(d
				+ cal.get(Calendar.ZONE_OFFSET)));
	}

	public static String getDateTime(DateFormat format, long d) {
		return format.format(new Date(d));
	}

	public static String getDateTime(String format, long d) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(d));
	}

	public static Date getDateTime(String formate, String d) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formate);
			return sdf.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date getDateTime(DateFormat formate, String d) {
		try {
			return formate.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String dateFormate(Date date, String formate) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(formate);
			return sdf.format(date);
		} else {
			return null;
		}

	}

	// public static Date getSodaoDateByString(String d) throws ParseException {
	// format.setTimeZone(TimeZone.getTimeZone("UTC"));
	// return format.parse(d);
	// }

	/**
	 * 0秒-60秒内显示“刚刚” 1分钟-2分钟内显示“1分钟前” 1小时-2小时内显示“1小时前” 1天-2天内显示“1天前”
	 * 7天-14天显示“1周前” 30天-60天显示1月前 之后已月类推计算 ）
	 */
	public static String getString(Date date) {
		long curTime = System.currentTimeMillis();

		long diffSecond = 0L;
		long diffDay = 0L;
		diffSecond = (curTime - date.getTime()) / 1000L;

		if (diffSecond >= 86400) {// 两个相差
			Calendar curDate = new GregorianCalendar();
			curDate.setTime(new Date(curTime));
			curDate.set(Calendar.HOUR_OF_DAY, 23);
			curDate.set(Calendar.MINUTE, 59);
			curDate.set(Calendar.SECOND, 59);
			curDate.set(Calendar.MILLISECOND, 999);
			diffDay = (curDate.getTimeInMillis() - date.getTime()) / 86400000L;
			if (diffDay <= 30) {
				return diffDay + "天前";
			} else {
				if (date.getYear() == new Date().getYear()) {
					return format4.format(new Date(date.getTime()
							+ cal.get(Calendar.ZONE_OFFSET)));
				} else {
					return getBirthDateTime(date.getTime());
				}
			}
		} else {
			if (diffSecond < 0) {
				return "刚刚";
			} else if (diffSecond < 60) {
				return "刚刚";
			} else if (diffSecond < 3600) {
				return diffSecond / 60 + "分钟前";
			} else if (diffSecond < 86400) {
				return diffSecond / 3600 + "小时前";
			} else if (diffSecond < 86400) {
				return diffSecond / 3600 + "小时前";
			}
		}
		return "...";
	}

	public static long getDayTime(String d) {
		try {
			return format1.parse(d).getTime()
					- cal.get(Calendar.ZONE_OFFSET);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static long getMonthTime(String d) {
		try {
			return format2.parse(d).getTime()
					- cal.get(Calendar.ZONE_OFFSET);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void main(String args[]) {
		System.out.println(dateFormate(new Date(1448504072862l), "yyyy-MM-dd HH:mm:ss"));
		System.out.println(dateFormate(new Date(1448504072866l), "yyyy-MM-dd HH:mm:ss"));
	}

}
