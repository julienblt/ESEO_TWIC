package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOFactory {
	public static Statement connection() {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/maven?user=root&password=&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC");
			Statement stm = connect.createStatement();
			return stm;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
