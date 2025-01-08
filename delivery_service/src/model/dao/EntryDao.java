package model.dao;

public class EntryDao {
	private EntryDao() {}
	private static EntryDao instance = new EntryDao();
	public static EntryDao getInstance () {
		return instance;
	}
	
	
}
