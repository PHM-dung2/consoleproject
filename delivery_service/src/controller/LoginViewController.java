package controller;

import model.dao.Dao;
import model.dto.MemberDto;
import util.DSCrypto;

public class LoginViewController {
	private LoginViewController() {
	}

	private static LoginViewController loginViewController = new LoginViewController();

	public static LoginViewController getInstance() {
		return loginViewController;
	}

	public MemberDto login(String id, String password) {
		return Dao.getInstance().login(id, password);
	}
}
