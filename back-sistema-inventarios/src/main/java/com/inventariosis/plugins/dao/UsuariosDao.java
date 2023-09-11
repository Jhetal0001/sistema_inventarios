package com.inventariosis.plugins.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.inventariosis.plugins.models.entity.UsuariosEntity;

public interface UsuariosDao {

	/**
	 * 
	 * Método que permite consultar un registro en Usuarios por su ID Creado el
	 * 9/09/2023 a las 1:00:01 a. m.
	 * 
	 * @author Jhon Vasquez
	 * @return List<UsuariosEntity
	 */
	List<UsuariosEntity> findUsuarios();

	/**
	 * 
	 * Método que permite traer un usuario por id Creado el 9/09/2023 a las 3:07:07
	 * a. m.
	 * 
	 * @author Jhon Vasquez
	 * @param id
	 * @return
	 */
	UsuariosEntity findUsuarioById(Long id);

	/**
	 * Metodo que permite registrar un nuevo usuario Creado el 10/09/2023 a las
	 * 7:17:45 p. m.
	 * 
	 * @author Jhon Vasquez
	 * @param usuario
	 * @return
	 */
	ResponseEntity<?> registrarUsuario(UsuariosEntity usuario);

}
