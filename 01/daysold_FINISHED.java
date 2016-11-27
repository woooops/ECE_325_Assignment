/**
 * Assignment 1: Using standard libraries <br />
 * Calculate age in days
 */
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class daysold {

    /**
     * Calculate how many days between today and the date, and them out
     * @param birthday      {@code String} The start date
     */
	
	public static void days(String birthday) {
        // TODO: Assignment 1 -- write your code here.
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();

		String now = (new SimpleDateFormat("yyyy-MM-dd")).format(today);
		
		cal.setTime(today);
		long time1 = cal.getTimeInMillis();
		
		String[] array1 = new String[3];
		array1 = now.split("-");
		
		int mon = Integer.parseInt(array1[1]);
		switch (mon){
		
			case 1:
				array1[1] = "January";
				break;
			case 2:
				array1[1] = "February";
				break;
			case 3:
				array1[1] = "March";
				break;
			case 4:
				array1[1] = "April";
				break;
			case 5:
				array1[1] = "May";
				break;
			case 6:
				array1[1] = "June";
				break;
			case 7:
				array1[1] = "July";
				break;
			case 8:
				array1[1] = "August";
				break;
			case 9:
				array1[1] = "September";
				break;
			case 10:
				array1[1] = "October";
				break;
			case 11:
				array1[1] = "November";
				break;
			case 12:
				array1[1] = "December";
				break;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birth = null;
		try{
			birth = sdf.parse(birthday); 
		} catch(ParseException e){
			e.printStackTrace();
		}
		birth = java.sql.Date.valueOf(birthday);
		
		cal.setTime(birth);
		long time2 = cal.getTimeInMillis();
		long interval = (time1 - time2)/(1000 * 3600 * 24);
		
		int difference = Integer.parseInt(String.valueOf(interval));
		
		String[] array = new String[3];
		array = birthday.split("-");
		
		int month = Integer.parseInt(array[1]);
		switch (month){
		
			case 1:
				array[1] = "January";
				break;
			case 2:
				array[1] = "February";
				break;
			case 3:
				array[1] = "March";
				break;
			case 4:
				array[1] = "April";
				break;
			case 5:
				array[1] = "May";
				break;
			case 6:
				array[1] = "June";
				break;
			case 7:
				array[1] = "July";
				break;
			case 8:
				array[1] = "August";
				break;
			case 9:
				array[1] = "September";
				break;
			case 10:
				array[1] = "October";
				break;
			case 11:
				array[1] = "November";
				break;
			case 12:
				array[1] = "December";
				break;
		}
		
		System.out.print("Birthday: " + array[1] + " " + array[2] + " " + array[0] + "; ");
		System.out.print("today: " + array1[1] + " " + array1[2] + " " + array1[0] + "; ");
		if(difference > 0){
			
			System.out.println("-- You are " + difference +" days old");
			
		}else{
			
			System.out.println("-- Wrong Birthday!!! ");
			
		}
    } // public static void days(String birthday)

    /**
     * Main entry
     * @param args          {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        days("2000-1-1");
        days("3000-1-1");     
        // This is a wrong birthday
    }   // public static void main(String[] args)

}
