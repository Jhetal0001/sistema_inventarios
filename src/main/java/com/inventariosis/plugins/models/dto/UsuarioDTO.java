package com.inventariosis.plugins.models.dto;

import java.io.Serializable;
import java.util.Date;

public class UsuarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4372571801631175880L;

	private Long id;
	private String nombre;
	private Long edad;
	private TipoCargoDTO tipoCargo;
	private Date fechaIngreso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getEdad() {
		return edad;
	}

	public void setEdad(Long edad) {
		this.edad = edad;
	}

	public TipoCargoDTO getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(TipoCargoDTO tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

}
