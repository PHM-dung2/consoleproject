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
		return Dao.getInstance().join(member);
	}
}
