package com.inventariosis.plugins.manager.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inventariosis.plugins.dao.UsuariosDao;
import com.inventariosis.plugins.manager.UsuarioManager;
import com.inventariosis.plugins.models.entity.UsuariosEntity;

@Service
public class UsuarioManagerImpl implements UsuarioManager {

	@Autowired
	private UsuariosDao usuariosDao;

	/*
	 * 
	 * @see com.inventariosis.plugins.manager.UsuarioManager#getUsuarios()
	 */
	@Override
	public ResponseEntity<List<UsuariosEntity>> getUsuarios() {
		try {
			List<UsuariosEntity> usuarios = usuariosDao.findUsuarios();
			return ResponseEntity.ok(usuarios);
		} catch (NoResultException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
