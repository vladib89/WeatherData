package com.vladi.WeatherData;

/**
 * interface for WeatherData - weather/forecast information application
 * 
 * @author Vladislav Bokhman
 *
 */

public interface IWeatherDataService {

	/**
	 * Returns weather data at current time according to specified location.
	 * 
	 * @param location
	 *            specified location of the point of interest.
	 * @param dates
	 *            used for retrieving information about air pollution and UV
	 *            index.
	 * @return WeatherData object which hold the information about the current
	 *         weather at a given location.
	 * @throws WeatherDataServiceException
	 */
	public WeatherData getWeatherData(Location location) throws WeatherDataServiceException;

	/**
	 * Returns weather forecast up to 16 days to a specific location.
	 * 
	 * @param location
	 *            specified location of the point of interest.
	 * @param days
	 *            number of days for the forecast.
	 * @return WeatherData object which hold the information about the weather
	 *         in given time spread.
	 * @throws WeatherDataServiceException
	 */
	public WeatherData getWeatherData(Location location, TimeRange days) throws WeatherDataServiceException;

	/**
	 * Returns weather data at current time surrounding a specific location or
	 * for multiple cities.
	 * 
	 * @param location
	 *            represent either point of interest or number of locations in
	 *            an array.
	 * @return WeatherData object which hold the information about the current
	 *         weather at a given locations.
	 * @throws WeatherDataServiceException
	 */

}
