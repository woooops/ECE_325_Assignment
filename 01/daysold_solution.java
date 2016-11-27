 import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Assignment 1: Using standard libraries <br />
 * Calculate age in days
 * @author  MarcoXZh
 * @version 1.0.0
 */
public class daysold_solution {

    /**
     * Calculate how many days between today and the date
     * @param date          {@code String} The start date
     */
    public static void days(String birthday) {
        String[] values = birthday.split("-");
        Calendar cBirthday = Calendar.getInstance();
        cBirthday.set(Integer.parseInt(values[0].trim()),               // Year
                      Integer.parseInt(values[1].trim()) - 1,           // Month (start from 0)
                      Integer.parseInt(values[2].trim()));              // Day
        Calendar cToday = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d yyyy");
        System.out.print("Birthday: " + sdf.format(cBirthday.getTime()) + "; today: " + sdf.format(cToday.getTime()));
        if (cToday.before(cBirthday)) {
            System.out.println(" -- Wrong birthday!");
        } else {
            long days = (cToday.getTimeInMillis() - cBirthday.getTimeInMillis()) / (24 * 60 * 60 * 1000);
            System.out.println(" -- You are " + days + " days old.");
        } // else - if (cToday.before(cBirthday))
    } // public static void days(String birthday)

    /**
     * Main entry
     * @param args          {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        days("2000-1-1");
        days("3000-1-1");           // This is a wrong birthday
    } // public static void main(String[] args)

} // public class daysold_solution

