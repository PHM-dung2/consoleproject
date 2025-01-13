package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.dto.DodgeDto;
import model.dto.MemberDto;
import model.dto.ShopDto;
import model.dto.ShopMenuDto;
import model.dto.OrderCompleteDto;

public class Dao {
	private String DBURL = "jdbc:mysql://127.0.0.1:3306/dssystem";
	private String DBID = "root";
	private String DBPWD = "1234";

	// 클라우드 디비 접속정보
//	private String DBURL = "jdbc:mysql://140.245.79.114:3306/dssystem";
//	private String DBID = "root";
//	private String DBPWD = "Don3bw3elY84167!!";

	protected Connection conn = null;

	protected Dao() {
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
			String sql = String.format("select mno, mid, mpwd, mname, mphone, mtype from member where mid = '%s'", id);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (!rs.next())
				return null; // 아무것도 조회되지 않았음

			member = new MemberDto();
			member.setMno(rs.getInt("mno"));
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

	// 해당 메뉴를 가진 가맹점 검색
	public ArrayList<ShopDto> searchShop(String menu, int mno) { // mno 는 로그인한 일반 주문회원이다.
		ArrayList<ShopDto> shopList = new ArrayList<>();

		try {
//			메뉴를 가진 가맹점중, 로그인 회원의 주소에서 시,구 가 일치하는 가맹점만 select 하는 쿼리
//			select distinct e.eno, e.ename, e.espot, ea.earoad
//			from entry as e
//		    join menu using (eno)
//		    join entryaddress as ea using (eno)
//		    where 
//				mename like '%버거%' and
//				ea.earoad like (
//					select concat(masi, ' ', masgg, '%') from memberaddress where mno = 5
//		        );			
			String sql = String.format("select distinct e.eno, e.ename, e.espot, ea.earoad from entry as e"
					+ " join menu using (eno)" + " join entryaddress as ea using (eno) where"
					+ " mename like '%%%s%%' and" + " ea.earoad like ("
					+ " select concat(masi, ' ', masgg, '%%') from memberaddress where mno = %d)" + " and etype = 1",
					menu, mno);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int eno = rs.getInt("e.eno");
				String ename = rs.getString("e.ename");
				String espot = rs.getString("e.espot");

				// dodge 리스트를 ArrayList 로 먼저 조회해서 가지고, eno mno 값이 같으면 추가 안하는 로직으로 추가
				boolean flag = false;
				ArrayList<DodgeDto> dodgeList = getDodgeList();
				for (DodgeDto dto : dodgeList) {
					// 기피 신청한 eno 이고, 지금 로그인(일반회원)한 mno 와 기피신청당한 get.getMno() 가 같다면!
					if (eno == dto.getEno() && dto.getMno() == mno) {
						flag = true;
						break;
					}
				}
				if (flag) {
					continue; // true 면 닷지에 해당하므로, 다음 레코드로 이동한다.
				}

				ShopDto dto = new ShopDto(eno, ename, espot);
				shopList.add(dto);
			}
		} catch (SQLException e) {
			System.out.println(">> " + e);
		}

		return shopList;
	}

	private ArrayList<DodgeDto> getDodgeList() {
		ArrayList<DodgeDto> dodgeDtoList = new ArrayList<>();

		String sql = "select eno, mno from dodge";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DodgeDto dto = new DodgeDto(rs.getInt("eno"), rs.getInt("mno"));
				dodgeDtoList.add(dto);
			}
		} catch (SQLException e) {
			System.out.println(">> " + e);
		}

		return dodgeDtoList;
	}

	// 해당 가맹점에서 제공하는 메뉴 검색
	public ArrayList<ShopMenuDto> searchShopMenu(int eno) {
		ArrayList<ShopMenuDto> shopMenuList = new ArrayList<>();

		try {
			// select meno, mename, meprice, eno, concat(ename, " ", espot) as efullname
			// from menu as m join entry as e using(eno) where eno = 1;
			String sql = String.format("select meno, mename, meprice, eno, concat(ename, \" \", espot) as efullname"
					+ " from menu as m join entry as e using(eno)" + " where eno = %d", eno);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				shopMenuList.add(new ShopMenuDto(rs.getInt("meno"), rs.getString("mename"), rs.getInt("meprice"),
						rs.getInt("eno"), rs.getString("efullname")));
			}
		} catch (SQLException e) {
			System.out.println(">> " + e);
		}

		return shopMenuList;
	}

	// mno 가 가진 eno 목록 리턴
	public ArrayList<Integer> selectEno(int mno) {
		ArrayList<Integer> enoList = new ArrayList<>();

		try {
			String sql = String.format("select eno from entry where mno = %d", mno);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enoList.add(rs.getInt("eno"));
			}
		} catch (SQLException e) {
			System.out.println(">> " + e);
		}

		return enoList;
	}

	// 메뉴 주문
	public boolean orderMenu(ShopMenuDto shopMenuDto, int mno) {
		try {
			String orderSql = String.format("insert into orderlist (odate, mno) values (now(), %d)", mno);

			// 오토커밋 비활성화 (트랜잭션으로 2개의 insert 문을 묶어서 커밋하려고 사용함)
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
			int count = ps.executeUpdate();
			if (count < 1) {
				return false;
			}

			// auto_increment 된 주문 번호를 가지고 오기 위한 코드이다. 아래 INSERT 쿼리시 사용된다.
			int lastInsertedId;
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				lastInsertedId = generatedKeys.getInt(1);
			} else {
				System.err.println("generatedKeys() 생성 실패");
				return false;
			}

			String orderDetailSql = String.format("insert into orderdetail (meno, ono) values (%d, %d)",
					shopMenuDto.getMeno(), lastInsertedId);
			PreparedStatement ps1 = conn.prepareStatement(orderDetailSql);
			count = ps1.executeUpdate();
			if (count < 1) {
				return false;
			}

			// 트랜잭션 수동 커밋
			conn.commit();
		} catch (SQLException e) {
			System.err.printf("Query Failed - %s ", e.getMessage());
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				System.err.printf("setAutoCommit(true) failed: ", e.getMessage());
			}
		}

		return true;
	}

	// eno 에 해당하는 mid 리턴하는 메소드
	public String selectMid(int eno) {
		try {
			String sql = String.format("select m.mid from member as m join entry using (mno) where eno = %d", eno);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("m.mid");
			} else {
				System.err.printf("select failed: %s", sql);
			}
		} catch (SQLException e) {
			System.out.println(">> " + e);
		}

		return null;
	}

	// mno 에 해당하는 eno 목록 리턴하는 메소드
	public ArrayList<Integer> selectEnoList(int mno) {
		ArrayList<Integer> enoList = new ArrayList<>();

		try {
			String sql = String.format("select eno from entry where mno = %d", mno);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enoList.add(rs.getInt("eno"));
			}
		} catch (SQLException e) {
			System.out.println(">> " + e);
		}

		return enoList;
	}

	// 주문 완료 목록
	public ArrayList<OrderCompleteDto> getOrderCompleteList(int mno) {
		ArrayList<OrderCompleteDto> orderCompleteList = new ArrayList<>();

		// mno 에 해당하는 eno 목록(해당 회원의 입점목록) 추출
		ArrayList<Integer> enoList = selectEnoList(mno);

		// 해당 회원의 입점 가게들 에서 주문 완료된 목록 추출
		// (주문일자 오름차순: enoList 를 각각 돌기에 order by 적용이 불가능함)
		for (int eno : enoList) {
			try {
//				select me.eno, mem.mid, ol.odate, me.mename, me.meprice, ol.mno, concat(en.ename, " ", en.espot) as entryname from orderlist as ol
//				join orderdetail as od using (ono)
//				join menu as me using (meno)
//				join member as mem using (mno)
//				join entry as en using (eno)
//				where me.eno = 1;
				String sql = String.format(
						"select me.eno, mem.mid, ol.odate, me.mename, me.meprice, ol.mno, concat(en.ename, \" \", en.espot) as entryname"
								+ " from orderlist as ol" + " join orderdetail as od using (ono)" + " join menu as me using (meno)"
								+ " join member as mem using (mno)" + " join entry as en using (eno)" + " where me.eno = %d",
						eno);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Timestamp timestamp = rs.getTimestamp("odate"); // now() 함수 결과값 가져오기
					OrderCompleteDto dto = new OrderCompleteDto(rs.getString("mid"), timestamp.toLocalDateTime(),
							rs.getString("mename"), rs.getInt("meprice"), rs.getInt("mno"), rs.getInt("eno"),
							rs.getString("entryname"));
					orderCompleteList.add(dto);
				}
			} catch (SQLException e) {
				System.out.println(">> " + e);
			}
		}

		return orderCompleteList;
	}
}