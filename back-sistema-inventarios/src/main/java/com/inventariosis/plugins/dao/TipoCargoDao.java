package com.inventariosis.plugins.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.inventariosis.plugins.models.entity.TipoCargoEntity;

public interface TipoCargoDao {

	/**
	 * Método que permite consultar un registro en TipoCargo por su ID Creado el
	 * 9/09/2023 a las 12:59:18 a. m.
	 * 
	 * @author Jhon Vasquez
	 * @param idTipoCargo
	 * @return TipoCargoEntity
	 */
	TipoCargoEntity findCargoById(Long idTipoCargo);

	/**
	 * 
	 * Método que permite traer todos los cargos registrados Creado el 10/09/2023 a
	 * las 6:54:21 p. m.
	 * 
	 * @author Jhon Vasquez
	 * @return
	 */
	List<TipoCargoEntity> findCargos();

	/**
	 * Método que permite registrar un cargo nuevo Creado el 10/09/2023 a las
	 * 7:25:31 p. m.
	 * 
	 * @author Jhon Vasquez
	 * @param cargo
	 * @return
	 */
	ResponseEntity<?> registrarCargo(TipoCargoEntity cargo);

}
