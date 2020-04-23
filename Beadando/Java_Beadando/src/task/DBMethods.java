package task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;

import javax.swing.JOptionPane;

public class DBMethods {
	private Statement statement = null;
	private Connection connection = null;
	private ResultSet resultSet = null;

	public void reg() {
		try {
			Class.forName("org.sqlite.JDBC");
			JOptionPane.showMessageDialog(null, "Successful driver registration!", "Program message", 1);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Unsuccessful driver registration!", "Program message", 0);

		}
	}

	public void connect() {
		try {
			String url = "jdbc:sqlite:D:/targyak/java/SQLite/dramadb";
			connection = DriverManager.getConnection(url);
			JOptionPane.showMessageDialog(null, "Successful connected!", "Program message", 1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Connection failed! Error: " + e.getMessage(), " Program message", 0);

		}
	}

	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
				JOptionPane.showMessageDialog(null, "successful disconnected", "Program message", 1);

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "disconnected failed! Error: " + e.getMessage(), " Program message",
						0);

			}
		}
	}

	public DramaTableDesign readAllData(String string) {
		Object dramaObject[] = { "Signal", "Identifier", "Title", "Director", "Performance date", "Ticket price" };
		DramaTableDesign drama = new DramaTableDesign(dramaObject, 0);
		String title = "", director = "", performanceDate = "";
		int identifier = 0, ticketPrice = 0;
		String sqlCommand = "select identifier,title,director,performanceDate,ticketPrice from drama";
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlCommand);
			while (resultSet.next()) {
				identifier = resultSet.getInt("identifier");
				title = resultSet.getString("title");
				director = resultSet.getString("director");
				performanceDate = resultSet.getString("performancedate");
				ticketPrice = resultSet.getInt("ticketprice");
				drama.addRow(new Object[] { false, identifier, title, director, performanceDate, ticketPrice });
			}
			resultSet.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), " Program message", 0);

		}
		return drama;
	}

	public void insert(String identifier, String title, String director, String performanceDate, String ticketPrice) {
		String sqlCommand = "insert into drama values(" + identifier + ",'" + title + "','" + director + "','"
				+ performanceDate + "'," + ticketPrice + ")";
		try {
			statement = connection.createStatement();
			statement.execute(sqlCommand);
			JOptionPane.showMessageDialog(null, "insert successful!", "Program message", 1);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "insert unsuccessful! : " + e.getMessage(), " Program message", 0);

		}
	}

	public void deleteData(String identifier) {
		String sqlCommand = "delete from drama where identifier=" + identifier;
		try {
			statement = connection.createStatement();
			statement.execute(sqlCommand);
			JOptionPane.showMessageDialog(null, "Delete successful", "Program message", 1);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Delete failed! " + e.getMessage(), " Program message", 0);

		}
	}

	public void update(String identifier, String mName, String mData) {
		String sqlCommand = "update drama set " + mName + "='" + mData + "'where identifier=" + identifier;
		try {
			statement = connection.createStatement();
			statement.execute(sqlCommand);
			JOptionPane.showMessageDialog(null, "Update successful!", "Program message", 1);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "JDBC Update: " + e.getMessage(), " Program message", 0);

		}
	}

	public void write(String string, DramaTableDesign drama) {
		try {
			Class.forName("org.sqlite.JDBC");

			JOptionPane.showMessageDialog(null, "Successful driver registration!", "Program message", 1);
			String url = "jdbc:sqlite:D:/targyak/java/SQLite/dramadb";
			connection = DriverManager.getConnection(url);
			JOptionPane.showMessageDialog(null, "Successful connected!", "Program message", 1);
			statement = connection.createStatement();
			String sql = "CREATE TABLE '" + string + "'(\n" + " identifier integer primary key,\n " + " title text,\n "
					+ " director text, \n" + " performanceDate date,\n " + " ticketPrice integer);";

			statement.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Table created successful!", "Program message", 1);
			int getRowCount = drama.getRowCount();
			int getColumnCount = drama.getColumnCount();
			for (int i = 0; i < getRowCount; i++) {
				for (int j = 1; j < getColumnCount - 1; j += 5) {
					String identifier = drama.getValueAt(i, 1).toString();
					String title = drama.getValueAt(i, 2).toString();
					String director = drama.getValueAt(i, 3).toString();
					String performanceDate = drama.getValueAt(i, 4).toString();
					String ticketPrice = drama.getValueAt(i, 5).toString();
					String sqlCommand = "insert into '" + string + "' values(" + identifier + ", '" + title + "', '"
							+ director + "', '" + performanceDate + "', " + ticketPrice + ")";
					statement = connection.createStatement();
					statement.execute(sqlCommand);
				}
			}
			JOptionPane.showMessageDialog(null, "Table completed successfully!", "Program message", 1);

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					connection.close();
			} catch (SQLException se) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

}
