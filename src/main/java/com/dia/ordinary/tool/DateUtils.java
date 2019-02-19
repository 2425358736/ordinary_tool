package com.dia.ordinary.tool;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 开发公司：青岛海豚数据技术有限公司
 * 版权：青岛海豚数据技术有限公司
 * <p>
 * DateUtils
 *
 * @author 刘志强
 * @created Create Time: 2019/1/28
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, String pattern) {
        String formatDate = null;
        if (pattern != null) {
            formatDate = DateFormatUtils.format(date, pattern);
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null){
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis()-date.getTime();
        return t/(24*60*60*1000);
    }

    /**
     * 获取过去的小时
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = System.currentTimeMillis()-date.getTime();
        return t/(60*60*1000);
    }

    /**
     * 获取过去的分钟
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis()-date.getTime();
        return t/(60*1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis){
        long day = timeMillis/(24*60*60*1000);
        long hour = (timeMillis/(60*60*1000)-day*24);
        long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
        long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
        long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
        return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 处理日期增加/减少（天、月）
     * @param date 原始日期
     * @param type month:增加或减少月  date:增加或减少天
     * @param number 天数、月数
     * @return
     */
    public static Date operationDate(Date date,String type,int number){
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        if(type.indexOf("month") > -1){
            cd.add(Calendar.MONTH, number);
        }else if(type.indexOf("date") > -1){
            cd.add(Calendar.DATE,number);
        }
        return cd.getTime();
    }

    public static Map<String,Object> getDateDifference(Date startDate, Date endDate){
        Map<String,Object> map = new HashMap<>();
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        int days = startCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int day = days - startCalendar.get(Calendar.DAY_OF_MONTH) + 1;
        if (day == days) {
            day = 0;
            month = month + 1;
        }
        if (endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) != endCalendar.get(Calendar.DAY_OF_MONTH)) {
            day = day + endCalendar.get(Calendar.DAY_OF_MONTH);
            month = month - 1;
        }
        map.put("year",year);
        map.put("month",month + year * 12);
        map.put("day",day);
        return map;
    }
    /**
     * 获取两个日期相差的年、月、日
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    public static Map<String,Object> getTwoDateDifference(Date startDate, Date endDate){
        Map<String,Object> map = new HashMap<>();
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        endCalendar.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        int day = endCalendar.get(Calendar.DAY_OF_MONTH) - startCalendar.get(Calendar.DAY_OF_MONTH);
        if (month < 0) {
            year = year - 1;
            endCalendar.add(Calendar.YEAR, -1);
            month = (11 - startCalendar.get(Calendar.MONTH)) + (endCalendar.get(Calendar.MONTH) + 1);
        }
        if (day < 0) {
            month = month - 1;
            Date end = endCalendar.getTime();
            endCalendar.add(Calendar.MONTH, -1);
            endCalendar.add(Calendar.DATE, -day);
            day = ((int)(end.getTime()/1000)-(int)(endCalendar.getTime().getTime()/1000))/3600/24;
        }

        map.put("year",year);
        map.put("month",month);
        map.put("day",day);
        return map;
    }



    /**
     * 获取当前月的第一天
     * @return
     */
    public static String getFirstDayOfMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        return first;
    }

    /**
     * 获取当下月的第一一天
     * @return
     */
    public static String getFirstDayOfMonthEnd() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.MONTH, 1);
        String first = format.format(c.getTime());
        return first;
    }

    /**
     * 获取指定月的第一天
     * @return
     */
    public static Date getFirstDayOfMonthDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return c.getTime();
    }

    /**
     * 获取指定月的天数
     */
    public static int getMonthLastDay(String dateStr)
    {
        Calendar date = Calendar.getInstance();
        date.setTime(parseDate(dateStr));
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, date.get(Calendar.YEAR));
        a.set(Calendar.MONTH, date.get(Calendar.MONTH));
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取指定日期末时分秒
     */
    public static Date getEndDate(Date date) {
        String str = formatDate(date, null);
        date = parseDate(str);
        Calendar EndCalendar = Calendar.getInstance();
        EndCalendar.setTime(date);
        EndCalendar.add(Calendar.DATE, 1);
        EndCalendar.add(Calendar.SECOND, -1);
        return EndCalendar.getTime();
    }

    /**
     * 获取缴费期限
     * @param startDate 账单开始日期
     * @param prePaymentNum 提前付款天数
     * @param prePaymentMon 提前付款月数
     * @param prePaymentStatus 提前付款状态 0:自然天 1:指定日
     *
     *
     * @return
     */
    public static Date getCapitalDate(Date startDate,Integer prePaymentNum,Integer prePaymentMon,Integer prePaymentStatus){
        Date capitalMon = operationDate(startDate,"month",-prePaymentMon);
        if(prePaymentStatus == 0){//自然日
            Date capitalDate = operationDate(capitalMon,"date",-prePaymentNum);
            return capitalDate;
        }else {//指定日
            int maxDay = getMonthLastDay(formatDate(capitalMon,"yyyy-MM-dd"));
            int payMentNum = prePaymentNum >= maxDay ? maxDay : prePaymentNum;
            String capitalDateStr = formatDate(capitalMon,"yyyy-MM")+"-"+payMentNum;
            return parseDate(capitalDateStr);
        }
    }
}
