package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Checker {

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");

	public boolean filled(JTextField a, String an) {
		String string = a.getText();
		if (string.length() > 0) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Error: the " + an + " field is empty", "Program message", 0);
			return false;
		}
	}

	public boolean goodInt(JTextField a, String an) {
		String string = a.getText();
		boolean check = filled(a, an);
		if (check)
			try {
				Integer.parseInt(string);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error: the " + an + " field has incorrect number!",
						"Program message", 0);
				check = false;
			}
		return check;
	}

	public boolean dateFormatChecker(String SDate) {
		try {
			Date date = simpleDateFormat.parse(SDate);
			return true;
		} catch (ParseException ef) {
			return false;
		}
	}

	public boolean goodDate(JTextField a, String an) {
		String string = a.getText();
		boolean check = filled(a, an);
		if (check && dateFormatChecker(string)) {
			return true;
		} else {

			JOptionPane.showMessageDialog(null, "The" + an + " field has the wrong date format!", "Program message", 0);
			return false;
		}
	}

	public boolean filled(JTextField a) {
		String string = a.getText();
		if (string.length() > 0) {
			return true;

		} else {
			return false;
		}
	}

	public int stringToInt(String s) {
		int numberFormat = -1;
		try {
			numberFormat = Integer.valueOf(s);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "stringToInt: " + nfe.getMessage(), "Program message", 0);

		}
		return numberFormat;
	}

	public boolean goodIdentifier(JTextField a, String an) {
		String string = a.getText();
		boolean b = filled(a, an);
		if (b && checkNumber(stringToInt(string))) {

			return true;

		} else {
			JOptionPane.showMessageDialog(null, "Error: " + an + " give  five digit number please!", "Program message",
					0);
			return false;
		}
	}

	public boolean checkNumber(int number) {
		if (number > 0 && number <= 99999)
			return true;
		else
			return false;
	}
}
