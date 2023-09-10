package com.inventariosis.plugins.dao;

import com.inventariosis.plugins.models.entity.TipoCargoEntity;

public interface TipoCargoDao {
	
	/**
	 * Método que permite consultar un registro en TipoCargo por su ID
	 * Creado el 9/09/2023 a las 12:59:18 a. m.
	 * @author Jhon Vasquez
	 * @param idTipoCargo
	 * @return TipoCargoEntity
	 */
	TipoCargoEntity findCargoById(Long idTipoCargo);

}
