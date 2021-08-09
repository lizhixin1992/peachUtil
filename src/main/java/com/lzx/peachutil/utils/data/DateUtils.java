package com.lzx.peachutil.utils.data;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * DATE 时间工具类
 *
 * @author lizhixin
 * @date 2021/8/6 15:49
 */
@Slf4j
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static final String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前日期
     *
     * @author lizhixin
     * @date 2021/8/9 11:18
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @author lizhixin
     * @date 2021/8/9 11:19
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd HH:mm:ss
     *
     * @author lizhixin
     * @date 2021/8/9 11:19
     */
    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取当前日期, 默认格式为yyyyMMddHHmmss
     *
     * @author lizhixin
     * @date 2021/8/9 11:19
     */
    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    /**
     * 获取当前日期, 根据传入的格式进行格式化
     *
     * @author lizhixin
     * @date 2021/8/9 11:19
     */
    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    /**
     * 根据传入的时间格式化, 默认格式为yyyy-MM-dd
     *
     * @author lizhixin
     * @date 2021/8/9 11:21
     */
    public static final String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    /**
     * 根据传入的时间格式化, 默认格式为yyyy-MM-dd HH:mm:ss
     *
     * @author lizhixin
     * @date 2021/8/9 11:21
     */
    public static final String dateTimeFormatYMDHMS(final Date date) {
        return parseDateToStr(YYYY_MM_DD_HH_MM_SS, date);
    }

    /**
     * 根据传入的时间与格式，格式化数据
     *
     * @author lizhixin
     * @date 2021/8/9 11:22
     */
    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 日期路径 即年/月/日 如2021/08/08
     *
     * @author lizhixin
     * @date 2021/8/9 11:24
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20210808
     *
     * @author lizhixin
     * @date 2021/8/9 11:24
     */
    public static final String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     *
     * @author lizhixin
     * @date 2021/8/9 11:24
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     *
     * @author lizhixin
     * @date 2021/8/9 11:25
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     *
     * @author lizhixin
     * @date 2021/8/9 11:25
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 获取当前时间戳
     *
     * @author lizhixin
     * @date 2021/8/9 11:25
     */
    public static Long getLongTimeMillis() {
        return System.currentTimeMillis();
    }


    /**
     * 获取一天的开始时间，2021-08-08 00:00:00
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Date getDayStart(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH), gregorianCalendar.get(Calendar.DATE),
                0, 0, 0);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取一天的结束时间，2021-08-08 23:59:59
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Date getDayEnd(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH), gregorianCalendar.get(Calendar.DATE),
                23, 59, 59);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当天的开始时间，2021-08-08 00:00:00
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Date getDayStart() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(getNowDate());
        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH), gregorianCalendar.get(Calendar.DATE),
                0, 0, 0);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当天的结束时间，2021-08-08 23:59:59
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Date getDayEnd() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(getNowDate());
        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH), gregorianCalendar.get(Calendar.DATE),
                23, 59, 59);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取对应时间月份的起始日期，2021-08-01 00:00:00
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Date getMonthStart(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH), 1,
                0, 0, 0);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取对应时间月份的最后日期，2021-08-01 23:59:59
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Date getMonthEnd(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        int maximum = gregorianCalendar.getMaximum(Calendar.DAY_OF_MONTH);
        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH), maximum,
                23, 59, 59);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当月月份的起始日期，2021-08-01 00:00:00
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Date getMonthStart() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(getNowDate());
        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH), 1,
                0, 0, 0);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当月月份的最后日期，2021-08-01 23:59:59
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Date getMonthEnd() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(getNowDate());
        int maximum = gregorianCalendar.getMaximum(Calendar.DAY_OF_MONTH);
        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH), maximum,
                23, 59, 59);
        return gregorianCalendar.getTime();
    }

    /**
     * 以指定时间为基准，向后num天
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Date getDateAdd(Date date, int num) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.add(Calendar.DATE, num);
        return gregorianCalendar.getTime();
    }

    /**
     * 以指定时间为基准，向前num天
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Date getDateDel(Date date, int num) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(getNowDate());
        gregorianCalendar.add(Calendar.DATE, -num);
        return gregorianCalendar.getTime();
    }

    /**
     * 以当前时间为基准，向后num天
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Date getDateAdd(int num) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(getNowDate());
        gregorianCalendar.add(Calendar.DATE, num);
        return gregorianCalendar.getTime();
    }

    /**
     * 以当前时间为基准，向前num天
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Date getDateDel(int num) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(getNowDate());
        gregorianCalendar.add(Calendar.DATE, -num);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当天在一周中的位置
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Integer getDayOfWeek() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(getNowDate());
        return gregorianCalendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取对应时间在一周中的位置
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Integer getDayOfWeek(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        return gregorianCalendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取当天是当月中的第多少天
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Integer getDateOfMonth() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(getNowDate());
        return gregorianCalendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取对应时间是当月中的第多少天
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Integer getDateOfMonth(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        return gregorianCalendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当天是一年中的第多少天
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Integer getDateOfYear() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(getNowDate());
        return gregorianCalendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取对应时间是一年中的第多少天
     *
     * @author lizhixin
     * @date 2021/8/9 14:35
     */
    public static Integer getDateOfYear(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        return gregorianCalendar.get(Calendar.DAY_OF_YEAR);
    }

}
