package cn.itcast.elec.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	/**将日期类型转换成String类型，精确到年月日时分秒*/
	public static String dateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}

}
