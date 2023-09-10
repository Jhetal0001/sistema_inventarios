package com.inventariosis.plugins.models.dto;

import java.io.Serializable;
import java.util.Date;

public class ProductoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1660809546371920007L;

	private Long id;
	private String nombreProducto;
	private Long cantidad;
	private Date fechaIngreso;
	private UsuarioDTO usuarioRegistra;
	private UsuarioDTO usuarioModifica;
	private Date fechaModifica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public UsuarioDTO getUsuarioRegistra() {
		return usuarioRegistra;
	}

	public void setUsuarioRegistra(UsuarioDTO usuarioRegistra) {
		this.usuarioRegistra = usuarioRegistra;
	}

	public UsuarioDTO getUsuarioModifica() {
		return usuarioModifica;
	}

	public void setUsuarioModifica(UsuarioDTO usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}

	public Date getFechaModifica() {
		return fechaModifica;
	}

	public void setFechaModifica(Date fechaModifica) {
		this.fechaModifica = fechaModifica;
	}

}
