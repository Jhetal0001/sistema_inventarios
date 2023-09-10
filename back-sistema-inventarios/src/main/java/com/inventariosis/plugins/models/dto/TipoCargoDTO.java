package com.inventariosis.plugins.models.dto;

import java.io.Serializable;

public class TipoCargoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2969346441124040251L;

	private Long id;
	private String nombreCargo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCargo() {
		return nombreCargo;
	}

	public void setNombreCargo(String nombre) {
		this.nombreCargo = nombre;
	}

}
