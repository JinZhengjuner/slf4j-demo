package com.jzj.activity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Data
@AllArgsConstructor
public class SearchResult implements Comparable<SearchResult>{
    int relativeRatio;   //相关度
    long count;           //浏览数
    int  recentOrders;   //最近订单数

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        System.out.println( getNextDate(10, date));
        Instant instant = date.toInstant();
        Instant instant1 = getNextDate(10, date).toInstant();
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Shanghai"));
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant1, ZoneId.of("Asia/Shanghai"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormatter.format(localDateTime1));
        System.out.println(dateTimeFormatter.format(localDateTime2));


    }

    public static Date getNextDate(int hours, Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    @Override
    public int compareTo(SearchResult o){
        //先比较优先度
        if( this.relativeRatio != o.relativeRatio){
            return this.relativeRatio > o.relativeRatio ? 1 : -1;
        }

        //优先度相等时再比较浏览数
        if( this.count != o.count){
            return this.count > o.count ? 1 : -1;
        }
        return 0;
    }
}
