package com.example.org.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public Date parse(String text) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (text == null || !text.matches("\\d{4}-[01]\\d-[0-3]\\d"))
                throw new Exception();
            return df.parse(text);
        } catch (Exception ex) {
            return null;
        }
    }
}
