package com.msc.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.msc.modelo.Usuario;

@Named
@ViewScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	@PostConstruct
	public void init() {
		this.usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public String goMenu() {
		return "/view/menu/menu.xhtml";
	}
	
	public String desloga() {
		usuario = new Usuario();
		return "/view/login/login.xhtml?faces-redirect=true";
	}
	
}
