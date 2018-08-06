package com.vladi.WeatherData;

import java.net.URL;
import java.io.*;
import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonObject;

/**
 * implements IweatherDataService interface, essentially accesses the web to
 * pull out the needed weather/forecast data from openweathermap.org.
 * 
 * @author Vladislav Bokhman
 * @see <a href="http://openweathermap.org/api">OpenWeatherMap </a>
 */
public class Service implements IWeatherDataService {
	/**
	 * implementing singleton pattern. returns the appropriate service needed -
	 * weather/forecast.
	 */
	private static Service service = new Service();

	public static Service getService() {
		return service;
	}

	/**
	 * 
	 * @param location
	 *            - location specified for weather data
	 * @return WeatherData object with the needed information about the current
	 *         weather
	 * @throws WeatherDataServiceException
	 */
	@Override
	public WeatherData getWeatherData(Location location) throws WeatherDataServiceException {
		URL url = null;
		WeatherData data = new WeatherData();
		InputStream is;
		try {
			if (location.getCityId() != 0)
				url = new URL("http://api.openweathermap.org/data/2.5/weather?id=" + location.getCityId()
						+ "&units=metric&appid=731ce94fde9034768ef396c90e3ead46");
			else if (location.getCityName() != null && location.getCountryCode() != null)
				url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + location.getCityName() + ","
						+ location.getCountryCode() + "&units=metric&appid=731ce94fde9034768ef396c90e3ead46");
			else if (location.getLatitude() != 0 && location.getLongitude() != 0)
				url = new URL("http://http://api.openweathermap.org/data/2.5/weather?lat=" + location.getLatitude()
						+ "&lon=" + location.getLongitude() + "&units=metric&appid=731ce94fde9034768ef396c90e3ead46");
			else
				throw new WeatherDataServiceException("insufficent data");

			is = url.openStream();
			JsonReader reader = Json.createReader(is);
			JsonObject obj = reader.readObject();
			JsonObject weather = obj.getJsonArray("weather").getJsonObject(0);
			data.setWeatherMain(weather.getString("main"));
			data.setWeatherDescription(weather.getString("description"));
			data.setWeatherIcon(weather.getString("icon"));
			JsonObject main = obj.getJsonObject("main");
			data.setDayTmp((float) main.getJsonNumber("temp").doubleValue());
			data.setPressure((float) main.getJsonNumber("pressure").doubleValue());
			data.setHumidity(main.getInt("humidity"));
			JsonObject wind = obj.getJsonObject("wind");
			data.setWndSpd((float) wind.getJsonNumber("speed").doubleValue());
			data.setWndDeg((float) wind.getJsonNumber("deg").doubleValue());
			data.setCloudsPer(obj.getJsonObject("clouds").getInt("all"));

		} catch (IOException e) {
			throw new WeatherDataServiceException(e);
		}
		return data;

	}

	/**
	 * @param location
	 *            - location specified for weather forecast data
	 * @param range
	 *            - time range for wanted weather forecast
	 * @return WeatherData object with a reference to WeatherData objects array
	 *         containing the forecast information.
	 */
	@Override
	public WeatherData getWeatherData(Location location, TimeRange range) throws WeatherDataServiceException {
		URL url = null;
		WeatherData data = new WeatherData();
		if (range.getDays() > 1)
			data.addWeatherData(range.getDays() - 1);

		InputStream is;
		try {
			if (location.getCityId() != 0)
				url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?id=" + location.getCityId()
						+ "&units=metric&cnt=" + range.getDays() + "&appid=731ce94fde9034768ef396c90e3ead46");
			else if (location.getCityName() != null && location.getCountryCode() != null)
				url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=" + location.getCityName() + ","
						+ location.getCountryCode() + "&units=metric&cnt=" + range.getDays()
						+ "&appid=731ce94fde9034768ef396c90e3ead46");
			else if (location.getLatitude() != 0 && location.getLongitude() != 0)
				url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?lat=" + location.getLatitude()
						+ "&lon=" + location.getLongitude() + "&units=metric&cnt=" + range.getDays()
						+ "&appid=731ce94fde9034768ef396c90e3ead46");
			else
				throw new WeatherDataServiceException("insufficent data");

			is = url.openStream();
			JsonReader reader = Json.createReader(is);
			JsonObject obj = reader.readObject();
			JsonObject name = obj.getJsonObject("city");
			data.setCity(name.getString("name"));
			data.setCountry(name.getString("country"));
			JsonObject list = obj.getJsonArray("list").getJsonObject(0);
			data.setDate(list.getJsonNumber("dt").longValue());
			JsonObject tmp = list.getJsonObject("temp");
			data.setMornTmp((float) tmp.getJsonNumber("morn").doubleValue());
			data.setDayTmp((float) tmp.getJsonNumber("day").doubleValue());
			data.setEveTmp((float) tmp.getJsonNumber("eve").doubleValue());
			data.setNightTmp((float) tmp.getJsonNumber("night").doubleValue());
			data.setPressure((float) list.getJsonNumber("pressure").doubleValue());
			data.setHumidity(list.getInt("humidity"));
			JsonObject weather = list.getJsonArray("weather").getJsonObject(0);
			data.setWeatherMain(weather.getString("main"));
			data.setWeatherDescription(weather.getString("description"));
			data.setWeatherIcon(weather.getString("icon"));
			data.setWndSpd((float) list.getJsonNumber("speed").doubleValue());
			data.setWndDeg((float) list.getJsonNumber("deg").doubleValue());
			data.setCloudsPer(list.getInt("clouds"));

			if (range.getDays() > 1) {
				WeatherData[] arr = data.getWeatherArray();
				for (int i = 0; i < range.getDays() - 1; i++) {
					list = obj.getJsonArray("list").getJsonObject(i + 1);
					arr[i].setDate(list.getJsonNumber("dt").longValue());
					tmp = list.getJsonObject("temp");
					arr[i].setMornTmp((float) tmp.getJsonNumber("morn").doubleValue());
					arr[i].setDayTmp((float) tmp.getJsonNumber("day").doubleValue());
					arr[i].setEveTmp((float) tmp.getJsonNumber("eve").doubleValue());
					arr[i].setNightTmp((float) tmp.getJsonNumber("night").doubleValue());
					arr[i].setPressure((float) list.getJsonNumber("pressure").doubleValue());
					arr[i].setHumidity(list.getInt("humidity"));
					weather = list.getJsonArray("weather").getJsonObject(0);
					arr[i].setWeatherMain(weather.getString("main"));
					arr[i].setWeatherDescription(weather.getString("description"));
					arr[i].setWeatherIcon(weather.getString("icon"));
					arr[i].setWndSpd((float) list.getJsonNumber("speed").doubleValue());
					arr[i].setWndDeg((float) list.getJsonNumber("deg").doubleValue());
					arr[i].setCloudsPer(list.getInt("clouds"));
				}
			}

		} catch (IOException e) {
			throw new WeatherDataServiceException(e);
		}
		return data;
	}

}
