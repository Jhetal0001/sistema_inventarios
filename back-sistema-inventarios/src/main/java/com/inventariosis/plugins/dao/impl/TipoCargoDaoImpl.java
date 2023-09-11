package com.inventariosis.plugins.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.inventariosis.plugins.dao.TipoCargoDao;
import com.inventariosis.plugins.models.entity.TipoCargoEntity;
import com.inventariosis.plugins.util.Constantes;

@Repository
public class TipoCargoDaoImpl implements TipoCargoDao {

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * 
	 * @see com.inventariosis.plugins.dao.TipoCargoDao#findCargoById(java.lang.Long)
	 */
	@Override
	public TipoCargoEntity findCargoById(Long idTipoCargo) {
		TipoCargoEntity TipoCargo = entityManager.find(TipoCargoEntity.class, idTipoCargo);
		if (TipoCargo == null) {
			System.out.println("No se encontró ningún producto con ID " + TipoCargo);
		}
		return TipoCargo;
	}

	/*
	 * 
	 * @see com.inventariosis.plugins.dao.TipoCargoDao#findCargos()
	 */
	@Override
	public List<TipoCargoEntity> findCargos() {

		String jpqlQuery = Constantes.QUERYS.get("GET_CARGOS");
		TypedQuery<TipoCargoEntity> query = entityManager.createQuery(jpqlQuery, TipoCargoEntity.class);
		return query.getResultList();
	}

	/*
	 * 
	 * @see
	 * com.inventariosis.plugins.dao.TipoCargoDao#registrarCargo(com.inventariosis.
	 * plugins.models.entity.TipoCargoEntity)
	 */
	@Override
	public ResponseEntity<?> registrarCargo(TipoCargoEntity cargo) {
		try {
			entityManager.persist(cargo);
			return ResponseEntity.ok("Registro Exitoso");
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			String mensajeError = "Ya existe un cargo con el mismo nombre.";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
		}
	}
}
