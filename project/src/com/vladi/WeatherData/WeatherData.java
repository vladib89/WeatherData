package com.vladi.WeatherData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * WeatherData class holds the weather/forecast information of a given day
 * 
 * @author Vladislav Bokhman
 *
 */
public class WeatherData {

	private String city;
	private String country;
	private String weatherMain;
	private String weatherDescription;
	private String weatherIcon;
	private float mornTmp;
	private float dayTmp;
	private float eveTmp;
	private float nightTmp;
	private float pressure;
	private float wndSpd;
	private float wndDeg;
	private int humidity;
	private int cloudsPer;
	private int rain3h;
	private String date;
	private WeatherData arr[];

	/**
	 * receives long type containing the unix utc date to be converted to
	 * people's date
	 * 
	 * @param UTCdate
	 *            - long containing the unix utc date
	 */
	public void setDate(long UTCdate) {
		if (UTCdate != 0) {
			Date dt = new Date(Long.valueOf(UTCdate) * 1000);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			date = sdf.format(dt) + "GMT";
		} else
			date = "N/A";
	}

	/**
	 * creates the array of WeatherData objects needed to for the forecast, time
	 * dependent
	 * 
	 * @param num
	 */
	public void addWeatherData(int num) {
		arr = new WeatherData[num];
		for (int i = 0; i < num; i++)
			arr[i] = new WeatherData();

	}

	/**
	 * 
	 * @return WeatherData object array reference
	 */
	public WeatherData[] getWeatherArray() {
		return arr;
	}

	/**
	 * 
	 * @return main weather description
	 */
	public String getWeatherMain() {
		return weatherMain;
	}

	/**
	 * sets main weather description
	 * 
	 * @param weatherMain
	 */
	public void setWeatherMain(String weatherMain) {
		if (weatherMain != null)
			this.weatherMain = weatherMain;
		else
			this.weatherMain = "N/A";
	}

	/**
	 * 
	 * @return weatherDesription string containing more detailed weather main
	 *         description
	 */
	public String getWeatherDescription() {
		return weatherDescription;
	}

	/**
	 * sets more detailed weather description
	 * 
	 * @param weatherDescription
	 *            - string containing more detailed weather main description
	 */
	public void setWeatherDescription(String weatherDescription) {
		if (weatherDescription != null)
			this.weatherDescription = weatherDescription;
		else
			this.weatherDescription = "N/A";
	}

	/**
	 * returns appropriate weather icon
	 * 
	 * @return weatherIcon string containing reference details so it could be
	 *         obtained from the url
	 */
	public String getWeatherIcon() {
		return weatherIcon;
	}

	/**
	 * sets weather icon
	 * 
	 * @param weatherIcon
	 *            - string containing reference details so it could be obtained
	 *            from the url
	 */
	public void setWeatherIcon(String weatherIcon) {
		if (weatherIcon != null)
			this.weatherIcon = weatherIcon;
		else
			this.weatherIcon = "N/A";
	}

	/**
	 * gets morning temperature - valid for forecast
	 * 
	 * @return - float containing the morning temperature - valid for forecast
	 */
	public float getMornTmp() {
		return mornTmp;
	}

	/**
	 * sets morning temperature - valid for forecast
	 * 
	 * @param mornTmp
	 *            -float containing the morning temperature - valid for forecast
	 */
	public void setMornTmp(float mornTmp) {
		this.mornTmp = mornTmp;
	}

	/**
	 * gets day temperature
	 * 
	 * @return dayTmp - float containing the day temperature
	 */
	public float getDayTmp() {
		return dayTmp;
	}

	/**
	 * sets day temperature
	 * 
	 * @param dayTmp
	 *            - float containing the day temperature
	 */
	public void setDayTmp(float dayTmp) {
		this.dayTmp = dayTmp;
	}

	/**
	 * gets evening temperature
	 * 
	 * @return dayTmp - float containing the evening temperature - valid for
	 *         forecast
	 */
	public float getEveTmp() {
		return eveTmp;
	}

	/**
	 * sets evening temperature
	 * 
	 * @param eveTmp
	 *            - float containing the evening temperature - valid for
	 *            forecast
	 */
	public void setEveTmp(float eveTmp) {
		this.eveTmp = eveTmp;
	}

	/**
	 * gets night temperature
	 * 
	 * @return nightTmp float containing the night temperature - valid for
	 *         forecast
	 */
	public float getNightTmp() {
		return nightTmp;
	}

	/**
	 * sets night temperature
	 * 
	 * @param nightTmp
	 *            - float containing the night temperature - valid for forecast
	 */
	public void setNightTmp(float nightTmp) {
		this.nightTmp = nightTmp;
	}

	/**
	 * gets ground level pressure
	 * 
	 * @return ground level pressure
	 */
	public float getPressure() {
		return pressure;
	}

	/**
	 * sets ground level pressure
	 * 
	 * @param pressure
	 *            - float containing ground level pressure
	 */
	public void setPressure(float pressure) {
		this.pressure = pressure;
	}

	/**
	 * gets wind speed
	 * 
	 * @return wind speed
	 */
	public float getWndSpd() {
		return wndSpd;
	}

	/**
	 * sets wind speed
	 * 
	 * @param wndSpd
	 *            - float containing wind speed info
	 */
	public void setWndSpd(float wndSpd) {
		this.wndSpd = wndSpd;
	}

	/**
	 * gets wind degree
	 * 
	 * @return wind degree
	 */
	public float getWndDeg() {
		return wndDeg;
	}

	/**
	 * sets wind degree
	 * 
	 * @param wndDeg
	 *            - float containing wind degree
	 */
	public void setWndDeg(float wndDeg) {
		this.wndDeg = wndDeg;
	}

	/**
	 * gets humidity percentage
	 * 
	 * @return humidity percentage
	 */
	public int getHumidity() {
		return humidity;
	}

	/**
	 * sets humidity percentage
	 * 
	 * @param humidity
	 *            - float containing humidity percentage
	 */
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	/**
	 * gets cloud percentage
	 * 
	 * @return cloud percentage
	 */
	public int getCloudsPer() {
		return cloudsPer;
	}

	/**
	 * sets cloud percentage
	 * 
	 * @param cloudsPer
	 *            - float containing information about cloud percentage
	 */
	public void setCloudsPer(int cloudsPer) {
		this.cloudsPer = cloudsPer;
	}

	/**
	 * gets quantity of rain in the last 3 hours
	 * 
	 * @return quantity of rain in the last 3 hours
	 */
	public int getRain3h() {
		return rain3h;
	}

	/**
	 * sets quantity of rain in the last 3 hours
	 * 
	 * @param rain3h
	 *            - integer containing quantity of rain in the last 3 hours
	 */
	public void setRain3h(int rain3h) {
		this.rain3h = rain3h;
	}

	/**
	 * gets the day range between two given forecast dates
	 * 
	 * @return day range between two given forecast dates
	 */
	public String getDate() {
		return date;
	}

	/**
	 * gets the country code
	 * 
	 * @return String containing the code of the country, usually 2 letters i.e
	 *         "IL" etc...
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * sets the country code
	 * 
	 * @param country
	 *            - String with the country code
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * gets the city's name
	 * 
	 * @return String containing the city's name
	 */
	public String getCity() {
		return city;
	}

	/**
	 * sets the city name
	 * 
	 * @param city
	 *            - String containing the city's name
	 */
	public void setCity(String city) {
		this.city = city;
	}

}
