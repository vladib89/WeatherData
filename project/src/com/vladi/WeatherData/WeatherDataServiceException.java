package com.vladi.WeatherData;

import java.awt.Dimension;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * extends Throwable class, handles WeatherDataService exceptions.
 * 
 * @author Vladislav Bokhman
 *
 */
public class WeatherDataServiceException extends Throwable {

	private static final long serialVersionUID = 1L;

	/**
	 * constructor that passes the error message to super class.
	 * 
	 * @param msg
	 *            - string to be passed to inherited parent class's constructor.
	 */
	public WeatherDataServiceException(String msg) {
		super(msg);
	}

	/**
	 * constructor that passes throwable object as a variable.
	 * 
	 * @param throwable
	 *            - throwable object to passed to inherited parent class's
	 *            constructor.
	 */
	public WeatherDataServiceException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * static method to show pop up window with printed stack of the given
	 * exception.
	 * 
	 * @param parent
	 *            - reference to JPanel object on which the pop-up window will
	 *            show on.
	 * @param e
	 *            - WeatherDataServiceException object reference that contains
	 *            the error.
	 */
	public static void showQuickErrorDialog(JPanel parent, WeatherDataServiceException e) {
		final JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		textArea.setText(writer.toString());
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(350, 150));
		JOptionPane.showMessageDialog(parent, scrollPane, "Error!", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * overloaded function static method to show pop up window with printed
	 * stack of the given exception.
	 * 
	 * @param parent
	 *            - reference to JPanel object on which the pop-up window will
	 *            show on.
	 * @param e
	 *            - IOException object reference that contains the error.
	 */
	public static void showQuickErrorDialogAny(JPanel parent, IOException e) {
		final JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		textArea.setText(writer.toString());
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(350, 150));
		JOptionPane.showMessageDialog(parent, scrollPane, "Error!", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * overloaded function static method to show pop up window with printed
	 * stack of the given exception.
	 * 
	 * @param parent
	 *            - reference to JPanel object on which the pop-up window will
	 *            show on.
	 * @param e
	 *            - NumberFormatException object reference that contains the
	 *            error.
	 */
	public static void showQuickErrorDialogOther(JPanel parent, NumberFormatException e) {
		final JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		textArea.setText(writer.toString());
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(350, 150));
		JOptionPane.showMessageDialog(parent, scrollPane, "Error!", JOptionPane.ERROR_MESSAGE);
	}

}
