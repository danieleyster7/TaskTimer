package deyster.timer.util;

public final class TimeUtil 
{
	//Converts milliseconds to hours, drops remainder
	public static int convertHours(int time) {
		return (time / 3600000);
	}
	
	//Converts remaining milliseconds from convertHours to minutes, drops remainder
	public static int convertMins(int time) {
		return ((time % 3600000) / 60000);
	}
	
	public static int convertSecs(int time) {
		return (((time % 3600000) % 60000) / 1000);
	}
	
	public static String getHoursAndMins(String time) {
		return time.substring(0, 5);
	}
	
	public static String convertToMins(String time) 
	{
		int hours = Integer.parseInt(time.substring(0, 1));
		int mins = (hours * 60) + Integer.parseInt(time.substring(3, 4));
		return Integer.toString(mins);
	}
	
	public static int getMinsFromTicket(int timeInMins) {
		return timeInMins % 60;
	}
	
	public static int getHoursFromTicket(int timeInMins) {
		return timeInMins / 60;
	}
}
