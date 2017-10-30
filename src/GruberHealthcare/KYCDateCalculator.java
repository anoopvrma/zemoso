package GruberHealthcare;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class KYCDateCalculator {
	private static void calculateDate(String signup, String current) throws ParseException {
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		
		Calendar registrationDate = Calendar.getInstance();
		registrationDate.setTime(df.parse(signup));
		
		Calendar today = Calendar.getInstance();
		today.setTime(df.parse(current));
		
		//KYC start date
		//KYC should start from next year 30 days before anniversary
		Calendar kycStart = Calendar.getInstance();
		kycStart.setTime(registrationDate.getTime());
		
		kycStart.add(Calendar.YEAR, 1);
		kycStart.add(Calendar.DATE, -30);
		
		if(today.compareTo(kycStart)<0) {
			System.out.println("No range");
			return;
		}
		
		//get anniversary date for current year
		int year = today.get(Calendar.YEAR);
		registrationDate.set(Calendar.YEAR, year);
		
		//range
		Calendar start = Calendar.getInstance();
		start.setTime(registrationDate.getTime());
		start.add(Calendar.DATE, -30);
		
		Calendar end = Calendar.getInstance();
		end.setTime(registrationDate.getTime());
		end.add(Calendar.DATE, 30);
		
		//range for current year is after today
		//then range should be taken from previous year
		if(today.compareTo(start)<0) {
			start.add(Calendar.YEAR, -1);
			end.add(Calendar.YEAR, -1);
		}
		
		//today is within the range then end date should be today
		if(today.compareTo(end)<0) {
			end = today;
		}
		
		System.out.println(df.format(start.getTime())+" "+df.format(end.getTime()));
		
	}
	
	public static void main(String[] args) throws ParseException {
		Scanner in = new Scanner(System.in);
		//uncomment below code if needs to run in loop
		
		//int t = in.nextInt();
		//while(t-->0) {
			String signup = in.next();//"04-04-2015";
			String current = in.next();//"15-03-2016";
			calculateDate(signup, current);
		//}
		
		in.close();
	}
}
