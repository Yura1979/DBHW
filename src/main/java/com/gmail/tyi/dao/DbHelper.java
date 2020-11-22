package com.gmail.tyi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelper {
	
	static final String DB_CONNECTION = "jdbc:mysql://192.168.0.50:3306/flatsDb?serverTimezone=Europe/Kiev";
    static final String DB_USER = "admin";
    static final String DB_PASSWORD = "P@ssw0rd79";
    static Connection conn;
    
    private DbHelper() {
    	
    }
    
    public void getFlatsWithCondition(String condition) {
    	
    	String sql = "select * from Flats" + condition;
    	
		try(PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			for (int i = 1; i <= md.getColumnCount(); i++) {
				System.out.print(md.getColumnName(i) + "\t\t");
			}
			System.out.println();
			while (rs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "\t\t");
                }
                System.out.println();
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public void getFlats() {
    	String condition = "";
		getFlatsWithCondition(condition);
	}
    
    public void addFlat(String region, String address, int squere, int rumsCount, int price) {
    	try(PreparedStatement ps = conn.prepareStatement("insert into Flats (region, address, squere, rumsCount, price) VALUES(?, ?, ?, ?, ?)")) {
			ps.setString(1, region);
			ps.setString(2, address);
			ps.setInt(3, squere);
			ps.setInt(4, rumsCount);
			ps.setInt(5, price);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
    
    public static DbHelper getInstance() {
		DbHelper dbInstance = new DbHelper();
		try {
			conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			init();
			dbInstance.addFlat("Shevchenkivskiy", "Degtyarivska 22, kv. 45", 52, 2, 75000);
			dbInstance.addFlat("Pecherskiy", "Lesy Ukrainki 5, kv. 2", 72, 2, 96000);
			dbInstance.addFlat("Svyatoshinskiy", "Gnata Yury 44, kv. 45", 110, 3, 78000);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dbInstance;
	}
    
    private static void init() throws SQLException {
    	
    	Statement st = conn.createStatement();
        try {
            st.execute("DROP TABLE IF EXISTS Flats");
            st.execute("CREATE TABLE Flats (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, region VARCHAR(20) NOT NULL, address VARCHAR(50) NOT NULL, squere INT NOT NULL, rumsCount INT NOT NULL, price INT NOT NULL)");
        } finally {
            st.close();
        }
		
	}

}
