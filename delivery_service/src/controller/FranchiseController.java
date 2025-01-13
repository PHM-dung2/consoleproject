package controller;

import java.util.ArrayList;

import model.dao.Dao;
import model.dto.OrderCompleteDto;

public class FranchiseController {
	private static FranchiseController instance = new FranchiseController();

	private FranchiseController() {
	}

	public static FranchiseController getInstance() {
		return instance;
	}

	public ArrayList<OrderCompleteDto> getOrderCompleteList(int mno) {
		return Dao.getInstance().getOrderCompleteList(mno);
	}

	public boolean insertStarPoint(int starPoint, OrderCompleteDto dto) {
		String sql = String.format("insert into rating (rrating, mno) values (%d, %d)", starPoint, dto.getOrderMno());

		return Dao.getInstance().execute(sql);
	}
	
	public boolean insertDodgeMember(OrderCompleteDto dto) {
		String sql = String.format("insert into dodge (eno, mno) values (%d, %d)", dto.getOrderEno(), dto.getOrderMno());

		return Dao.getInstance().execute(sql);
	}
}
