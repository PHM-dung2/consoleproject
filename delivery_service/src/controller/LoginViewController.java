package controller;

import model.dao.Dao;
import model.dto.MemberDto;

public class LoginViewController {
		private LoginViewController() {}
		
		private static LoginViewController loginViewController = new LoginViewController();
		
		public static LoginViewController getInstance() {
			return loginViewController;
		}
		
		public MemberDto login(String id, String password) {
			// ID 에 해당하는 회원 정보를 DB 에서 가지고 온다.
			MemberDto member = Dao.getInstance().selectMember(id);
			
			if (member == null) { // ID 가 존재하지 않음
				System.out.printf("없는 ID(%s) 입니다. \n", id); // 디버깅 메시지 
				return null;
			}
			else if (!member.getPassword().equals(password)) {
				System.out.printf("ID(%s) 는 있지만, 패스워드(%s)가 일치하지 않는다.\n", id, password); // 디버깅 메시지
				return null;
			}
			
			return member;
		}
}
