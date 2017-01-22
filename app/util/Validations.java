package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Validations {

	public static boolean isDateFormat(String dates){
		Date date=null;
		try {
			SimpleDateFormat simpleDF=new SimpleDateFormat("yyyy-MM-dd");
			date=simpleDF.parse(dates);
			if(!dates.equals(simpleDF.format(date))){
				date=null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return date!=null;
	}

}
