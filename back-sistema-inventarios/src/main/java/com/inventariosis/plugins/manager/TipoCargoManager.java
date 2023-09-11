package com.inventariosis.plugins.manager;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.inventariosis.plugins.models.dto.TipoCargoDTO;
import com.inventariosis.plugins.models.entity.TipoCargoEntity;

public interface TipoCargoManager {

	/**
	 * Servicio que permite traer todos los cargos registrados Creado el 10/09/2023
	 * a las 7:00:11 p. m.
	 * 
	 * @author Jhon Vasquez
	 * @return
	 */
	ResponseEntity<List<TipoCargoEntity>> getCargos();

	/**
	 * Servicio que permite registrar un cargo nuevo Creado el 10/09/2023 a las
	 * 7:26:55 p. m.
	 * 
	 * @author Jhon Vasquez
	 * @param cargo
	 * @return
	 */
	ResponseEntity<?> setCargo(TipoCargoDTO cargo);

}
