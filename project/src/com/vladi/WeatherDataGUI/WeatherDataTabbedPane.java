package com.vladi.WeatherDataGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import com.vladi.WeatherData.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class WeatherDataTabbedPane extends JFrame {

	private static final long serialVersionUID = 1L;
	private IWeatherDataService service;
	private Location location;
	private WeatherData weatherData;
	private TimeRange time;
	private ImageIcon icon;
	private JCheckBox cbCcityCountry;
	private JCheckBox cbCcoord;
	private JCheckBox cbCid;
	private JCheckBox cbFcityCountry;
	private JCheckBox cbFid;
	private JCheckBox cbFcoord;
	private JCheckBox cbDays;
	private JCheckBox cbDates;
	private JButton getWeather;
	private JButton getForecast;
	private JButton cClear;
	private JPanel current;
	private JPanel forecast;
	private JTabbedPane tabbedPane;
	private JLabel byLocationF;
	private JLabel byIDf;
	private JLabel byCoordinatesF;
	private JLabel currentMain;
	private JLabel currentWD;
	private JLabel currentWindSpeed;
	private JLabel currentWindDeg;
	private JLabel currentClouds;
	private JLabel currentTmp;
	private JLabel currentPressure;
	private JLabel currentHumidity;
	private JLabel currentIcon;
	private JLabel byLocation;
	private JLabel byID;
	private JLabel byCoordinates;
	private JLabel fromDate;
	private JLabel toDate;
	private JLabel daysNum;
	private JTextField currentID;
	private JTextField currentCountry;
	private JTextField currentCity;
	private JTextField currentLong;
	private JTextField currentLat;
	private JTextField forecastCountry;
	private JTextField forecastCity;
	private JTextField forecastID;
	private JTextField forecastLong;
	private JTextField forecastLat;
	private JTextField days;
	private JTextField from;
	private JTextField to;
	private JTextArea forecastPanel[];
	private ActionListener listener;
	private DateFormat difference;
	private int range;

	public WeatherDataTabbedPane() {
		getWeather = new JButton("Get!");
		getForecast = new JButton("Get!");
		cClear = new JButton();
		current = new JPanel();
		forecast = new JPanel();
		icon = new ImageIcon("resources/icon.png");
		tabbedPane = new JTabbedPane();
		forecastPanel = new JTextArea[16];
		for (int i = 0; i < 16; i++)
			forecastPanel[i] = new JTextArea(5, 5);
		byLocation = new JLabel();
		byLocationF = new JLabel();
		byID = new JLabel();
		byIDf = new JLabel();
		byCoordinates = new JLabel();
		byCoordinatesF = new JLabel();
		currentMain = new JLabel();
		currentWD = new JLabel();
		currentWindSpeed = new JLabel();
		currentWindDeg = new JLabel();
		currentClouds = new JLabel();
		currentTmp = new JLabel();
		currentIcon = new JLabel();
		fromDate = new JLabel();
		toDate = new JLabel();
		daysNum = new JLabel();
		currentPressure = new JLabel();
		currentHumidity = new JLabel();
		currentCountry = new JTextField();
		currentCity = new JTextField();
		currentID = new JTextField();
		forecastCountry = new JTextField();
		forecastCity = new JTextField();
		forecastID = new JTextField();
		currentLong = new JTextField();
		currentLat = new JTextField();
		forecastLong = new JTextField();
		forecastLat = new JTextField();
		from = new JTextField();
		to = new JTextField();
		days = new JTextField();
		cbCcityCountry = new JCheckBox();
		cbCcoord = new JCheckBox();
		cbCid = new JCheckBox();
		cbFcityCountry = new JCheckBox();
		cbFcoord = new JCheckBox();
		cbFid = new JCheckBox();
		cbDays = new JCheckBox();
		cbDates = new JCheckBox();

		listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				if (getWeather == source) {
					try {
						location = new Location();
						if (cbCid.isSelected()) {
							location.setCityId(Integer.parseInt(currentID.getText().trim()));
						}
						if (cbCcityCountry.isSelected()) {
							location.setCountryCode(currentCountry.getText().trim());
							location.setCityName(currentCity.getText().trim());
						} else {
							location.setLatitude(Double.parseDouble(currentLat.getText().trim()));
							location.setLongtitude(Double.parseDouble(currentLong.getText().trim()));
						}

						service = WeatherDataServiceFactory
								.getWeatherDataService(WeatherDataServiceFactory.OPEN_WEATHER_MAP);

						weatherData = service.getWeatherData(location);
						URL url = new URL("http://openweathermap.org/img/w/" + weatherData.getWeatherIcon() + ".png");
						BufferedImage image = ImageIO.read(url);
						currentIcon.setIcon(new ImageIcon(image));
						currentIcon.setVisible(true);
						currentMain.setText(weatherData.getWeatherMain());
						currentWD.setText(weatherData.getWeatherDescription());
						currentTmp.setText("Temp: " + String.valueOf(weatherData.getDayTmp()) + "°C");
						currentWindSpeed.setText("Wind speed: " + String.valueOf(weatherData.getWndSpd()) + "m/s");
						currentWindDeg.setText("Wind Degree: " + String.valueOf(weatherData.getWndDeg()) + "°");
						currentPressure.setText("Pressure: " + String.valueOf(weatherData.getPressure()) + "hPa");
						currentHumidity.setText(String.valueOf("Humidity:" + weatherData.getHumidity()) + "°");
						currentClouds.setText("Clouds: " + String.valueOf(weatherData.getCloudsPer()) + "°");
						currentClouds.setVisible(true);
					} catch (WeatherDataServiceException e1) {
						WeatherDataServiceException.showQuickErrorDialog(current, e1);
					} catch (MalformedURLException e1) {
						WeatherDataServiceException.showQuickErrorDialogAny(current, e1);
					} catch (IOException e1) {
						WeatherDataServiceException.showQuickErrorDialogAny(current, e1);
					} catch (NumberFormatException e1) {
						WeatherDataServiceException.showQuickErrorDialogOther(current, e1);
					}

				} else if (cClear == source) {
					currentMain.setText(null);
					currentWD.setText(null);
					currentTmp.setText(null);
					currentWindSpeed.setText(null);
					currentWindDeg.setText(null);
					currentPressure.setText(null);
					currentHumidity.setText(null);
					cbCcityCountry.setSelected(false);
					cbCcoord.setSelected(false);
					cbCid.setSelected(false);
					currentCity.setVisible(false);
					currentCountry.setVisible(false);
					currentLat.setVisible(false);
					currentLong.setVisible(false);
					currentID.setVisible(false);
					currentIcon.setVisible(false);
					currentClouds.setVisible(false);
					//
					cbFid.setVisible(true);
					cbFcoord.setVisible(true);
					cbFcityCountry.setVisible(true);
					byLocationF.setVisible(true);
					byIDf.setVisible(true);
					byCoordinatesF.setVisible(true);
					forecast.setLayout(null);
					to.setVisible(false);
					from.setVisible(false);
					days.setVisible(false);
					daysNum.setVisible(true);
					fromDate.setVisible(true);
					toDate.setVisible(true);
					cbDates.setVisible(true);
					cbDays.setVisible(true);
					getForecast.setVisible(true);
					if (cbFcityCountry.isSelected())
						cbFcityCountry.doClick();
					else if (cbFid.isSelected())
						cbFid.doClick();
					else if (cbFcoord.isSelected())
						cbFcoord.doClick();
					if (cbDates.isSelected()) {
						for (int i = 0; i < forecastPanel.length; i++) {
							forecastPanel[i].setVisible(false);
							forecastPanel[i].setText(null);
						}
					} else if (cbDays.isSelected()) {

						for (int i = 0; i < forecastPanel.length; i++) {
							forecastPanel[i].setVisible(false);
							forecastPanel[i].setText(null);
						}
					}
					cbDays.setSelected(false);
					cbDates.setSelected(false);

				} else if (cbCcityCountry == source) {
					if (cbCcityCountry.isSelected()) {
						currentCountry.setVisible(true);
						currentCountry.setText("Country");
						currentCity.setText("City");
						currentCity.setVisible(true);
						if (cbCcoord.isSelected())
							cbCcoord.doClick();
						if (cbCid.isSelected())
							cbCid.doClick();
					} else {
						currentCountry.setVisible(false);
						currentCity.setVisible(false);
					}
				} else if (cbCcoord == source) {
					if (cbCcoord.isSelected()) {
						currentLat.setText("Latitude");
						currentLat.setVisible(true);
						currentLong.setText("Longitude");
						currentLong.setVisible(true);
						if (cbCid.isSelected())
							cbCid.doClick();
						if (cbCcityCountry.isSelected())
							cbCcityCountry.doClick();
					} else {
						currentLat.setVisible(false);
						currentLong.setVisible(false);
					}
				} else if (cbCid == source) {
					if (cbCid.isSelected()) {
						currentID.setText("ID");
						currentID.setVisible(true);
						if (cbCcoord.isSelected())
							cbCcoord.doClick();
						if (cbCcityCountry.isSelected())
							cbCcityCountry.doClick();
					} else
						currentID.setVisible(false);

				}
				/*****************************************************************
				 * forecast * *
				 *****************************************************************/

				else if (cbFcityCountry == source) {
					if (cbFcityCountry.isSelected()) {
						forecastCountry.setVisible(true);
						forecastCountry.setText("Country");
						forecastCity.setText("City");
						forecastCity.setVisible(true);
						cbFid.setSelected(false);
						forecastID.setVisible(false);
						cbFcoord.setSelected(false);
						forecastLat.setVisible(false);
						forecastLong.setVisible(false);
					} else {
						forecastCountry.setVisible(false);
						forecastCity.setVisible(false);
					}
				} else if (cbFcoord == source) {
					if (cbFcoord.isSelected()) {
						forecastLat.setText("Latitude");
						forecastLong.setText("Longitude");
						forecastLong.setVisible(true);
						forecastLat.setVisible(true);
						if (cbFid.isSelected())
							cbFid.doClick();
						if (cbFcityCountry.isSelected())
							cbFcityCountry.doClick();
					} else {
						forecastLat.setVisible(false);
						forecastLong.setVisible(false);
					}
				} else if (cbFid == source) {
					if (cbFid.isSelected()) {
						forecastID.setText("ID");
						forecastID.setVisible(true);
						cbFcoord.setSelected(false);
						forecastLat.setVisible(false);
						forecastLong.setVisible(false);
						cbFcityCountry.setSelected(false);
						forecastCountry.setVisible(false);
						forecastCity.setVisible(false);
					} else {
						forecastID.setVisible(false);
					}
				} else if (cbDays == source) {
					if (cbDays.isSelected()) {
						days.setText("days");
						days.setVisible(true);
						if (cbDates.isSelected())
							cbDates.doClick();
					} else
						days.setVisible(false);
				} else if (cbDates == source) {
					if (cbDates.isSelected()) {
						from.setText("from");
						from.setVisible(true);
						to.setText("to");
						to.setVisible(true);
						if (cbDays.isSelected())
							cbDays.doClick();
					} else {
						from.setVisible(false);
						to.setVisible(false);
					}
				} else if (getForecast == source) {
					forecastLat.setVisible(false);
					forecastLong.setVisible(false);
					forecastID.setVisible(false);
					forecastCity.setVisible(false);
					forecastCountry.setVisible(false);
					cbFid.setVisible(false);
					cbFcoord.setVisible(false);
					cbFcityCountry.setVisible(false);
					byLocationF.setVisible(false);
					byIDf.setVisible(false);
					byCoordinatesF.setVisible(false);
					to.setVisible(false);
					from.setVisible(false);
					days.setVisible(false);
					daysNum.setVisible(false);
					fromDate.setVisible(false);
					toDate.setVisible(false);
					cbDates.setVisible(false);
					cbDays.setVisible(false);
					getForecast.setVisible(false);
					forecast.setLayout(new FlowLayout());
					service = WeatherDataServiceFactory
							.getWeatherDataService(WeatherDataServiceFactory.OPEN_WEATHER_MAP);
					if (cbDates.isSelected()) {

						int j = 1;

						time = new TimeRange();
						time.setDates(from.getText(), to.getText());
						TimeRange diff = new TimeRange();
						difference = new SimpleDateFormat("dd/MM/yyyy");
						Date currentDate = new Date();
						diff.setDates(difference.format(currentDate), from.getText());
						int interval = diff.getDays();
						range = time.getDays();
						time.setDays(16);
						location = new Location();

						if (cbFcityCountry.isSelected()) {
							location.setCountryCode(forecastCountry.getText());
							location.setCityName(forecastCity.getText());

						} else if (cbFid.isSelected()) {
							location.setCityId(Integer.parseInt(forecastID.getText()));

						} else if (cbFcoord.isSelected()) {
							location.setLatitude(Double.parseDouble(forecastLat.getText()));
							location.setLongtitude(Double.parseDouble(forecastLong.getText()));
						}
						try {
							weatherData = service.getWeatherData(location, time);
							for (int i = 0; i < range + 1; i++) {
								forecast.add(forecastPanel[i]);
								forecastPanel[i].setVisible(true);
							}
							if (interval == 0) {
								forecastPanel[0].append(weatherData.getCity() + "," + weatherData.getCountry() + "\n");
								forecastPanel[0].append(weatherData.getDate() + "\n");
								forecastPanel[0].append(String.valueOf(weatherData.getWeatherMain()) + "\n");
								forecastPanel[0].append(String.valueOf(weatherData.getWeatherDescription()) + "\n");
								forecastPanel[0].append(
										"Morning temp: " + String.valueOf(weatherData.getMornTmp()) + "°C" + "\n");
								forecastPanel[0]
										.append("Day temp: " + String.valueOf(weatherData.getDayTmp()) + "°C" + "\n");
								forecastPanel[0].append(
										"Evening temp: " + String.valueOf(weatherData.getEveTmp()) + "°C" + "\n");
								forecastPanel[0].append(
										"Night temp: " + String.valueOf(weatherData.getNightTmp()) + "°C" + "\n");
								forecastPanel[0].append(
										"Pressure: " + String.valueOf(weatherData.getPressure()) + "hPa" + "\n");
								forecastPanel[0]
										.append("Humidity: " + String.valueOf(weatherData.getHumidity()) + "°" + "\n");
								forecastPanel[0]
										.append("Wind deg: " + String.valueOf(weatherData.getWndDeg()) + "°" + "\n");
								forecastPanel[0].append(
										"Wind speed: " + String.valueOf(weatherData.getWndSpd()) + "m/s" + "\n");
								forecastPanel[0].append(
										"Cloud percentage: " + String.valueOf(weatherData.getCloudsPer()) + "°" + "\n");
							} else
								j = 0;
							for (int i = interval; i < time.getDays() - (time.getDays() - interval - range - 2); i++) {
								WeatherData data[] = weatherData.getWeatherArray();
								forecastPanel[j].append(weatherData.getCity() + "," + weatherData.getCountry() + "\n");
								forecastPanel[j].append(data[i].getDate() + "\n");
								forecastPanel[j].append(String.valueOf(data[i].getWeatherMain()) + "\n");
								forecastPanel[j].append(String.valueOf(data[i].getWeatherDescription()) + "\n");
								forecastPanel[j]
										.append("Morning temp: " + String.valueOf(data[i].getMornTmp()) + "°C" + "\n");
								forecastPanel[j]
										.append("Day temp: " + String.valueOf(data[i].getDayTmp()) + "°C" + "\n");
								forecastPanel[j]
										.append("Evening temp: " + String.valueOf(data[i].getEveTmp()) + "°C" + "\n");
								forecastPanel[j]
										.append("Night temp: " + String.valueOf(data[i].getNightTmp()) + "°C" + "\n");
								forecastPanel[j]
										.append("Pressure: " + String.valueOf(data[i].getPressure()) + "hPa" + "\n");
								forecastPanel[j]
										.append("Humidity: " + String.valueOf(data[i].getHumidity()) + "°" + "\n");
								forecastPanel[j]
										.append("Wind deg: " + String.valueOf(data[i].getWndDeg()) + "m/s" + "\n");
								forecastPanel[j].append("Wind speed: " + String.valueOf(data[i].getWndSpd()) + "\n");
								forecastPanel[j++].append(
										"Cloud percentage: " + String.valueOf(data[i].getCloudsPer()) + "°" + "\n");
							}
						} catch (NumberFormatException e1) {

							WeatherDataServiceException.showQuickErrorDialogOther(forecast, e1);

						} catch (WeatherDataServiceException e1) {
							WeatherDataServiceException.showQuickErrorDialog(forecast, e1);
						}
					} else if (cbDays.isSelected()) {
						try {
							time = new TimeRange();

							time.setDays(Integer.parseInt(days.getText()));
							location = new Location();
							if (cbFcityCountry.isSelected()) {
								location.setCountryCode(forecastCountry.getText());
								location.setCityName(forecastCity.getText());
							} else if (cbFid.isSelected()) {
								location.setCityId(Integer.parseInt(forecastID.getText()));
							} else if (cbFcoord.isSelected()) {
								location.setLatitude(Double.parseDouble(forecastLat.getText()));
								location.setLongtitude(Double.parseDouble(forecastLong.getText()));
							}
							try {
								weatherData = service.getWeatherData(location, time);
								for (int i = 0; i < time.getDays(); i++) {
									forecast.add(forecastPanel[i]);
									forecastPanel[i].setVisible(true);
								}
								forecastPanel[0].append(weatherData.getCity() + "," + weatherData.getCountry() + "\n");
								forecastPanel[0].append(weatherData.getDate() + "\n");
								forecastPanel[0].append(String.valueOf(weatherData.getWeatherMain()) + "\n");
								forecastPanel[0].append(String.valueOf(weatherData.getWeatherDescription()) + "\n");
								forecastPanel[0].append(
										"Morning temp: " + String.valueOf(weatherData.getMornTmp()) + "°C" + "\n");
								forecastPanel[0]
										.append("Day temp: " + String.valueOf(weatherData.getDayTmp()) + "°C" + "\n");
								forecastPanel[0].append(
										"Evening temp: " + String.valueOf(+weatherData.getEveTmp()) + "°C" + "\n");
								forecastPanel[0].append(
										"Night temp: " + String.valueOf(weatherData.getNightTmp()) + "°C" + "\n");
								forecastPanel[0].append(
										"Pressure: " + String.valueOf(weatherData.getPressure()) + "hPa" + "\n");
								forecastPanel[0]
										.append("Humidity: " + String.valueOf(weatherData.getHumidity()) + "°" + "\n");
								forecastPanel[0]
										.append("Wind deg: " + String.valueOf(weatherData.getWndDeg()) + "°" + "\n");
								forecastPanel[0].append(
										"Wind speed: " + String.valueOf(weatherData.getWndSpd()) + "m/s" + "\n");
								forecastPanel[0].append(
										"Cloud percentage: " + String.valueOf(weatherData.getCloudsPer()) + "°" + "\n");
								for (int i = 1; i < time.getDays(); i++) {
									WeatherData data[] = weatherData.getWeatherArray();
									forecastPanel[i]
											.append(weatherData.getCity() + "," + weatherData.getCountry() + "\n");
									forecastPanel[i].append(data[i - 1].getDate() + "\n");
									forecastPanel[i].append(String.valueOf(data[i - 1].getWeatherMain()) + "\n");
									forecastPanel[i].append(String.valueOf(data[i - 1].getWeatherDescription()) + "\n");
									forecastPanel[i].append(
											"Morning temp: " + String.valueOf(data[i - 1].getMornTmp()) + "°C" + "\n");
									forecastPanel[i].append(
											"Day temp: " + String.valueOf(data[i - 1].getDayTmp()) + "°C" + "\n");
									forecastPanel[i].append(
											"Evening temp: " + String.valueOf(data[i - 1].getEveTmp()) + "°C" + "\n");
									forecastPanel[i].append(
											"Night temp: " + String.valueOf(data[i - 1].getNightTmp()) + "°C" + "\n");
									forecastPanel[i].append(
											"Pressure: " + String.valueOf(data[i - 1].getPressure()) + "hPa" + "\n");
									forecastPanel[i].append(
											"Humidity: " + String.valueOf(data[i - 1].getHumidity()) + "°" + "\n");
									forecastPanel[i].append(
											"Wind deg: " + String.valueOf(data[i - 1].getWndDeg()) + "°" + "\n");
									forecastPanel[i].append(
											"Wind speed: " + String.valueOf(data[i - 1].getWndSpd()) + "m/s" + "\n");
									forecastPanel[i].append("Cloud percentage: "
											+ String.valueOf(data[i - 1].getCloudsPer()) + "°" + "\n");
								}
							} catch (WeatherDataServiceException e1) {
								WeatherDataServiceException.showQuickErrorDialog(forecast, e1);
							} catch (NumberFormatException e1) {
								WeatherDataServiceException.showQuickErrorDialogOther(forecast, e1);
							}

						} catch (NumberFormatException e1) {
							WeatherDataServiceException.showQuickErrorDialogOther(forecast, e1);
						}
					} else {
						cClear.doClick();
					}
				}

			}
		};
	}

	public void start() {
		getWeather.addActionListener(listener);
		getForecast.addActionListener(listener);
		cClear.addActionListener(listener);
		current.setLayout(null);
		forecast.setLayout(null);
		tabbedPane.add(current);
		tabbedPane.add(forecast);
		tabbedPane.add("Current", current);
		tabbedPane.add("Forecast", forecast);
		add(tabbedPane);
		setResizable(false);
		cbCcityCountry.addActionListener(listener);
		cbCcoord.addActionListener(listener);
		cbCid.addActionListener(listener);
		byLocation.setBounds(380, 48, 70, 20);
		byLocation.setText("By Location");
		currentCountry.setBounds(380, 70, 70, 20);
		currentCountry.setVisible(false);
		currentCity.setBounds(380, 130, 70, 20);
		currentCity.setVisible(false);
		byID.setBounds(380, 168, 70, 20);
		byID.setText("By ID");
		currentID.setBounds(380, 190, 70, 20);
		currentID.setVisible(false);
		cbCid.setBounds(455, 190, 20, 20);
		cbCcityCountry.setBounds(455, 70, 20, 20);
		currentMain.setBounds(10, 10, 70, 20);
		currentWD.setBounds(10, 70, 180, 20);
		currentWindSpeed.setBounds(10, 190, 150, 20);
		currentWindDeg.setBounds(10, 250, 150, 20);
		currentIcon.setBounds(200, 80, 70, 70);
		currentClouds.setBounds(215, 90, 70, 20);
		currentIcon.setBounds(200, 0, 90, 90);
		currentTmp.setBounds(10, 130, 100, 20);
		byCoordinates.setBounds(380, 228, 90, 20);
		byCoordinates.setText("By coordinates");
		currentLong.setBounds(380, 250, 70, 20);
		currentLat.setBounds(380, 310, 70, 20);
		cbCcoord.setBounds(455, 250, 20, 20);
		currentLat.setVisible(false);
		currentLong.setVisible(false);
		currentPressure.setBounds(10, 310, 150, 20);
		currentHumidity.setBounds(10, 370, 70, 20);
		getWeather.setBounds(405, 390, 67, 30);
		cClear.setText("Clear");
		cClear.setBounds(328, 390, 70, 30);
		current.add(byID);
		current.add(byCoordinates);
		current.add(byLocation);
		current.add(currentCountry);
		current.add(currentCity);
		current.add(currentID);
		current.add(getWeather);
		current.add(currentWindDeg);
		current.add(currentMain);
		current.add(currentWD);
		current.add(currentWindSpeed);
		current.add(currentClouds);
		current.add(currentIcon);
		current.add(currentTmp);
		current.add(currentPressure);
		current.add(currentHumidity);
		current.add(cbCcityCountry);
		current.add(currentLong);
		current.add(currentLat);
		current.add(cbCid);
		current.add(cbCcoord);
		current.add(cClear);

		days.setBounds(315, 390, 70, 20);
		days.setText("days");
		days.setVisible(false);
		daysNum.setBounds(315, 368, 70, 20);
		daysNum.setText("Days");
		cbDays.setBounds(292, 368, 20, 20);
		cbDays.addActionListener(listener);
		to.setBounds(225, 390, 70, 20);
		toDate.setBounds(225, 368, 70, 20);
		toDate.setText("To");
		to.setText("to");
		to.setVisible(false);
		from.setBounds(135, 390, 70, 20);
		from.setVisible(false);
		cbDates.setBounds(112, 368, 20, 20);
		cbDates.addActionListener(listener);
		fromDate.setBounds(135, 368, 70, 20);
		fromDate.setText("From");
		from.setText("from");
		getForecast.setBounds(405, 390, 67, 30);
		forecastCountry.setBounds(380, 70, 70, 20);
		forecastCountry.setText("country");
		forecastCountry.setVisible(false);
		forecastCity.setBounds(380, 130, 70, 20);
		forecastCity.setText("city");
		forecastCity.setVisible(false);
		forecastID.setBounds(380, 190, 70, 20);
		forecastID.setText("ID");
		forecastID.setVisible(false);

		forecastLong.setBounds(380, 250, 70, 20);
		forecastLong.setText("Longitude");
		forecastLong.setVisible(false);
		forecastLat.setBounds(380, 310, 70, 20);
		forecastLat.setText("latitude");
		forecastLat.setVisible(false);
		cbFcityCountry.addActionListener(listener);
		cbFcityCountry.setBounds(455, 70, 20, 20);
		cbFcoord.addActionListener(listener);
		cbFcoord.setBounds(455, 250, 20, 20);
		cbFid.addActionListener(listener);
		cbFid.setBounds(455, 190, 20, 20);
		byIDf.setBounds(380, 168, 70, 20);
		byIDf.setText("By ID");
		byLocationF.setBounds(380, 48, 70, 20);
		byLocationF.setText("By Location");
		byCoordinatesF.setBounds(380, 228, 90, 20);
		byCoordinatesF.setText("By coordinates");
		forecast.add(cbDates);
		forecast.add(cbDays);
		forecast.add(fromDate);
		forecast.add(toDate);
		forecast.add(daysNum);
		forecast.add(days);
		forecast.add(from);
		forecast.add(to);
		forecast.add(byLocationF);
		forecast.add(byIDf);
		forecast.add(byCoordinatesF);
		forecast.add(cbFcityCountry);
		forecast.add(cbFcoord);
		forecast.add(cbFid);
		forecast.add(getForecast);
		forecast.add(forecastCountry);
		forecast.add(forecastCity);
		forecast.add(forecastID);
		forecast.add(forecastLong);
		forecast.add(forecastLat);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setVisible(true);
		setSize(500, 500);
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				WeatherDataTabbedPane test = new WeatherDataTabbedPane();
				test.start();

			}

		});

	}
}
