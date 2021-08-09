package vn.seven.stc.umgr.utils;


import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StringUtils {

    public static String convertDate(String date, String fromFormat, String toFormat) {
        String result = null;
        try {

            // Declare date
            DateFormat dateFormat = new SimpleDateFormat(fromFormat);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(toFormat);

            // Convert date time
            Date dateDf = dateFormat.parse(date);
            result = simpleDateFormat.format(dateDf);

        } catch (ParseException e) {
            result = null;
            e.printStackTrace();
        }

        return result;
    }

    public static String convertDateWithTimeZone(String date, String fromFormat, TimeZone fromTimeZone, String toFormat, TimeZone toTimeZone) {
        String result = null;
        try {

            // Declare date
            DateFormat dateFormat = new SimpleDateFormat(fromFormat);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(toFormat);

            // Set time zone
            dateFormat.setTimeZone(fromTimeZone);
            simpleDateFormat.setTimeZone(toTimeZone);

            // Convert date time
            Date dateDf = dateFormat.parse(date);
            result = simpleDateFormat.format(dateDf);

        } catch (ParseException e) {
            result = null;
            e.printStackTrace();
        }

        return result;
    }

    public static Long convertDatetimeToTimestamp(String dateTime, String fromFormat) {
        Long result = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(fromFormat);
            Date date = dateFormat.parse(dateTime);
            Timestamp timestamp = new Timestamp(date.getTime());
            result = timestamp.getTime();
        } catch (ParseException e) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    public static String convertDateToString(int interval) {
        Calendar cal = Calendar.getInstance();
        if (interval > 0) {
            cal.add(Calendar.MINUTE, -interval);
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
    }

    public static long convertDateToLong(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        // Convert date time
        Date dateDf = simpleDateFormat.parse(date);
        return dateDf.getTime();
    }

    public static boolean checkExpireTime(long created, long expireTime) {
        if((System.currentTimeMillis() - expireTime*1000) < created) return true;
        return false;
    }

    public static String setToString(Set<Long> list){
        String tmp = list.toString();
        tmp= tmp.replaceAll("\\[","(").replace("]",")");
        return tmp;
    }

    public static String setStringToString(Set<String> list){
        String tmp = list.toString();
        tmp= tmp.replaceAll("\\[","").replace("]","");
        return tmp;
    }

    public static String setLongToString(Set<Long> list){
        String tmp = list.toString();
        tmp= tmp.replaceAll("\\[","(").replace("]",")");
        return tmp;
    }

    public static String setBigIntegerToString(Set<BigInteger> list){
        String tmp = list.toString();
        tmp= tmp.replaceAll("\\[","(").replace("]",")");
        return tmp;
    }

    public static String listBigIntegerToString(List<BigInteger> list){
        String tmp = list.toString();
        tmp= tmp.replaceAll("\\[","(").replace("]",")");
        return tmp;
    }

    public static Set<Long> convertListBigIntegerToSetLong(List<BigInteger> list){
        Set<Long> ids = new HashSet<>();
        if(list.size() < 1) return ids;
        for (BigInteger i : list){
            if(i!=null){
                ids.add(i.longValue());
            }
        }

        return ids;
    }

    public static String setIntegerToString(Set<Integer> list){
        String tmp = list.toString();
        tmp= tmp.replaceAll("\\[","(").replace("]",")");
        return tmp;
    }
}
