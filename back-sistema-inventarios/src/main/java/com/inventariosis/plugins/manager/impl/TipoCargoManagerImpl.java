package com.inventariosis.plugins.manager.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inventariosis.plugins.dao.TipoCargoDao;
import com.inventariosis.plugins.manager.TipoCargoManager;
import com.inventariosis.plugins.models.dto.TipoCargoDTO;
import com.inventariosis.plugins.models.entity.TipoCargoEntity;

@Service
public class TipoCargoManagerImpl implements TipoCargoManager {

	@Autowired
	private TipoCargoDao tipoCargoDao;

	/*
	 * 
	 * @see com.inventariosis.plugins.manager.TipoCargoManager#getCargos()
	 */
	@Override
	public ResponseEntity<List<TipoCargoEntity>> getCargos() {
		try {
			List<TipoCargoEntity> usuarios = tipoCargoDao.findCargos();
			return ResponseEntity.ok(usuarios);
		} catch (NoResultException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*
	 * 
	 * @see
	 * com.inventariosis.plugins.manager.TipoCargoManager#setCargo(com.inventariosis
	 * .plugins.models.dto.TipoCargoDTO)
	 */
	@Transactional
	@Override
	public ResponseEntity<?> setCargo(TipoCargoDTO cargo) {
		TipoCargoEntity tipoCargo = new TipoCargoEntity();
		tipoCargo.setNombreCargo(cargo.getNombreCargo());
		tipoCargoDao.registrarCargo(tipoCargo);
		return this.getCargos();
	}

}
