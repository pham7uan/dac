package vn.seven.stc.core.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.seven.stc.core.errors.BadRequestAlertException;

import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.*;
import java.time.YearMonth;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class Common {
    private static final Logger logger = LoggerFactory.getLogger(Common.class);
    private Common() {
    }

    public static Boolean containSpecialCharacter(String text){
        Pattern p = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        return m.find();
    }

    public static String getCurrentDateTime(){
        return  new SimpleDateFormat("ddMMyyyyHHmmss").format(Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh")).getTime());
    }

    public static void createDirectory(String path){
        File theDir = new File(path);
        if (!theDir.exists()) {
            try{
                theDir.mkdir();
            }
            catch(SecurityException se){
                throw new BadRequestAlertException("Create new directory fail", "common", "createDirectoryFail");
            }
        }
    }

    public static Integer getDay(Long time){
        Date date=new Date(time);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static String convertDateTime(Long time, String pattern){
        if(time != null){
            Date date=new Date(time);
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
            cal.setTime(date);
            String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
            if (cal.get(Calendar.DAY_OF_MONTH)  < 10 ) {
                day = 0 + day;
            }
            pattern = pattern.replace("dd",day);
            String month = Integer.toString(cal.get(Calendar.MONTH) +1);
            if (cal.get(Calendar.MONTH) +1 < 10 ) {
                month = 0 + month;
            }
            pattern = pattern.replace("MM",month);
            pattern = pattern.replace("yyyy",Integer.toString(cal.get(Calendar.YEAR)));
            pattern = pattern.replace("HH",Integer.toString(cal.get(Calendar.HOUR_OF_DAY)));
            pattern = pattern.replace("mm",Integer.toString(cal.get(Calendar.MINUTE)));
            pattern = pattern.replace("ss",Integer.toString(cal.get(Calendar.SECOND)));
            return pattern;
        }
        return null;
    }

    public static  String convertDateTime(Long time){
        if(time !=null){
            Date d = new Date(time);
            DateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            f.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
            return f.format(d);
        }
        return null;
    }

    public static  String convertDateTime(Object time){
        if(time !=null){
            try {
                String stringToConvert = String.valueOf(time);
                Long convertedLong = Long.parseLong(stringToConvert);
                Date d = new Date(convertedLong);
                DateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                f.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
                return f.format(d);
            } catch (Exception e){
                return null;
            }
        }
        return null;
    }

    public static Long convertDateTime(String dateTime,String pattern, int addition){
        SimpleDateFormat f = new SimpleDateFormat(pattern);
        f.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        Long time = null;
        try {
            Date d = f.parse(dateTime);
            f.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
            if(addition !=0){
                d = addDays(d, 1);
            }
            time = d.getTime();
        } catch (ParseException e) {
           return  1612026000000L;
        }
        return time;
    }

    public static Long getCurrentDate(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }

    public static Long getCurrentDate(Long time){
        Date date = new Date(time);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }

    public static Integer getMonth(Long time){
        Date date = new Date(time);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        cal.setTime(date);
        return cal.get(Calendar.MONTH) +1;
    }

    public static Integer getCurrentYear(Long time){
        Date date = new Date(time);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static String getCurrentDateString(){
        Long today = getCurrentDate();
        return convertDateTime(today,"yyyy-MM-dd");
    }

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static Long addMonth(Long time, int month)
    {
        Date date = new Date(time);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        return cal.getTime().getTime();
    }

    public static Long nextMonth(Long time, int month)
    {
        Date date = new Date(time);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        Long newTime = cal.getTime().getTime();
        Long firstDayOfMonth = getFirstDayOfMonth(newTime);
        return firstDayOfMonth;
    }

    public static String removeLastCharacter(String str) {
        if (str != null && str.length() > 0 ) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    public static Long getTimestamp(String date){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(date);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            return timestamp.getTime();
        } catch(Exception e) { //this generic but you can control another types of exception
            // look the origin of excption
            return null;
        }
    }

    public static Long getLastDayOfMonth(Long time) {
        try {
            Date date = new Date(time);
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
            calendar.setTime(date);
            String month = Integer.toString(calendar.get(Calendar.MONTH) +1);
            String year =  Integer.toString(calendar.get(Calendar.YEAR));
            return getLastDayOfMonth(year,month);
        } catch (Exception e){
            return null;
        }

    }

    public static Long getLastDayOfMonth(String year,String month) {
        try {
            String date = year+"-"+month+"-01";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
            cal.setTime(dateFormat.parse(date));
            int res = cal.getActualMaximum(Calendar.DATE);
            String value = year +"-"+month+"-"+res;
            return getTimestamp(value);
        } catch (Exception e){
            return null;
        }

    }

    public static Long getFirstDayOfMonth(String year,String month) {
        try {
            String date = year+"-"+month+"-01";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
            cal.setTime(dateFormat.parse(date));
            int res = cal.getActualMinimum(Calendar.DATE);
            String value = year +"-"+month+"-"+res;
            return getTimestamp(value);
        } catch (Exception e){
            return null;
        }

    }

    public static Long getFirstDayOfMonth(Long time){
        Date date = new Date(time);
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTimeInMillis();
    }

    public static Integer getNumberDayOfMonth(Long time){
        Date date = new Date(time);
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        c.setTime(date);
        int month = c.get(Calendar.MONTH) +1;
        int year  = c.get(Calendar.YEAR);
        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();
    }

    public static String getFileName(String filePath){
        if(filePath == null){
            return null;
        }
        int index = filePath.lastIndexOf("/");
        String fileName = filePath.substring(index + 1);
        return fileName;
    }

    public static void generateQRCodeImage(String text, int width, int height, String filePath, String fileName)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        File tmp = new File(filePath, fileName+ ".png");
        tmp.createNewFile();
        MatrixToImageWriter.writeToFile(bitMatrix, "PNG", tmp);
    }

    public static String convertSetLongToString(Set<Long> ids){
        if(ids == null || ids.size() == 0){
            return null;
        }
        StringBuilder result = new StringBuilder("(");
        int index =0;
        for (Long id:ids){
            if(index>0){
                result.append(",");
            }
            result.append(id);
            index++;
        }
        result.append(")");
        return result.toString();
    }

    public static String convertSetBigIntegerToString(Set<BigInteger> ids){
        if(ids == null || ids.size() == 0){
            return null;
        }
        StringBuilder result = new StringBuilder("(");
        int index =0;
        for (BigInteger id:ids){
            if(index>0){
                result.append(",");
            }
            result.append(id);
            index++;
        }
        result.append(")");
        return result.toString();
    }

    public static Object[] convertMapToObjects(Map<String,String> map){
        String[] keys = new String[map.size()];
        Object[] objects = new Object[map.size()];
        int index = 0;
        for (Map.Entry<String, String> mapEntry : map.entrySet()) {
            keys[index] = mapEntry.getKey();
            objects[index] = mapEntry.getValue();
            index++;
        }
        return objects;
    }

    public static String renameDuplicateFile(String fileName){
        int pos = fileName.lastIndexOf(".");
        if (pos > 0) {
            String name = fileName.substring(0, pos);
            String extension = fileName.substring(pos+1);
            fileName = name + "_" + getCurrentDateTime() + "." + extension;
        } else {
            fileName += getCurrentDateTime();
        }
        return  fileName;
    }

    public static String generateBase64Image(byte[] image)
    {
        return Base64.encodeBase64String(image);
    }

    public static String replaceAt(String origin, String replace, int index){
        if(index + replace.length() <= origin.length()){
            origin = origin.substring(0,index) + replace + origin.substring(index + replace.length());
        } else {
            origin = origin.substring(0,index) + replace;
        }
        return origin;
    }

    public static void autoSizeRow(Sheet currSht, String cellValue, int mergedCellWidth, int rowNum){
        java.awt.Font currFont = new java.awt.Font("Arial", 0, 10);
        AttributedString attrStr = new AttributedString(cellValue);
        attrStr.addAttribute(TextAttribute.FONT, currFont);

// Use LineBreakMeasurer to count number of lines needed for the text
        FontRenderContext frc = new FontRenderContext(null, true, true);
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
        int nextPos = 0;
        int lineCnt = 0;
        while (measurer.getPosition() < cellValue.length())
        {
            nextPos = measurer.nextOffset(mergedCellWidth); // mergedCellWidth is the max width of each line
            lineCnt++;
            measurer.setPosition(nextPos);
        }

        Row currRow = currSht.getRow(rowNum);
        currRow.setHeight((short)(currRow.getHeight() * lineCnt));
    }

    public static String getCellStringValue(Cell cell){
        String result = null;
        if(cell !=null){
            if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                result = cell.getStringCellValue().trim();
            } else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                result = Long.toString((long)cell.getNumericCellValue());
            }
        }
        return result;
    }

    private static Integer replaceOrTypeOne(StringBuffer stringBuffer){
        Integer start = 0;
        Integer offset = 0;
        while(offset >= 0){
            offset = stringBuffer.indexOf("'",start);
            if(offset > 0){
                String sub = stringBuffer.substring(start,offset);
                sub = sub.replace(","," OR ");
                stringBuffer.replace(start,offset,sub);
                offset = stringBuffer.indexOf("=(",start);
                start = stringBuffer.indexOf(")",offset);
            }
        }
        offset = start;
        while(offset >= 0){
            offset = stringBuffer.indexOf(",",start);
            if(offset > 0){
                String sub = stringBuffer.substring(start,offset+1);
                sub = sub.replace(","," OR ");
                stringBuffer.replace(start,offset+1,sub);
                start = offset+1;
            }
        }
        return offset;
    }

    public static String convertRsqlToSql(String rSql){
        if (rSql==null || rSql.isEmpty()){
            return rSql;
        }
        StringBuilder origin = new StringBuilder(rSql);
        rSql = rSql.replace("%22", "\'");
        rSql = rSql.replace("\"", "\'");
        StringBuffer stringBuffer = new StringBuffer(rSql);
        Integer start = 0;
        Integer offset = 0;
        try{
            while (offset >= 0){
                offset = stringBuffer.indexOf("=(",start);
                if(offset > 0){
                    String sub = stringBuffer.substring(start,offset);
                    StringBuffer sub2 = new StringBuffer(sub);
                    replaceOrTypeOne(sub2);
                    stringBuffer.replace(start,offset,sub2.toString());
                    offset = stringBuffer.indexOf("=(",start);
                    start = stringBuffer.indexOf(")",offset);
                }
            }
            offset = start;
            StringBuffer sub3 = new StringBuffer(stringBuffer.substring(offset,stringBuffer.length()-1));
            replaceOrTypeOne(sub3);
            stringBuffer.replace(start,stringBuffer.length()-1,sub3.toString());

            rSql = stringBuffer.toString();
            rSql = rSql.replace(";"," AND ");
            rSql = rSql.replace("=='null'"," IS NULL ");
            rSql = rSql.replace("!='null'"," IS NOT NULL ");
            rSql = rSql.replace("==null"," IS NULL ");
            rSql = rSql.replace("!=null"," IS NOT NULL ");
            rSql = rSql.replace("==\'*"," LIKE \'%");
            rSql = rSql.replace("==\'"," LIKE \'");
            rSql = rSql.replace("!=\'*"," NOT LIKE \'%");
            rSql = rSql.replace("*\'","%\'");
            rSql = rSql.replace("!=%22*"," NOT LIKE \'%");
            rSql = rSql.replace("%20", " ");

            rSql = rSql.replace("==", "=");
            rSql = rSql.replace("=in=", " IN ");
            rSql = rSql.replace("=out=", " NOT IN ");
            rSql = rSql.replace("',", "' OR ");
        } catch (Exception e){
            logger.info("Fail to convert rSQL: {}",origin.toString());
        }

        return rSql;
    }

    public static Boolean compareSetLong(Set<Long> set1, Set<Long> set2){
        Boolean result = false;
        if(set1 == null || set2 !=null){
            return false;
        }
        if(set1 != null || set2 ==null){
            return false;
        }

        if(set1.size()!=set2.size()){
            return false;
        }
        return set1.containsAll(set2);
    }

    public static Boolean compareLongValue(Long l1, Long l2){
        if( (l1 !=null && !l1.equals(l2)) || (l1 == null && l2 !=null)){
            return false;
        }
        return true;
    }

    public static Boolean compareBigValue(BigDecimal l1, BigDecimal l2){
        if( (l1 !=null && !l1.equals(l2)) || (l1 == null && l2 !=null)){
            return false;
        }
        return true;
    }

    public static<K,V> Map<K,V> clone(Map<K,V> original) {
        return original.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue));
    }

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static String randomString(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public static Set<Long> setBigToLong(Set<BigInteger> big){
        Set<Long> result = new HashSet<>();
        if(big !=null){
            for(BigInteger b:big){
                try {
                    result.add(b.longValue());
                } catch (Exception e){
                    Integer v = b.intValue();
                    result.add(v.longValue());
                }

            }
        }
        return result;
    }

    public static Set<Long> setBigToLong(List<BigInteger> big){
        Set<Long> result = new HashSet<>();
        if(big !=null){
            for(BigInteger b:big){
                result.add(b.longValue());
            }
        }
        return result;
    }

    public static Boolean bigDecimalGreaterThanZero(BigDecimal decimal){
        if(decimal == null) {
            return false;
        }
        if(decimal.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        return false;
    }

    public static String formatNumber(BigDecimal quantity){
        NumberFormat format = NumberFormat.getInstance();
        String money = format.format(quantity);
        return money;
    }

    public static void main(String[] args) {
        System.out.println(getRandomNumberInRange(1,5));
        String a="12/12/1992";
        System.out.println(a.contains("/"));
        System.out.println(a.contains("\\/"));
    }

    public static String moneyCommas(BigDecimal val){
        String replace = val.toString().replace("/\\B(?=(\\d{3})+(?!\\d))/g", ",");
        return replace;
    }

    public static Set<Long> clone(Set<Long> ids){
        Set<Long> clone = new HashSet<>();
        for(Long id: ids){
            clone.add(id);
        }
        return clone;
    }

}

