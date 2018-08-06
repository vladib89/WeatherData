package com.vladi.WeatherData;

/**
 * holds the needed location information for weather/forecast data used by
 * Service class
 * 
 * @author Vladislav Bokhman
 *
 */
public class Location {

	private int cityId = 0;
	private String countryCode = null;
	private double longitude = 0;
	private double latitude = 0;
	private String cityName = null;

	/**
	 * Default constructor, didn't see a use of having one for each possible
	 * value received
	 */
	public Location() {
	}

	/**
	 * Returns the current city's ID.
	 * 
	 * @return city's ID.
	 * @see <a href="http://bulk.openweathermap.org/sample/">OpenWeatherMap
	 *      CityId list</a> for a list of city IDs.
	 */
	public int getCityId() {
		return cityId;
	}

	/**
	 * Sets the current city's ID.
	 * 
	 * @param cityId.
	 * @see <a href="http://bulk.openweathermap.org/sample/">OpenWeatherMap
	 *      CityId list</a> for a list of city IDs.
	 */
	public void setCityId(int cityId) {
		if (cityId != 0) {
			this.cityId = cityId;
		}
	}

	/**
	 * Returns geographic longitude.
	 * 
	 * @return longitude of geographical location.
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Sets longitude to a specific location.
	 * 
	 * @param longitude
	 */
	public void setLongtitude(double longitude) {
		if (longitude != 0) {
			this.longitude = longitude;
		}
	}

	/**
	 * Returns country code compliant to ISO 3166 country codes.
	 * 
	 * @return countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Sets country code compliant to ISO 3166 country codes.
	 * 
	 * @param countryCode
	 */
	public void setCountryCode(String countryCode) {
		if (countryCode != null) {
			this.countryCode = countryCode;
		}
	}

	/**
	 * Returns geographical latitude of a specific location.
	 * 
	 * @return latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Sets geographical latitude of a specific location.
	 * 
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		if (latitude != 0) {
			this.latitude = latitude;
		}
	}

	/**
	 * Returns the name of the specific city in question.
	 * 
	 * @return cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * Sets the name of the city.
	 * 
	 * @param cityName
	 */
	public void setCityName(String cityName) {
		if (cityName != null) {
			this.cityName = cityName;
		}
	}

}