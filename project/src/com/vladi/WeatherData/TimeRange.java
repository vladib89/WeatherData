package com.vladi.WeatherData;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * holds the needed time interval for weather forecast to be used by Service
 * class
 * 
 * @author Vladislav Bokhman
 *
 */
public class TimeRange {

	private int days = 1;

	/**
	 * Returns the number of days to forecast.
	 * 
	 * @return number of days of the wanted weather forecast.
	 */
	public int getDays() {
		return days;
	}

	/**
	 * Sets the number of day for the weather forecast.
	 * 
	 * @param days
	 *            number of days of the wanted weather forecast.
	 */
	public void setDays(int days) {
		if (days > 0)
			this.days = days;
	}

	/**
	 * Sets the date for the UV index and pollution information.
	 * 
	 * @param dates
	 *            pivot point for retrieving information.
	 */
	public void setDates(String from, String to) {
		if (from != null && to != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date date1 = sdf.parse(from);
				Date date2 = sdf.parse(to);
				long diff = date2.getTime() - date1.getTime();
				this.days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
