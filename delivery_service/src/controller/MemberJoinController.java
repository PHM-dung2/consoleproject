package controller;

import model.dao.Dao;
import model.dto.MemberDto;

public class MemberJoinController {
	private MemberJoinController() {}
	
	private static MemberJoinController memberJoinController = new MemberJoinController();
	
	public static MemberJoinController getInstance() {
		return memberJoinController;
	}
	
	public boolean join(MemberDto member) {
		// insert 쿼리 생성
		String sql = String.format("insert into 회원 (아이디, 비밀번호, 이름, 전화번호, 주소, 타입) values ('%s', '%s', '%s', '%s', '%s', %d)"
				, member.getId(), member.getPassword(), member.getName(), member.getTelno(), member.getAddress(), member.getType());
		// Dao 싱글턴 DB 객체 호출하여 쿼리 실행
		return Dao.getInstance().execute(sql);
	}
}
