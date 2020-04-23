package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Checker {

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");

	public boolean textFilled(JTextField textField, String fieldName) {
		String string = textField.getText();
		if (string.length() > 0) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Error: the " + fieldName + " field is empty", "Program message", 0);
			return false;
		}
	}

	public boolean goodInt(JTextField textField, String fieldName) {

		if (textFilled(textField, fieldName)) {
			try {
				Integer.parseInt(textField.getText());
				return true;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error: the " + fieldName + " field has incorrect number!",
						"Program message", 0);
				return false;

			}
		}
		return true;

	}

	public boolean isValidDate(String dateString) {
		try {
			Date date = simpleDateFormat.parse(dateString);
			return true;
		} catch (ParseException ef) {
			JOptionPane.showMessageDialog(null, "Performance date field as the wrong date format. " + ef.getMessage(),
					"Program message", 0);
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
			JOptionPane.showMessageDialog(null, "stringToInt: " + nfe.getMessage(), "Program message", 0);

		}
		return numberFormat;
	}

	public boolean goodIdentifier(JTextField textField, String fieldName) {

		return textFilled(textField, fieldName) && checkNumber(stringToInt(textField.getText()));

	}

	public boolean checkNumber(int number) {
		if (number > 0 && number < 100000)
			return true;
		else {
			JOptionPane.showMessageDialog(null, "Error: enter a number between 1 and 100000! ", "Program message", 0);
			return false;
		}
	}
}
