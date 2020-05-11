package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LIVRO")
public class Livro {
	
	@Id
	@Column(name="TITULO", nullable = false)
	private String titulo;
	
	@Column(name="GENERO", nullable = false)
	private String genero;
	
	@Column(name="AUTOR", nullable = false)
	private String autor;
	
	@Column(name="EDITOR", nullable = false)
	private String editor;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	

}
