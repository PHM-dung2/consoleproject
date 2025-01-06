package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {
	private String DBURL = "jdbc:mysql://140.245.79.114:3306/dssystem";
	private String DBID = "root";
	private String DBPWD = "1234";
 
	private Connection conn = null;

	private Dao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DBURL, DBID, DBPWD);			
		} catch (ClassNotFoundException e) {
			System.err.println(">> JDBC 드라이버가 없습니다. " + e);
		} catch (SQLException e) {
			System.err.println(">> DB연동 실패 " + e);
		}
	}

	private static Dao instance = new Dao();

	public static Dao getInstance() {
		return instance;
	}
	
	// 정상적으로 DB 가 열렸는지 확인하는 메소드
	public boolean isOK() {		
		return conn == null ? false : true;
	}
	
	public String select() {
		String s = null;
		
		try {			
			String sql = "select * from user where uno=1";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			
			while ( rs.next() ) {
				System.out.printf("%d \t %s \t %s \n", 
						rs.getInt("uno"), rs.getString("uname"), rs.getInt("uage"));
				s = rs.getString("uname");
				System.out.printf("dao - %s : %d\n", s, System.identityHashCode(s));
			}			
		} catch (SQLException e) {
			System.out.println(">> " + e);
		}
		
		return s;
	}

	// insert, update, delete 쿼리 실행 메소드
	public boolean execute(String sql) {
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			int count = ps.executeUpdate();
			if ( count >= 1 ) {				
				return true;
			}
		} catch (SQLException e) {
			System.err.printf("Query Failed - %s >> %s", sql, e.getMessage());
		}		
		
		return false;
	}
}