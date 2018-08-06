package com.vladi.WeatherData;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/*	junit test for weather app
 * @author Vladislav Bokhman
 */
public class IWeatherDataServiceTest {
	private String cName = "London", cCode = "uk";
	private TimeRange timeTest;
	private Location location;
	private WeatherData weatherData;
	private WeatherData weatherForecast;
	private static IWeatherDataService service;
	private static IWeatherDataService foreCast;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 service = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceFactory.OPEN_WEATHER_MAP); 
		 foreCast = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceFactory.OPEN_WEATHER_MAP); 
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		timeTest = new TimeRange();
		location = new Location();
		timeTest.setDays(3);
		location.setCountryCode(cCode);
		location.setCityName(cName);
		try {
			weatherData = service.getWeatherData(location);
			weatherForecast = foreCast.getWeatherData(location, timeTest);
		} catch (WeatherDataServiceException e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetWeatherData() {
		IWeatherDataService serviceTest = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceFactory.OPEN_WEATHER_MAP);
		try {
			WeatherData wd = serviceTest.getWeatherData(location);
			assertEquals("test", wd.getDayTmp(), weatherData.getDayTmp(), 0.1);
		}
		catch (WeatherDataServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetWeatherDataForecast() {
		IWeatherDataService foreCastTest = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceFactory.OPEN_WEATHER_MAP);
		try {
			WeatherData wd = foreCastTest.getWeatherData(location, timeTest);
			assertEquals("test", wd.getDayTmp(), weatherForecast.getDayTmp(), 0.1);
		}
		catch (WeatherDataServiceException e) {
			e.printStackTrace();
		}
	}
		
}


