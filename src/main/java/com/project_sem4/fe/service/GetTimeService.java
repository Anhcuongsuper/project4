package com.project_sem4.fe.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Service
public class GetTimeService {
    public static void main(String[] args) {
//        Calendar now = Calendar.getInstance();
//        System.out.println("Current date : " + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR));
//
//        String[] strDays = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday",
//                "Friday", "Saturday" };
//        // Day_OF_WEEK starts from 1 while array index starts from 0
//        System.out.println("Current day is : " + strDays[now.get(Calendar.DAY_OF_WEEK) - 1]);
        Calendar c = Calendar.getInstance();
        Calendar d = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        d.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        System.out.println("Date " + c.getTimeInMillis());
        System.out.println("Date " + d.getTimeInMillis());
    }
}
