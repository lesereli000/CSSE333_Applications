package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import ui.TestUI;

public class Main {

	public static void main(String[] args) {
		Connect connect = new Connect();
		connect.connect();
		System.out.println("hello");
		connect.close();
		TestUI ui = new TestUI();
	}

}
