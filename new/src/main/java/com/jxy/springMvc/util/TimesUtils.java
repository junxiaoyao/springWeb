package com.jxy.springMvc.util;
import java.util.Calendar;
import java.util.Date;

public class TimesUtils {
	public static String[] times(){
		Date date=new Date();
		int month=date.getMonth()+12;
		String[]times=new String[month+2];
		times[times.length-1]=DateUtils.format(date,"yyyy-MM-dd HH:mm:ss");
		for(int i=0;i<=month;i++){
			String time=DateUtils.getBackMonthTime(date, i);
			Date date1=DateUtils.toDate(time);
			int year=date1.getYear();
			int mon=date1.getMonth();
			Date dateTime=new Date(year, mon, 1);
			times[times.length-2-i]=DateUtils.format(dateTime,"yyyy-MM-dd HH:mm:ss");
		}
		return times;
	}
    public static String[] getTimes(String time){
    	String[] times=time.trim().split("~");
    	times[0]=times[0].trim()+"-01 00:00:00";
    	times[1]=times[1].trim()+"-01 00:00:00";
    	System.out.println(times[1]);
    	Date date1=DateUtils.toDate(times[0]);
    	Date date2=DateUtils.toDate(times[1]);
    	int length=getMonthDiff(date1, date2);
    	String[] times2=times(date2, length);
		return times2;
    }
	public static String[] times(Date date,int length){
		//Date date=new Date();
		int month=date.getMonth()+12;
		String[]times=new String[length+2];
		times[times.length-1]=DateUtils.getBackMonthTime(date, -1).toString();
		for(int i=0;i<=length;i++){
			String time=DateUtils.getBackMonthTime(date, i);
			Date date1=DateUtils.toDate(time);
			int year=date1.getYear();
			int mon=date1.getMonth();
			Date dateTime=new Date(year, mon, 1);
			times[times.length-2-i]=DateUtils.format(dateTime,"yyyy-MM-dd HH:mm:ss");
		}
		return times;
	}
	public static String[]  dates(String[]times){
		String[] dates =new String[times.length-1];
		for(int i=0;i<dates.length;i++){
			dates[i]=times[i].substring(0, 7);
		}
		return dates;
	}
    public static int getMonthDiff(Date d1, Date d2) {  
        Calendar c1 = Calendar.getInstance();  
        Calendar c2 = Calendar.getInstance();  
        c1.setTime(d1);  
        c2.setTime(d2);  
        int year1 = c1.get(Calendar.YEAR);  
        int year2 = c2.get(Calendar.YEAR);  
        int month1 = c1.get(Calendar.MONTH);  
        int month2 = c2.get(Calendar.MONTH);  
        int nonthC=(year2-year1)*12+month2-month1;
        return nonthC;  
    } 
}
