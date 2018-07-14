package com.uat.hbc.common;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class AllUtils {
	public  static String getFormattedDate(String strDate)
	{
		Date sDate=null;
		try
		{
			strDate=strDate.replaceAll("-", "/");
		}catch(Exception e)
		{
			e.printStackTrace();
			////System.out.println("Exception in Formatting Date"+e);
		}
		return  strDate;

	}
	public  static String getFormattedDateForXML(String strDate)
	{
		Date sDate=null;
		try
		{
			strDate=strDate.replaceAll("/", "-");
		}catch(Exception e)
		{
			e.printStackTrace();
			////System.out.println("Eception in Formatting Date"+e);
		}
		return  strDate;

	}
	public  static String getFormattedDateForSql(String strDate)
	{
		String sDate=null;
		try
		{
			SimpleDateFormat fromformat = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat myformat = new SimpleDateFormat("dd/MMM/yyyy");
			sDate=myformat.format(fromformat.parse(strDate));
			//////System.out.println("date received "+sDate);
		}catch(Exception e)
		{
			e.printStackTrace();
			////System.out.println("Exception in Formatting Date"+e);
		}
		return sDate;

	}
	
	public  static String getFormattedDateForSummary(String strDate)
	{
		String sDate=null;
		try
		{
			SimpleDateFormat fromformat = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat myformat = new SimpleDateFormat("dd/MMM/yyyy");
			sDate=myformat.format(fromformat.parse(strDate));
			//////System.out.println("date received "+sDate);
		}catch(Exception e)
		{
			e.printStackTrace();
			////System.out.println("Exception in Formatting Date"+e);
		}
		return sDate;

	}

	public  static String getFormattedDateForSqlForWorkingdate(String strDate)
	{
		String sDate=null;
		try
		{
			SimpleDateFormat fromformat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat myformat = new SimpleDateFormat("dd/MM/yyyy");
			sDate=myformat.format(fromformat.parse(strDate));

			//////System.out.println("date is "+sDate);


		}catch(Exception e)
		{
			e.printStackTrace();
			////System.out.println("Exception in Formatting Date"+e);
		}
		return sDate;

	}
	public  static String getDateset(String strDate)
	{
		String sDate=null;
		try
		{
			SimpleDateFormat fromformat = new SimpleDateFormat("dd-MM-yy");
			SimpleDateFormat myformat = new SimpleDateFormat("d/M/yy");
			sDate=myformat.format(fromformat.parse(strDate));

			//////System.out.println("date is "+sDate);


		}catch(Exception e)
		{
			e.printStackTrace();
			////System.out.println("Exception in Formatting Date"+e);
		}
		return sDate;

	}

	public  static String getFormattedDateForSqlnew(String strDate)
	{
		String sDate=null;
		try
		{
			SimpleDateFormat fromformat = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat myformat = new SimpleDateFormat("M/d/yyyy");
			sDate=myformat.format(fromformat.parse(strDate));
			//////System.out.println("date is "+sDate);
		}catch(Exception e)
		{
			e.printStackTrace();
			////System.out.println("Exception in Formatting Date"+e);
		}
		return sDate;

	}
	public  static String getCustomFormattedDateForSql(String strDate,String format)
	{
		String sDate=null;
		try
		{
			SimpleDateFormat fromformat = new SimpleDateFormat(format);//"yyyy-MM-dd"
			//////System.out.println("allutils fromformat "+fromformat);
			SimpleDateFormat myformat = new SimpleDateFormat("dd/MM/yyyy");
			sDate=myformat.format(fromformat.parse(strDate));

			//////System.out.println("date is "+sDate);


		}catch(Exception e)
		{
			e.printStackTrace();
			////System.out.println("Exception in Formatting Date"+e);
		}
		return sDate;

	}

	public static String getroundvalue(Double value)
	{
		String formate="0.00";
		try
		{
			DecimalFormat df=new DecimalFormat("0.00");
			formate = df.format(value); 
			//////System.out.println("formate is "+formate);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			////System.out.println("check Exception in rounding value"+e.getMessage());
		}
		return formate;

	}
	public static String getCurrentDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		//////System.out.println(dateFormat.format(date));
		return dateFormat.format(date);
		
	}
	public static String getCurrentDateWithTimeEntered(String strTime)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"+" "+strTime);
		Date date = new Date();
		////System.out.println(dateFormat.format(date));
		return dateFormat.format(date);
		
	}
	
	public static String getCurrentDateWithAMPM(String strTime) throws ParseException
	{
		String dt = (new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		////System.out.println("3) dt = "+dt);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		////System.out.println("4) "+ dt+" "+strTime);
		Date date = dateFormat.parse(dt+" "+strTime);
		////System.out.println(dateFormat.format(date));
		return dateFormat.format(date);
		
	}
	
	public static String getCurrentDateWithTime(String strTime)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"+" "+strTime+":00");
		Date date = new Date();
		////System.out.println(dateFormat.format(date));
		return dateFormat.format(date);
		
	}
	
	public static String getCurrentDateWithTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		//////System.out.println(dateFormat.format(date));
		return dateFormat.format(date);
		
	}
	public static String getCurrentTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		////System.out.println("time "+dateFormat.format(date));
		return dateFormat.format(date);
		
	}
	
	public static String getCurrentTimeAmPm()
	{
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:a");
		Date date = new Date();
		////System.out.println("time "+dateFormat.format(date));
		return dateFormat.format(date);
		
	}
	
	public  static String getFormattedDateWithTime(String strDate,String format)
	{
		String sDate=null;
		try
		{
			SimpleDateFormat fromformat = new SimpleDateFormat(format);
			//SimpleDateFormat myformat = new SimpleDateFormat("dd-MMM-yyyy");
			SimpleDateFormat myformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			sDate=myformat.format(fromformat.parse(strDate));
			////System.out.println("date received "+sDate);
		}catch(Exception e)
		{
			
			////System.out.println("Exception in Formatting Date"+e);
		}
		return sDate;

	}
	
	public  static String getFormattedDateWithTime1(String strDate,String format)
	{
		String sDate=null;
		try
		{
			SimpleDateFormat fromformat = new SimpleDateFormat(format);
			//SimpleDateFormat myformat = new SimpleDateFormat("dd-MMM-yyyy");
			SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");
			sDate=myformat.format(fromformat.parse(strDate));
			////System.out.println("date received "+sDate);
		}catch(Exception e)
		{
			
			////System.out.println("Exception in Formatting Date"+e);
		}
		return sDate;

	}
	
	public static String getCurrentDate(String dateformat)
	{
		DateFormat dateFormat = new SimpleDateFormat(dateformat);
		Date date = new Date();
		//////System.out.println(dateFormat.format(date));
		return dateFormat.format(date);
		
	}
	
	public static String round(double price) {
		
		NumberFormat numFormat = NumberFormat.getInstance();
		numFormat.setMinimumFractionDigits(2);
		numFormat.setMaximumFractionDigits(2);
		String amt = numFormat.format(price);
		return amt.replaceAll(",", "");
		
	}

	public  static String getFormattedDateOracle(String strDate)
	{
		String sDate=null;
		try
		{
			SimpleDateFormat fromformat = new SimpleDateFormat("dd/MM/yyyy");
			//SimpleDateFormat myformat = new SimpleDateFormat("dd-MMM-yyyy");
			SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sDate=myformat.format(fromformat.parse(strDate));
			////System.out.println("date received "+sDate);
		}catch(Exception e)
		{
			
			////System.out.println("Exception in Formatting Date"+e);
		}
		return sDate;

	}
	
	public  static String getFormattedDateOracleWithTime(String strDate)
	{
		String sDate=null;
		try
		{
			SimpleDateFormat fromformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			//SimpleDateFormat myformat = new SimpleDateFormat("dd-MMM-yyyy");
			SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sDate=myformat.format(fromformat.parse(strDate));
			////System.out.println("date received "+sDate);
		}catch(Exception e)
		{
			
			////System.out.println("Exception in Formatting Date"+e);
		}
		return sDate;

	}
	
	public  static String getFormattedDatereturn(String strDate)
	{
		String sDate=null;
		try
		{
			SimpleDateFormat fromformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//SimpleDateFormat myformat = new SimpleDateFormat("dd-MMM-yyyy");
			SimpleDateFormat myformat = new SimpleDateFormat("dd/MM/yyyy");
			sDate=myformat.format(fromformat.parse(strDate));
			////System.out.println("date received back "+sDate);
		}catch(Exception e)
		{
			
			////System.out.println("Exception in Formatting Date"+e);
		}
		return sDate;

	}
	public  static String getFormattedForSpring(String strDate, String format)
	{
		String sDate=null;
		try
		{
			SimpleDateFormat fromformat = new SimpleDateFormat(format);
			//SimpleDateFormat myformat = new SimpleDateFormat("dd-MMM-yyyy");
			SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sDate=myformat.format(fromformat.parse(strDate));
			//////System.out.println("date received "+sDate);
		}catch(Exception e)
		{
			////System.out.println("Exception in Formatting Date"+e);
		}
		return sDate;

	}
	
	public static String getCurrentDateForDb()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = new Date();
		//////System.out.println(dateFormat.format(date));
		return dateFormat.format(date);
		
	}
	
	public static String getIpAddr(HttpServletRequest request) { 
	     
	    if (request.getHeader("x-forwarded-for") == null) { 
	        return request.getRemoteAddr(); 
	    } 
	    return request.getHeader("x-forwarded-for");  
	     
	}
	
	public static String getMACAddress(String ip){ 
        String str = ""; 
        /*String macAddress = ""; 
        try { 
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip); 
            InputStreamReader ir = new InputStreamReader(p.getInputStream()); 
            LineNumberReader input = new LineNumberReader(ir); 
            for (int i = 1; i <100; i++) { 
                str = input.readLine(); 
                if (str != null) { 
                    if (str.indexOf("MAC Address") > 1) { 
                        macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length()); 
                        break; 
                    } 
                } 
            } 
        } catch (IOException e) { 
            e.printStackTrace(System.out); 
        } 
        return macAddress;*/ 
        return null;
    }
	
	public  static String convertDate(String strDate, String fromFormt, String toFormat)
	{
		String sDate=null;
		try
		{
			SimpleDateFormat fromformat = new SimpleDateFormat(fromFormt);
			//SimpleDateFormat myformat = new SimpleDateFormat("dd-MMM-yyyy");
			SimpleDateFormat myformat = new SimpleDateFormat(toFormat);
			sDate=myformat.format(fromformat.parse(strDate));
			//////System.out.println("date received "+sDate);
		}catch(Exception e)
		{
			
			////System.out.println("Exception in Formatting Date"+e);
		}
		return sDate;

	}
	
	public  static String getUserDesc(String entityId, String branchId,String deptId,String userId,HttpServletRequest request)
	{
		//String userDesc=entityId+"-"+branchId+"-"+deptId+"-"+userId+"-"+getCurrentDate()+"-"+getIpAddr(request)+"-"+getMACAddress(getIpAddr(request));
		String userDesc=entityId+"-"+branchId+"-"+deptId+"-"+userId+"-"+getCurrentDate()+"-"+getIpAddr(request);
		////System.out.println("userDesc "+userDesc);
		return userDesc;
	}

	public static String calcIntrest(String intrestType,double amount,double rate, double period,String periodMode,String frequency)
	{
     
		double lv_calc_amt=0.0;
		double lv_calc_mode_value=0.0;
		double lv_time_mode_value=0.0;
		
		/*String intrestType = "C";
		double amount = 10000;
		double rate = 10;
		int period = 1;
		String periodMode = "Y";
		String frequency = "Y";*/
		
		/////////////// Period D-Daily, M-Monthly, Y-Yearly
		if(periodMode.equals("D"))
		{
				lv_time_mode_value = period / 365;
		}
		else if(periodMode.equals("M"))
		{
			lv_time_mode_value = period / 12;
		}
		else if( periodMode.equals("Y"))
		{
			lv_time_mode_value = period;
		}

		/////////////// Frequency D-daily, M-Monthly, Q-Quart, H-Half Yearly, Y-Yearly
		if(frequency.equals("D"))
		{
			lv_calc_mode_value = 365;
		}
		else if(frequency.equals("M"))
		{
			lv_calc_mode_value = 12;			
		}
		else if(frequency.equals("Q"))
		{
			lv_calc_mode_value = 4;			
		}
		else if(frequency.equals("H"))
		{
			lv_calc_mode_value = 2;			
		}
		else if(frequency.equals("Y"))
		{
			lv_calc_mode_value = 1;			
		}
		
		// Calculate Intrest  C-Compound Intrest S-Simple Intrest
		if(intrestType.equals("C"))
		{			
			lv_calc_amt = (amount * Math.pow((1 + rate / (lv_calc_mode_value * 100)),(lv_time_mode_value * (lv_calc_mode_value))))-amount;
		}
		else if(intrestType.equals("S"))
		{	
			lv_calc_amt = (amount * rate * lv_time_mode_value)/100;
		}
		return getroundvalue(lv_calc_amt);
	}
	
	public static String getAge(String dateStr){
		Date currDate = new Date();
		Date reqDate = null;
		try {
			reqDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long diff = Math.abs(currDate.getTime() - reqDate.getTime());
		long diffDays = diff / (24 * 60 * 60 * 1000);
		
		diffDays = diffDays/365;
		
		return ""+diffDays;
	}
	
}
