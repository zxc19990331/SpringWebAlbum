package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    public static String getCurrentDate(){
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(new Date());
    }

    public static String dateToString(){
        return null;
    }
}
