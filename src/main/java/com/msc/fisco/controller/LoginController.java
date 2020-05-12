package com.msc.fisco.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class LoginController  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String senha;
	
	public String goMenu() {
		System.out.println(login);
		System.out.println(senha);
		return "/index.xhtml";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
