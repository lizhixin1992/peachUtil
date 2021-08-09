package com.lzx.peachutil.utils.localDatetime;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * LocalDateTime is used to Java8中的时间类
 * <p>
 * 为什么需要 LocalDate、LocalTime、LocalDateTime:
 * 1. Date如果不格式化，打印出的日期可读性差,使用 SimpleDateFormat对时间进行格式化，但 SimpleDateFormat是线程不安全的
 * 2. calendar是共享变量，并且这个共享变量没有做线程安全控制。
 * 当多个线程同时使用相同的 SimpleDateFormat对象【如用 static修饰的 SimpleDateFormat】调用format方法时，
 * 多个线程会同时调用calendar.setTime方法，可能一个线程刚设置好 time值另外的一个线程马上把设置的 time值给修改了,
 * 导致返回的格式化时间可能是错误的.
 * 3. Date对时间处理比较麻烦，比如想获取某年、某月、某星期，以及 n天以后的时间
 * <p>
 * <p>
 * Use Example:
 * <p>
 * LocalDateTimeUtils.formatNow("yyyy年MM月dd日 HH:mm:ss")
 * "年:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.YEARS)
 * "月:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.MONTHS)
 * "日:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.DAYS)
 * "半日:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.HALF_DAYS)
 * "小时:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.HOURS)
 * "分钟:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.MINUTES)
 * "秒:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.SECONDS)
 * "毫秒:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.MILLIS)
 * --------------------------------------------------------------------------------------------
 * 增加二十分钟
 * LocalDateTimeUtils.formatTime(LocalDateTimeUtils.plus(LocalDateTime.now(),20,ChronoUnit.MINUTES), "yyyy年MM月dd日 HH:mm")
 * 增加两年
 * LocalDateTimeUtils.formatTime(LocalDateTimeUtils.plus(LocalDateTime.now(),2, ChronoUnit.YEARS), "yyyy年MM月dd日 HH:mm")
 */

/**
 * LocalDateTime 时间工具类
 *
 * @author lizhixin
 * @date 2021/8/9 11:28
 */
@Slf4j
public class LocalDateTimeUtils {

    public static String FORMAT_Y_M_D = "yyyy-MM-dd";

    /**
     * Date转换为 LocalDateTime
     *
     * @param date date
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }


    /**
     * LocalDateTime转换为 Date
     *
     * @param time time
     * @return {@link Date}
     */
    public static Date convertLocalDateTimeToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 获取指定日期的毫秒
     *
     * @param time time
     * @return {@link Long}
     */
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


    /**
     * 获取指定日期的秒
     *
     * @param time time
     * @return {@link Long}
     */
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }


    /**
     * 获取指定时间的指定格式
     *
     * @param time    time
     * @param pattern pattern
     * @return {@link String}
     */
    public static String formatTime(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }


    /**
     * 获取当前时间的指定格式
     *
     * @param pattern pattern
     * @return {@link String}
     */
    public static String formatNow(String pattern) {
        return formatTime(LocalDateTime.now(), pattern);
    }


    /**
     * 日期加上一个数,根据 field不同加不同值,field为 ChronoUnit.*
     *
     * @param time   time
     * @param number number
     * @param field  field
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }


    /**
     * 日期减去一个数,根据 field不同减不同值,field参数为 ChronoUnit.*
     *
     * @param time   time
     * @param number number
     * @param field  field
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
        return time.minus(number, field);
    }


    /**
     * 获取两个日期的差  field参数为 ChronoUnit.*
     *
     * @param startTime startTime
     * @param endTime   endTime
     * @param field     field
     * @return {@link long}
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS) {
            return period.getYears() * 12 + period.getMonths();
        }
        return field.between(startTime, endTime);
    }


    /**
     * 获取一天的开始时间，2020,5,11 00:00
     *
     * @param time time
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }


    /**
     * 获取一天的结束时间，2020,5,11 23:59:59.999999999
     *
     * @param time time
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }

    /**
     * 获取当前时间
     *
     * @author lizhixin
     * @date 2021/7/15 14:25
     */
    public static LocalDateTime getNewDay() {
        return LocalDateTime.now(ZoneId.systemDefault());
    }
}