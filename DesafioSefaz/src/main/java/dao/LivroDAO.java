package dao;

import java.util.List;

import entidades.Livro;

public interface LivroDAO {
	
	public void inserir(Livro livro);
	
	public void alterar(Livro livro);
	
	public void remover(Livro livro);
	
	public Livro pesquisar(String titulo);
	
	public List<Livro> listarLivros();

}
