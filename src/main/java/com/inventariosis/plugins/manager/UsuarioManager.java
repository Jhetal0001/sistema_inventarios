package com.inventariosis.plugins.manager;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.inventariosis.plugins.models.entity.UsuariosEntity;

public interface UsuarioManager {

	/**
	 * Método que retorna una lista de usuarios registrados
	 * Creado el 9/09/2023 a las 1:01:55 a. m.
	 * @author Jhon Vasquez
	 * @return ResponseEntity<List<UsuariosEntity>>
	 */
	ResponseEntity<List<UsuariosEntity>> getUsuarios();
}
