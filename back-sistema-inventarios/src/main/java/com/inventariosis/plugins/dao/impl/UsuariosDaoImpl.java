package com.inventariosis.plugins.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.inventariosis.plugins.dao.UsuariosDao;
import com.inventariosis.plugins.models.entity.UsuariosEntity;
import com.inventariosis.plugins.util.Constantes;

@Repository
public class UsuariosDaoImpl implements UsuariosDao {

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * 
	 * @see com.inventariosis.plugins.dao.UsuariosDao#findUsuarios()
	 */
	@Override
	public List<UsuariosEntity> findUsuarios() {

		String jpqlQuery = Constantes.QUERYS.get("GET_USUARIOS");
		TypedQuery<UsuariosEntity> query = entityManager.createQuery(jpqlQuery, UsuariosEntity.class);
		return query.getResultList();
	}

	/*
	 * 
	 * @see
	 * com.inventariosis.plugins.dao.UsuariosDao#findUsuarioById(java.lang.Long)
	 */
	@Override
	public UsuariosEntity findUsuarioById(Long id) {
		UsuariosEntity usuario = entityManager.find(UsuariosEntity.class, id);
		if (usuario == null) {
			System.out.println("No se encontró ningún usuario con ID " + id);
		}
		return usuario;
	}

}
