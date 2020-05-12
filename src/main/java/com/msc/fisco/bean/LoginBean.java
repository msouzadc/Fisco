package com.msc.fisco.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.msc.dao.UsuarioDao;
import com.msc.fisco.modelo.Usuario;
import com.msc.fisco.tx.Transacional;

@Named
@ViewScoped
public class LoginBean implements Serializable {
	private static final String USUARIO_LOGADO = "usuarioLogado";

	private static final long serialVersionUID = 1L;

	@Inject
	private HttpSession session;
	@Inject
	private UsuarioDao usuarioDao;
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	@PostConstruct
	public void init() {
		System.out.println("LoginBean.init();");
		usuario = (Usuario) session.getAttribute(USUARIO_LOGADO);
		if (usuario == null) {
			usuario = new Usuario();
		}
	}
	
	@Transacional
	public String loga() {
		Usuario usuarioAutenticado  = usuarioDao.buscaUsuarioPelaAutenticacao(this.usuario);
		if (usuarioAutenticado != null) {
			usuarioDao.atualiza(usuarioAutenticado);
			session.setAttribute(USUARIO_LOGADO, usuarioAutenticado);
			usuario = usuarioAutenticado;
			return "/view/menu/menu.xhtml?faces-redirect=true";
		}
		String mensagem = "Usuário e/ou senha não inválido!";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", mensagem));
		return null;
	}

	public String desloga() {
		session.removeAttribute(USUARIO_LOGADO);
		session.invalidate();
		usuario = new Usuario();
		return "/index.xhtml?faces-redirect=true";
	}
}
