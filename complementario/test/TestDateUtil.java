package pe.edu.ulasalle.utest.test.complementario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import pe.edu.ulasalle.utest.complementario.DateStyle;
import pe.edu.ulasalle.utest.complementario.DateUtil;
import pe.edu.ulasalle.utest.complementario.Week;

class TestDateUtil {

	@Test
	void testIsDateFalse() {
		String date = "asdas";
		boolean is_date = new DateUtil().isDate(date);
		assertEquals(false, is_date);
	}
	@Test
	void testIsDateNull() {
		boolean is_date = new DateUtil().isDate(null);
		assertEquals(false, is_date);
	}
	@Test
	void testIsDate() {
		String date = "2020-09-10";
		boolean is_date = new DateUtil().isDate(date);
		assertEquals(true, is_date);
	}

	@Test
	void testGetDateStyleFail() {
		String date = "-20sas200910";
		DateStyle style = new DateUtil().getDateStyle(date); // Try no tiene sentido de ser porque lo salva el if
		assertEquals(null, style);
	}


	@Test
	void testGetDateStyle() {
		String date = "2020-09-10";
		DateStyle style = new DateUtil().getDateStyle(date);
		assertEquals("yyyy-MM-dd", style.getValue());
	}
	
	@Test
	void testgetAccurateDate() {
		String date = "2020-03-15 15:07:01";
		//Date d1=new SimpleDateFormat("yyyy-MM-dd").parse(date); 
		Date d1 = new DateUtil().StringToDate(date);
		List<Long> timestamps = new ArrayList<Long>();
		timestamps.add(d1.getTime());
		timestamps.add(d1.getTime());
		Date accurateDate = new DateUtil().getAccurateDate(timestamps);
		assertEquals(null, accurateDate);
		
	}
	@Test
	void testgetAccurateDateChangeOrder() {
		String date = "2012-11-01";
		String date1 = "2012-01-01";
		String date2 = "2015-01-01";
		//Date d1=new SimpleDateFormat("yyyy-MM-dd").parse(date); 
		Date d = new DateUtil().StringToDate(date);
		Date d1 = new DateUtil().StringToDate(date1);
		Date d2 = new DateUtil().StringToDate(date2);
		
		List<Long> timestamps = new ArrayList<Long>();
		timestamps.add(d.getTime());
		timestamps.add(d1.getTime());
		timestamps.add(d2.getTime());

		Date accurateDate = new DateUtil().getAccurateDate(timestamps);
		assertEquals("Thu Nov 01 00:00:00 PET 2012", accurateDate.toString());
	}
	
	@Test
	void testgetAccurateDateisEmpty() {
		String date2 = "2012-11-01";
		String date1 = "2012-01-01";
		String date = "2015-01-01";
		//Date d1=new SimpleDateFormat("yyyy-MM-dd").parse(date); 
		Date d = new DateUtil().StringToDate(date);
		Date d1 = new DateUtil().StringToDate(date1);
		Date d2 = new DateUtil().StringToDate(date2);
		
		List<Long> timestamps = new ArrayList<Long>();
		timestamps.add(d.getTime());
		timestamps.add(d1.getTime());
		timestamps.add(d2.getTime());

		Date accurateDate = new DateUtil().getAccurateDate(timestamps);
		assertEquals("Thu Nov 01 00:00:00 PET 2012", accurateDate.toString());
	}
	
	@Test
	void testgetAccurateDateNull() {
		Date accurateDate = new DateUtil().getAccurateDate(null);
		assertEquals(null, accurateDate);		
	}
	
	@Test
	void testgetAccurateDateEmpty() {
		List<Long> timestamps = new ArrayList<Long>();
		Date accurateDate = new DateUtil().getAccurateDate(timestamps);
		DateStyle style = new DateUtil().getDateStyle(null);
		assertEquals(null, accurateDate);		
	}
	
	@Test
	void testgetAccurateDateComplement() {
		List<Long> timestamps = new ArrayList<Long>();
		String date1 = "2012-11-1";
		String date2 = "2012-11";
		/*
		Date d1 = new DateUtil().StringToDate(date1);
		Date d2 = new DateUtil().StringToDate(date2);
		
		timestamps.add(d1.getTime());
		timestamps.add(d2.getTime());
		
		Date accurateDate = new DateUtil().getAccurateDate(timestamps);*/
		DateStyle style = new DateUtil().getDateStyle(date1+date2);
		assertEquals(null, style);
		//assertEquals(null, accurateDate);		
	}
	
	
	@Test
	void testGetDateStyleTwo() {
		String date = "{2020-03-99";
		DateStyle style = new DateUtil().getDateStyle(date);
		//assertEquals("yyyy-MM-dd", style.getValue());
		
	}
	@Test
	void testGetDateStyleNULL() {
		DateStyle style = new DateUtil().getDateStyle(null);
		assertEquals(null, style);
		
	}

	@Test
	void testStringToDateFail() {
		String date = "20200910";
		Date style = new DateUtil().StringToDate(date);
		assertEquals(null, style);
	}

	
	@Test
	void testStringToDate() throws ParseException {
		String date = "2020-03-15";
		Date d1=new SimpleDateFormat("yyyy-MM-dd").parse(date); 
		Date converted = new DateUtil().StringToDate(date);
		assertEquals(d1, converted);
	}

	@Test
	void testStringToDateStringString() throws ParseException {
		String date = "2020-03-15";
		Date d1=new SimpleDateFormat("yyyy-MM-dd").parse(date); 
		Date util = new DateUtil().StringToDate(date, "yyyy-MM-dd");
		assertEquals(d1, util);
	}
	@Test
	void testStringToDateStringStringNull(){
		String date = null;
		Date util = new DateUtil().StringToDate(date, "yyyy-MM-dd");
		assertEquals(null, util);
	}
	@Test
	void testStringToDateStringStringFail(){
		String date = "asds";
		Date util = new DateUtil().StringToDate(date, "yyasdasyy-MM-dd");
		assertEquals(null, util);
	}

	@Test
	void testDateToStringDateString() throws ParseException {

		String date = "2020-03-15";
		String partner = null;
		Date d1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
		String util = new DateUtil().DateToString(d1, partner);
		assertEquals(null, util);
	}

	@Test
	void testDateToStringDateDateStyle() throws ParseException {
		String date = "2020-03-15";
		DateStyle style = new DateUtil().getDateStyle(date);
		String util = new DateUtil().StringToString(date,date,style);
		assertEquals("2020-03-15", util);
	}

	@Test
	void testStringToStringStringString() {
		String date = "2020-03-15";
		String util = new DateUtil().StringToString(date, "dd-MM-yyyy");
		assertEquals("15-03-2020", util);
	}

	@Test
	void testStringToStringStringDateStyle() {
		String date = "2020-03-15";
		DateStyle style = new DateUtil().getDateStyle(date);
		String util = new DateUtil().StringToString(date,style);
		assertEquals("2020-03-15", util);
	}

	@Test
	void testStringToStringStringStringString() {
		String date = "2020-03-15";
		String util = new DateUtil().StringToString(date,"yyyy-MM-dd", "dd-MM-yyyy");
		assertEquals("15-03-2020", util);
	}

	@Test
	void testAddYearStringInt() {
		
		String date = "2020-09-10";
		String style = new DateUtil().addYear(date, 1);
		assertEquals("2021-09-10", style);
	}

	@Test
	void testAddYearStringIntNegative() {
		
		String date = "2020-03-15";
		String style = new DateUtil().addYear(date, -1);
		assertEquals("2019-03-15", style);
	}

	@Test
	void testAddYearDateInt() throws ParseException {
		String date = "2020-03-15 15:07:01";
		Date d1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		Date util = new DateUtil().addYear(d1, 5);
		assertEquals("Sat Mar 15 15:07:01 PET 2025", util.toString());
	}

	@Test
	void testAddMonthStringInt() {
		String date = "2020-03-15";
		String style = new DateUtil().addMonth(date, 1);
		assertEquals("2020-04-15", style);
	}

	@Test
	void testAddMonthDateInt() throws ParseException {
		String date = "2020-03-15 15:07:01";
		Date d1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		Date util = new DateUtil().addMonth(d1, 5);
		assertEquals("Sat Aug 15 15:07:01 PET 2020", util.toString());
	}

	@Test
	void testAddDayStringInt() {
		String date = "2020-03-15";
		String style = new DateUtil().addDay(date, 1);
		assertEquals("2020-03-16", style);
	}

	@Test
	void testAddDayDateInt() throws ParseException {
		String date = "2020-03-15 15:07:01";
		Date d1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		Date util = new DateUtil().addDay(d1, 5);
		assertEquals("Fri Mar 20 15:07:01 PET 2020", util.toString());
	}

	@Test
	void testAddHourStringInt() {
		String date1 = "15:07:01";
		String dateTime = new DateUtil().addHour(date1,1);
		assertEquals("16:07:01", dateTime);	
	}

	@Test
	void testAddHourDateInt() throws ParseException {
		String date = "2020-03-15 15:07:01";
		Date d1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		Date util = new DateUtil().addHour(d1, 5);
		assertEquals("Sun Mar 15 20:07:01 PET 2020", util.toString());
	}

	@Test
	void testAddMinuteStringInt() {
		String date1 = "15:07:01";
		String dateTime = new DateUtil().addMinute(date1,1);
		assertEquals("15:08:01", dateTime);
	}

	@Test
	void testAddMinuteDateInt() throws ParseException {
		String date = "2020-03-15 15:07:01";
		Date d1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		Date util = new DateUtil().addMinute(d1, 5);
		assertEquals("Sun Mar 15 15:12:01 PET 2020", util.toString());
	}

	@Test
	void testAddSecondStringInt() {
		String date1 = "15:07:01";
		String dateTime = new DateUtil().addSecond(date1,10);
		assertEquals("15:07:11", dateTime);
	}

	@Test
	void testAddSecondDateInt() throws ParseException {
		String date = "2020-03-15 15:07:01";
		Date d1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		Date util = new DateUtil().addSecond(d1, 5);
		assertEquals("Sun Mar 15 15:07:06 PET 2020", util.toString());
	}

	@Test
	void testGetYearString() {
		String date = "2020-09-10";
		int year = new DateUtil().getYear(date);
		assertEquals(2020, year);
	}


	@Test
	void testGetMonthString() {
		String date = "2020-09-10";
		int Month = new DateUtil().getMonth(date);
		assertEquals(9, Month);	
	}

	@Test
	void testGetDayString() {
		String date = "2020-09-10";
		int day = new DateUtil().getDay(date);
		assertEquals(10, day);		
	}

	@Test
	void testGetHourString() {
		String time = "14:20:05";
		int minute = new DateUtil().getHour(time);
		assertEquals(14, minute);		
	}

	@Test
	void testGetMinuteString() {
		String time = "14:20:05";
		int minute = new DateUtil().getMinute(time);
		assertEquals(20, minute);		
	}

	@Test
	void testGetSecondString() {
		String time = "14:00:05";
		int second = new DateUtil().getSecond(time);
		assertEquals(5, second);
	}

	@Test
	void testGetSecondDate() throws ParseException {
		String time = "14:00:05";
		Date d1=new SimpleDateFormat("hh:mm:ss").parse(time);
		int t = new DateUtil().getSecond(d1);
		assertEquals(5, t);
	}

	@Test
	void testGetDateString() {
		String date = "2020-03-15";
		String util = new DateUtil().getDate(date);
		assertEquals("2020-03-15", util.toString());
	}

	@Test
	void testGetDateDate() throws ParseException {
		String date = "2020-03-15";
		Date d1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
		String util = new DateUtil().getDate(d1);
		assertEquals("2020-03-15", util);
	}

	@Test
	void testGetTimeString() {
		
		String time = "14:00";
		String converted = new DateUtil().getTime(time);
		assertEquals(null, converted);
		
	}

	@Test
	void testGetTimeDate() throws ParseException {
		String time = "14:00";
		Date d1=new SimpleDateFormat("hh:mm").parse(time);
		String t = new DateUtil().getTime(d1);
		assertEquals("14:00:00", t);
	}

	@Test
	void testGetDateTimeString() {
		String date1 = "2020/09/22 15:07:01";
		String dateTime = new DateUtil().getDateTime(date1);
		assertEquals("2020-09-22 15:07:01", dateTime);	
	}

	@Test
	void testGetDateTimeDate() throws ParseException {
		String date1 = "2020-09-22 15:07:01";
		Date d1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date1);
		String dateTime = new DateUtil().getDateTime(d1);
		assertEquals("2020-09-22 15:07:01", dateTime);	
	}

	@Test
	void testGetWeekStringSunday() {
		String date1 = "2022-03-06";
		Week week = new DateUtil().getWeek(date1);
		assertEquals("Sunday", week.getName());	
	}
	@Test
	void testGetWeekStringMonday() {
		String date1 = "2022-03-07";
		Week week = new DateUtil().getWeek(date1);
		assertEquals("Monday", week.getName());	
	}
	@Test
	void testGetWeekStringTuesday() {
		String date1 = "2022-03-08";
		Week week = new DateUtil().getWeek(date1);
		assertEquals("Tuesday", week.getName());	
	}
	@Test
	void testGetWeekStringWednesday() {
		String date1 = "2022-03-09";
		Week week = new DateUtil().getWeek(date1);
		assertEquals("Wednesday", week.getName());	
	}
	@Test
	void testGetWeekStringThursday() {
		String date1 = "2022-03-10";
		Week week = new DateUtil().getWeek(date1);
		assertEquals("Thursday", week.getName());	
	}
	@Test
	void testGetWeekStringFriday() {
		String date1 = "2022-03-11";
		Week week = new DateUtil().getWeek(date1);
		assertEquals("Friday", week.getName());	
	}
	@Test
	void testGetWeekStringSaturday() {
		String date1 = "2022-03-12";
		Week week = new DateUtil().getWeek(date1);
		assertEquals("Saturday", week.getName());	
	}

	@Test
	void testGetWeekDate() throws ParseException {
		String date1 = "2022-03-12";
		Date d1=new SimpleDateFormat("yyyy-MM-dd").parse(date1);
		Week week = new DateUtil().getWeek(d1);
		assertEquals("Saturday", week.getName());
	}

	@Test
	void testGetIntervalDaysStringString() {
		
		String date1 = "2020-03-15";
		String date2 = "2020-03-30";
		int interval = new DateUtil().getIntervalDays(date1, date2);
		assertEquals(15, interval);		
	}

	@Test
	void testGetAge() throws ParseException {
		String date1 = "2022-03-15";
		String date2 = "2020-03-30";
		Date d1=new SimpleDateFormat("yyyy-MM-dd").parse(date1);
		Date d2=new SimpleDateFormat("yyyy-MM-dd").parse(date2);
		String age = new DateUtil().getAge(d1, d2);
		assertEquals("2岁1个月18天", age);	
	}
	@Test
	void testGetAgeNull() {
		String age = new DateUtil().getAge(null, null);
		assertEquals("-1天", age);	
	}
	
	@Test
	void testWeek() {
		String date1 = "2022-03-12";
		Week week = new DateUtil().getWeek(date1);
		String chinese_name = week.getChineseName();
		String short_name = week.getShortName();
		int number = week.getNumber();
		
		String concat = chinese_name+"-"+short_name+"-"+number;

		assertEquals("星期六-Sat.-6", concat);		
	}
}