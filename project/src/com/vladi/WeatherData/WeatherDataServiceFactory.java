package com.vladi.WeatherData;

/**
 * implements factory pattern to produce needed object that defines the service
 * we inquire from openweathermap.org in this case one object defines both needs
 * 
 * @author Vladislav Bokhman
 *
 */
public class WeatherDataServiceFactory {

	public static final int OPEN_WEATHER_MAP = 0;

	public static IWeatherDataService getWeatherDataService(int choice) {

		if (choice == OPEN_WEATHER_MAP)
			return Service.getService();

		return null;
	}

}