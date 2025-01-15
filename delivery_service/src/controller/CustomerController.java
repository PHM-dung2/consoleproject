package controller;

import java.util.ArrayList;

import model.dao.Dao;
import model.dto.ShopDto;
import model.dto.ShopMenuDto;

public class CustomerController {
	private CustomerController() {
	}

	private static CustomerController instance = new CustomerController();

	public static CustomerController getInstance() {
		return instance;
	}

	public ArrayList<ShopDto> searchShop(String menu, int mno) {
		return Dao.getInstance().searchShop(menu, mno);
	}

	public ArrayList<ShopMenuDto> searchShopMenu(int eno) {
		return Dao.getInstance().searchShopMenu(eno);
	}

	public ArrayList<Integer> selectEno(int mno) {
		return Dao.getInstance().selectEno(mno);
	}

	public boolean orderMenu(ShopMenuDto shopMenuDto, int mno) {
		return Dao.getInstance().orderMenu(shopMenuDto, mno);
	}
	
	public String selectMid(int eno) {
		return Dao.getInstance().selectMid(eno);
	}
}
