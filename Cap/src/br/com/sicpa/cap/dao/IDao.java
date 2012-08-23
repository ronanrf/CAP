package br.com.sicpa.cap.dao;

import java.util.List;

public interface IDao<T> {
	
	public void salvar(T usuario);
	
	public void upadte(T obj );
	
	public List<T> findAll();
	
	public Object findById(Integer id);

}
