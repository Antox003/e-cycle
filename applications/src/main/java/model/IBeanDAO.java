package model;

import java.sql.SQLException;
import java.util.List;

public interface IBeanDAO<T> {
	
	public void doSave(T bean) throws SQLException;

	public boolean doDelete(int code) throws SQLException;

	public T doRetrieveByKey(int code) throws SQLException;
	
	public List<T> doRetrieveAll(String order) throws SQLException;
}
