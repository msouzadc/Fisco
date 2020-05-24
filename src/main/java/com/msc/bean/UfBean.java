package com.msc.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class UfBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String sigla;
	private String nome;
	
	public UfBean() {
		System.out.println("Criando controller");
	}
	public String goMenu() {
		System.out.println(sigla);
		System.out.println(nome);
		return "/index.xhtml";
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
