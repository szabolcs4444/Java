package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Checker {

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");

	public boolean textFilled(JTextField textField, String fieldName) {

		return textField.getText().length() > 0;
	}

	public boolean goodInt(JTextField textField, String fieldName) {

		try {
			Integer.parseInt(textField.getText());
			return true;
		} catch (NumberFormatException e) {

			return false;

		}
	}

	public boolean isValidDate(String dateString) {
		try {
			Date date = simpleDateFormat.parse(dateString);
			return true;
		} catch (ParseException ef) {

			return false;
		}
	}

	public boolean goodDate(JTextField textField, String fieldName) {

		return (textFilled(textField, fieldName) && isValidDate(textField.getText()));

	}

	public boolean filled(JTextField textField) {
		return textField.getText().length() > 0;
	}

	public int stringToInt(String string) {
		int numberFormat = -1;
		try {
			numberFormat = Integer.valueOf(string);
		} catch (NumberFormatException nfe) {

		}
		return numberFormat;
	}

	public boolean goodIdentifier(JTextField textField, String fieldName) {

		return textFilled(textField, fieldName) && checkNumber(stringToInt(textField.getText()));

	}

	public boolean checkNumber(int number) {
		return (number > 0 && number < 100000);

	}
}
