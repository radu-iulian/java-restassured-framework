package com.apitesting.utils;

import static org.assertj.core.util.DateUtil.parseDatetimeWithMs;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import lombok.SneakyThrows;

public class DateTimeUtils {

  private static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<>() {{
    put("^\\d{8}$", "yyyyMMdd");
    put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
    put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
    put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
    put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
    put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
    put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
    put("^\\d{12}$", "yyyyMMddHHmm");
    put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
    put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
    put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
    put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
    put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
    put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
    put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
    put("^\\d{14}$", "yyyyMMddHHmmss");
    put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
    put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
    put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
    put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
    put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
    put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
    put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
    put("^\\d{4}-\\d{1,2}-\\d{2}T\\d{1,2}:\\d{2}:\\d{2}\\+01:00$", "yyyy-MM-dd'T'HH:mm:ssXXX");
    put("^\\d{4}-\\d{1,2}-\\d{2}T\\d{1,2}:\\d{2}:\\d{2}\\+02:00$", "yyyy-MM-dd'T'HH:mm:ssXXX");
    put("^\\d{4}-\\d{1,2}-\\d{2}T\\d{1,2}:\\d{2}:\\d{2}.\\d*", "yyyy-MM-dd'T'HH:mm:ss.SSS");
  }};

  public static String formatDate(Date dateToBeFormatted) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return simpleDateFormat.format(dateToBeFormatted);
  }

  public static String formatDate(Date dateToBeFormatted, String dateFormat) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
    return simpleDateFormat.format(dateToBeFormatted);
  }

  public static SimpleDateFormat timeZoneFormatter(String dateFormat, TimeZone timeZone) {
    SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
    formatter.setTimeZone(timeZone);
    return formatter;
  }

  public static String convertTimestampToDate(Timestamp timestamp) {
    SimpleDateFormat formatterMillis = DateTimeUtils.timeZoneFormatter("yyyy-MM-dd HH:mm:ss", TimeZone.getTimeZone("Europe/Vienna"));
    return formatterMillis.format(timestamp.getTime());
  }

  @SneakyThrows
  public static Date parseDateTime(String dateString) {
    String dateFormat = determineDateFormat(dateString);
    if (dateFormat == null) {
      throw new ParseException("Unknown date format.", 0);
    }
    return parseDateTime(dateString, dateFormat);
  }

  @SneakyThrows
  public static Date parseDateTimeWithTimeZone(String dateString, TimeZone timeZone) {
    String dateFormat = determineDateFormat(dateString);
    if (dateFormat == null) {
      throw new ParseException("Unknown date format.", 0);
    }
    return parseDateTimeWithTimeZone(dateString, dateFormat, timeZone);
  }

  public static Date currentDateTimeForVienna() {
    SimpleDateFormat formatterFullMillis = DateTimeUtils.timeZoneFormatter("yyyy-MM-dd'T'HH:mm:ss.SSS",
        TimeZone.getTimeZone("Europe/Vienna"));
    return parseDatetimeWithMs(formatterFullMillis.format(new Date()));
  }

  private static Date parseDateTime(String dateString, String dateFormat) throws ParseException {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
    simpleDateFormat.setLenient(false);
    return simpleDateFormat.parse(dateString);
  }

  private static Date parseDateTimeWithTimeZone(String dateString, String dateFormat, TimeZone timeZone) throws ParseException {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
    simpleDateFormat.setTimeZone(timeZone);
    simpleDateFormat.setLenient(false);
    return simpleDateFormat.parse(dateString);
  }

  private static String determineDateFormat(String dateString) {
    for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
      if (dateString.matches(regexp)) {
        return DATE_FORMAT_REGEXPS.get(regexp);
      }
    }
    return null;
  }
}
