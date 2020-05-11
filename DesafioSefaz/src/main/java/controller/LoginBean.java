package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import entidades.Usuario;

@ManagedBean(name = "LoginBean")
@SessionScoped
public class LoginBean {
	private String txtEmail;
	private String txtSenha;
	private List<Usuario> listaUsuarios;
	private Usuario usuario;
	private UsuarioDAO usuarioDAO;

	public LoginBean() {
		this.usuario = new Usuario();
		this.listaUsuarios = new ArrayList<Usuario>();
		this.usuarioDAO = new UsuarioDAOImpl();
	}

	public String entrar() {
		boolean achou = false;
		boolean vazio = false;
		
		this.listaUsuarios = this.usuarioDAO.listarUsuarios();

		for (Usuario usuarioPesquisa : listaUsuarios) {
			if (usuarioPesquisa.getEmail().equals(this.txtEmail) && usuarioPesquisa.getSenha().equals(this.txtSenha)) {
				achou = true;
			}
		}

		if (this.txtEmail.equals("") || this.txtSenha.equals("")) {
			vazio = true;
		}

		if(vazio) {
				FacesContext.getCurrentInstance().addMessage
				(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Preencha os Campos do Login"));
				return "login.xhtml";
		} else {
			if(achou) {
				return "pagPrincipal.xhtml";
			} else {
				FacesContext.getCurrentInstance().addMessage
				(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Usuário inválido"));
				return "login.xhtml";
			}
		}
	}

	public void criarUsuario() {
		Usuario novo = new Usuario();
		novo.setNome(this.usuario.getNome());
		novo.setEmail(this.usuario.getEmail());
		novo.setTelefone(this.usuario.getTelefone());
		novo.setSenha(this.usuario.getSenha());

		boolean achou = false;
		boolean vazio = false;
		
		this.listaUsuarios = this.usuarioDAO.listarUsuarios();

		for (Usuario listaPesquisa : listaUsuarios) {
			if (listaPesquisa.getEmail().equals(this.usuario.getEmail())
					|| listaPesquisa.getSenha().equals(this.usuario.getSenha())) {
				achou = true;
			}
		}

		if (this.usuario.getNome().isEmpty() || this.usuario.getEmail().isEmpty()
				|| this.usuario.getSenha().isEmpty() || this.usuario.getSenha().isEmpty()) {
			vazio = true;
		}

		if(vazio) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Preencha todos os campos do Cadrastro"));
		} else {
			if(achou) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Email ou Senha já Existente"));
			} else {
				//this.listaUsuarios.add(novo);
				this.usuarioDAO.inserir(novo);
				this.usuario = new Usuario();
			}
			
			
		}

	}

	public String getTxtSenha() {
		return txtSenha;
	}

	public void setTxtSenha(String txtSenha) {
		this.txtSenha = txtSenha;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(String txtEmail) {
		this.txtEmail = txtEmail;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	

}
