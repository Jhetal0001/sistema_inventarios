package com.inventariosis.plugins.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.inventariosis.plugins.dao.TipoCargoDao;
import com.inventariosis.plugins.models.entity.TipoCargoEntity;

@Repository
public class TipoCargoDaoImpl implements TipoCargoDao {

	private EntityManager entityManager;

	/*
	 * 
	 * @see com.inventariosis.plugins.dao.TipoCargoDao#findCargoById(java.lang.Long)
	 */
	@Override
	public TipoCargoEntity findCargoById(Long idTipoCargo) {
		try {
			return entityManager.find(TipoCargoEntity.class, idTipoCargo);			
		} catch(NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}
}
