package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidades.Livro;
import util.JpaUtil;

public class LivroDAOImpl implements LivroDAO {
	
	public void inserir(Livro livro) {
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();
		tx.begin();
		
		ent.persist(livro);
		
		tx.commit();
		ent.close();
	}
	
	public void alterar(Livro livro) {
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();
		tx.begin();
		
		ent.merge(livro);
		
		tx.commit();
		ent.close();
	}
	
	public void remover(Livro livro) {
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();
		tx.begin();
		
		ent.remove(livro);
		
		tx.commit();
		ent.close();
	}
	
	public Livro pesquisar(String titulo) {
		EntityManager ent = JpaUtil.getEntityManager();
		Livro livros = ent.find(Livro.class, titulo);
		return livros;
	}
	
	public List<Livro> listarLivros(){
		EntityManager ent = JpaUtil.getEntityManager();
		Query query = ent.createQuery("from Livros l");
		List<Livro> livros = query.getResultList();
		return livros;
	}
	

}
