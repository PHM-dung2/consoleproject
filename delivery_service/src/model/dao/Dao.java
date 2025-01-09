package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.MemberDto;

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

	// insert, update, delete 쿼리 실행 메소드
	public boolean execute(String sql) {
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			int count = ps.executeUpdate();
			if (count >= 1) {
				return true;
			}
		} catch (SQLException e) {
			System.err.printf("Query Failed - %s >> %s", sql, e.getMessage());
		}

		return false;
	}

	/*
	 * 아래부터는 select 메소드 목록
	 */
	
	// 회원번호 검색
	public int selectMno(String id) {
		try {
			String sql = String.format("select mno from member where mid = '%s'", id);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();			
			if (!rs.next())
				return -1; // 아무것도 조회되지 않았음
			return rs.getInt("mno");
		} catch (SQLException e) {
			System.out.println(">> " + e);
		}

		return -1;
	}
	
	// 아이디 중복 체크
	public boolean selectCheckId(String id) {
		boolean result = false;
		
		try {
			String sql = String.format("select mid from member where mid = '%s'", id);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			System.out.println(">> " + e);
		}

		return result;
	}

	// 회원정보(1명) 검색
	public MemberDto selectMember(String id) {
		MemberDto member = null;
		
		try {
			String sql = String.format("select mid, mpwd, mname, mphone, mtype from member where mid = '%s'", id);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (!rs.next())
				return null; // 아무것도 조회되지 않았음

			member = new MemberDto();
			member.setId(rs.getString("mid"));
			member.setPassword(rs.getString("mpwd"));
			member.setName(rs.getString("mname"));
			member.setTelno(rs.getString("mphone"));			
			member.setType(rs.getInt("mtype"));
		} catch (SQLException e) {
			System.out.println(">> " + e);
		}

		return member;
	}
	
	
}