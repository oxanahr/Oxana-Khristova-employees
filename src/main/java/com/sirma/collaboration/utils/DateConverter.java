package com.sirma.collaboration.utils;

import com.opencsv.bean.AbstractBeanField;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;


public class DateConverter extends AbstractBeanField {

    private static final String[] POSSIBLE_DATE_FORMATS = {
        "yyyy.MM.dd G 'at' HH:mm:ss z",
        "EEE, MMM d, ''yy", "h:mm a",
        "hh 'o''clock' a, zzzz",
        "K:mm a, z",
        "yyyyy.MMMMM.dd GGG hh:mm aaa",
        "EEE, d MMM yyyy HH:mm:ss Z",
        "yyMMddHHmmssZ",
        "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
        "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
        "YYYY-'W'ww-u",
        "EEE, dd MMM yyyy HH:mm:ss z",
        "EEE, dd MMM yyyy HH:mm zzzz",
        "yyyy-MM-dd'T'HH:mm:ssZ",
        "yyyy-MM-dd'T'HH:mm:ss.SSSzzzz",
        "yyyy-MM-dd'T'HH:mm:sszzzz",
        "yyyy-MM-dd'T'HH:mm:ss z",
        "yyyy-MM-dd'T'HH:mm:ssz",
        "yyyy-MM-dd'T'HH:mm:ss",
        "yyyy-MM-dd'T'HHmmss.SSSz",
        "yyyy-MM-dd",
        "yyyyMMdd",
        "dd/MM/yy",
        "dd/MM/yyyy"
    };

    @Override
    protected Object convert(String s) {
        try {
            return DateUtils.parseDate(s, POSSIBLE_DATE_FORMATS);
        } catch (ParseException e) {
            return new Date();
        }
    }
}
