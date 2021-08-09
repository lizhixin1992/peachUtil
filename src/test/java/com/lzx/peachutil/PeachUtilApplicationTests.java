package com.lzx.peachutil;

import com.lzx.peachutil.utils.http.OkHttpUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DisplayName("PeachUtilApplicationTests")
@SpringBootTest
class PeachUtilApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void testDateUtils() {
//        GregorianCalendar gregorianCalendar = new GregorianCalendar();
//
//        log.info(String.valueOf(gregorianCalendar.getTimeZone()));
//        gregorianCalendar.setTime(new Date());
//        log.info(String.valueOf(gregorianCalendar.get(Calendar.YEAR)));
//        log.info(String.valueOf(gregorianCalendar.get(Calendar.MONTH)));
//        log.info(String.valueOf(gregorianCalendar.get(Calendar.DATE)));
//        log.info(String.valueOf(gregorianCalendar.get(Calendar.DAY_OF_WEEK)));
//        log.info(String.valueOf(gregorianCalendar.get(Calendar.HOUR_OF_DAY)));
//        log.info(String.valueOf(gregorianCalendar.get(Calendar.MINUTE)));
//        log.info(String.valueOf(gregorianCalendar.get(Calendar.SECOND)));
//        log.info(String.valueOf(gregorianCalendar.get(Calendar.MILLISECOND)));
//
//        gregorianCalendar.set(gregorianCalendar.get(Calendar.YEAR),gregorianCalendar.get(Calendar.MONTH),gregorianCalendar.get(Calendar.DATE),
//                23,59,59);
//        log.info(parseDateToStr(YYYY_MM_DD_HH_MM_SS,gregorianCalendar.getTime()));

//        log.info(getDayOfWeek(getNowDate()).toString());

//        log.info(dateTimeFormatYMDHMS(getMonthStart(getNowDate())));
//        log.info(dateTimeFormatYMDHMS(getMonthEnd(getNowDate())));

//        log.info(dateTimeFormatYMDHMS(getDateAdd(23)));
//        log.info(dateTimeFormatYMDHMS(getDateDel(10)));
    }


    @Test
    @DisplayName("test okhttp")
    void testOkHttpUtils() {
        OkHttpUtils builder = OkHttpUtils.builder();
//        String sync = builder.url("https://www.baidu.com")
//                .addParam("a", "12121")
//                .addHeader("Content-Type", "application/json; charset=utf-8")
//                .get()
//                .sync();
//        System.out.println(sync);

//        String sync1 = builder.url("https://www.baidu.com")
//                .addParam("a", "12121")
//                .addHeader("Content-Type", "application/json; charset=utf-8")
//                .post(true)
//                .sync();
//        System.out.println(sync1);

//        String sync2 = builder.url("https://www.baidu.com")
//                .addParam("a", "12121")
//                .addHeader("Content-Type", "application/json; charset=utf-8")
//                .post(false)
//                .sync();
//        System.out.println(sync2);

//        String sync3 = builder.url("https://www.baidu.com")
//                .addParam("a", "12121")
//                .addHeader("Content-Type", "application/json; charset=utf-8")
//                .post(false)
//                .async();
//        System.out.println(sync3);

    }

}
