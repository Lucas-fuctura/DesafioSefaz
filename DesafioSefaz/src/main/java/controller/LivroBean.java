package controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.LivroDAO;
import dao.LivroDAOImpl;
import entidades.Livro;

@ManagedBean(name="LivroBean")
@SessionScoped
public class LivroBean {
	private List<Livro> listadeLivros;
	private Livro livro;
	private String novoTitulo;
	private String novoAutor;
	private String novoGenero;
	private String novoEditor;
	private String txtpesquisa;
	private LivroDAO livroDAO;
	
	
	public LivroBean() {
		this.livro = new Livro();
		this.listadeLivros = new ArrayList<Livro>();
		this.livroDAO = new LivroDAOImpl();
		}
	
	public void novoLivro() {
		Livro novo = new Livro();
		novo.setTitulo(this.livro.getTitulo());
		novo.setAutor(this.livro.getAutor());
		novo.setGenero(this.livro.getGenero());
		novo.setEditor(this.livro.getEditor());
		
		boolean achou = false;
		boolean vazio = false;
		
		this.listadeLivros = this.livroDAO.listarLivros();
		
		for(Livro livroPesquisa: listadeLivros) {
			if(livroPesquisa.getTitulo().equals(this.livro.getTitulo())) {
				achou = true;
			}
		}
		
		if(this.livro.getTitulo().isEmpty() || this.livro.getAutor().isEmpty()
				|| this.livro.getGenero().isEmpty()	 || this.livro.getEditor().isEmpty()) {
			vazio = true;
		}
		
		if(vazio) {
			FacesContext.getCurrentInstance().addMessage(null,
			new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Preencha todos os campos Obrigatórios"));
		} else {
			if(achou) {
				FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Livro já Existente"));
			} else {
				//this.listadeLivros.add(novo);
				this.livroDAO.inserir(novo);
				this.livro = new Livro();
			}
		}
	}
	
	public List<Livro> listarLivros(){
		return this.listadeLivros;
	}
	
	public void pesquisa() {
		this.listadeLivros = this.livroDAO.listarLivros();
		
		for(Livro pesquisarLivro: listadeLivros) {
			if(pesquisarLivro.getTitulo().equals(this.txtpesquisa)) {
				this.livroDAO.pesquisar(this.txtpesquisa);
			}
		}
	}
	
	public void edicao() {
		
	}
	
	public void tabeladeLivros() {
		
	}
	
	public String sair() {
		return "login.xhtml";
	}
		
	public List<Livro> getListadeLivros() {
		return listadeLivros;
	}
	public void setListadeLivros(List<Livro> listadeLivros) {
		this.listadeLivros = listadeLivros;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public String getNovoTitulo() {
		return novoTitulo;
	}

	public void setNovoTitulo(String novoTitulo) {
		this.novoTitulo = novoTitulo;
	}

	public String getNovoAutor() {
		return novoAutor;
	}

	public void setNovoAutor(String novoAutor) {
		this.novoAutor = novoAutor;
	}

	public String getNovoGenero() {
		return novoGenero;
	}

	public void setNovoGenero(String novoGenero) {
		this.novoGenero = novoGenero;
	}

	public String getNovoEditor() {
		return novoEditor;
	}

	public void setNovoEditor(String novoEditor) {
		this.novoEditor = novoEditor;
	}

	public String getTxtpesquisa() {
		return txtpesquisa;
	}

	public void setTxtpesquisa(String txtpesquisa) {
		this.txtpesquisa = txtpesquisa;
	}
	
	 public LivroDAO getLivroDAO() {
		return livroDAO;
	}

	public void setLivroDAO(LivroDAO livroDAO) {
		this.livroDAO = livroDAO;
	}

}
