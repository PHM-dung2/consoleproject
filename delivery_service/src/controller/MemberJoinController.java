package controller;

import model.dao.Dao;
import model.dto.MemberDto;

public class MemberJoinController {
	private MemberJoinController() {
	}

	private static MemberJoinController memberJoinController = new MemberJoinController();

	public static MemberJoinController getInstance() {
		return memberJoinController;
	}

	public boolean checkID(String id) {
		return Dao.getInstance().selectCheckId(id);
	}

	// TODO: 트랜잭션으로 도로명주소, 멤버 insert 쿼리 둘다 묶어서 처리. 다 성공하거나 or 다 실패하거나 해야되기 떄문이다.
	public boolean join(MemberDto member) {
		// 멤버 테이블에 insert
		String sql = String.format(
				"insert into member (mid, mpwd, mname, mphone, mtype) values ('%s', '%s', '%s', '%s', %d)",
				member.getId(), member.getPassword(), member.getName(), member.getTelno(), member.getType());
		// 트랜잭션없이 그냥 처리...
		Dao.getInstance().execute(sql);
		
		int mno = Dao.getInstance().selectMno(member.getId());
		if (mno < 0) {
			System.err.printf("Query Failed: %s\n", sql);
			return false;
		}
		
		// 도로명주소 테이블에 insert
		sql = String.format(
				"insert into memberaddress (mazipcode, maroad, mastreet, madetail, masi, masgg, mno) values ('%s', '%s', '%s', '%s', '%s', '%s', '%d')",
				member.getRoadAddressDto().getZipCode(), member.getRoadAddressDto().getRoadAddress(),
				member.getRoadAddressDto().getJibunAddress(), member.getRoadAddressDto().getDetailAddress(), 
				member.getRoadAddressDto().getSi(), member.getRoadAddressDto().getSgg(), mno);

		return Dao.getInstance().execute(sql); 
	}
}
