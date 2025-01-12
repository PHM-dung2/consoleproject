package controller;

import java.util.ArrayList;

import model.dao.Dao;
import model.dto.OrderCompleteDto;

public class FranchiseController {	
	private static FranchiseController instance = new FranchiseController();
	private FranchiseController() {}
	public static FranchiseController getInstance() {
		return instance;
	}
	
	public ArrayList<OrderCompleteDto> getOrderCompleteList(int mno) {
		return Dao.getInstance().getOrderCompleteList(mno);
	}
}
