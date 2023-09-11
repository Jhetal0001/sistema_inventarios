package com.inventariosis.plugins.manager;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.inventariosis.plugins.models.dto.UsuarioDTO;
import com.inventariosis.plugins.models.entity.UsuariosEntity;

public interface UsuarioManager {

	/**
	 * Método que retorna una lista de usuarios registrados Creado el 9/09/2023 a
	 * las 1:01:55 a. m.
	 * 
	 * @author Jhon Vasquez
	 * @return ResponseEntity<List<UsuariosEntity>>
	 */
	ResponseEntity<List<UsuariosEntity>> getUsuarios();

	/**
	 * Servicio que permite registrar un nuevo usuario Creado el 10/09/2023 a las
	 * 7:13:59 p. m.
	 * 
	 * @author Jhon Vasquez
	 * @param usuarioDTO
	 * @return
	 */
	ResponseEntity<?> setUsuario(UsuarioDTO usuarioDTO);
}
