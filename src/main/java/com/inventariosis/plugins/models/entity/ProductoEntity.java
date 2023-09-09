package com.inventariosis.plugins.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PRODUCTO")
public class ProductoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5542856670557160125L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nombre_producto", nullable = false, unique = true)
	private String nombreProducto;

	private int cantidad;

	@Column(name = "fecha_ingreso")
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;

	@ManyToOne
	@JoinColumn(name = "usuario_registra")
	private UsuariosEntity usuarioRegistra;

	@ManyToOne
	@JoinColumn(name = "usuario_modifica")
	private UsuariosEntity usuarioModifica;

	@Column(name = "fecha_modifica")
	@Temporal(TemporalType.DATE)
	private Date fechaModifica;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public UsuariosEntity getUsuarioRegistra() {
		return usuarioRegistra;
	}

	public void setUsuarioRegistra(UsuariosEntity usuarioRegistra) {
		this.usuarioRegistra = usuarioRegistra;
	}

	public UsuariosEntity getUsuarioModifica() {
		return usuarioModifica;
	}

	public void setUsuarioModifica(UsuariosEntity usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}

	public Date getFechaModifica() {
		return fechaModifica;
	}

	public void setFechaModifica(Date fechaModifica) {
		this.fechaModifica = fechaModifica;
	}

}
