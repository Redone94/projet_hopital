package dao;

import java.util.List;

import model.Patient;

public interface IDAO<T,K> {
	String lien="jdbc:mysql://localhost:3306/";
	String database="hopital";
	String login="root";
	String password="";
	
	public void ajouter(T t);
	public void modifier(T t);
	public void supprimer(K id);
	public T selectById(K id);
	public List<T> selectAll();
}
