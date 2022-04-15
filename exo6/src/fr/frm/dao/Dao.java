package fr.frm.dao;

import java.sql.Connection;
import java.util.List;

public interface Dao<T> {
	public BddConnection bddConnection = BddConnection.getInstance();
	public Connection connection = bddConnection.getConnection();
	List<T> readAllObjs();
	void createObj(T t);
	void updateObj(T t);
	void deleteObj(int id);
	T getObjById(int id);
	//boolean validateObjLogin(T t);
	void loadDatabase();
}
