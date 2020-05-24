package com.msc.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.msc.modelo.Usuario;

@Named
@RequestScoped
public class UsuarioDao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Dao<Usuario> dao;
	
	@PostConstruct
	void init() {
		this.dao = new Dao<Usuario>(this.em, Usuario.class);
	}

	@Inject
	private EntityManager em;
	
	public void adiciona(Usuario t) {
		dao.adiciona(t);
	}

	public void atualiza(Usuario t){
		em.merge(t);
	}

	public void remove(Usuario t) {
		dao.remove(t);
	}

	public Usuario buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public List<Usuario> listaTodosPaginada(int firstResult, int maxResults) {
		return dao.listaTodosPaginada(firstResult, maxResults);
	}
	
	public Usuario buscaPorLogin (String login) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select u from Usuario u ");
		sb.append(" where");
		sb.append("		u.login = :pLogin");
		TypedQuery<Usuario> query = em.createQuery(sb.toString(), Usuario.class);
		
		query.setParameter("pLogin", login);
		
		try {
			return query.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
		
	}
	
	public Usuario buscaUsuarioPelaAutenticacao(Usuario usuario) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select u from Usuario u ");
		jpql.append(" where ");
		jpql.append("       u.login = :pLogin ");
		jpql.append("   and u.senha = :pSenha ");
		
		TypedQuery<Usuario> query = em.createQuery(jpql.toString() , Usuario.class);
		
		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

}
