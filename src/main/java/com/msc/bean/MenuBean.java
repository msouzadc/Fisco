package com.msc.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class MenuBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public String goUf() {
		return  "/view/login/index.xhtml";
	}
}
