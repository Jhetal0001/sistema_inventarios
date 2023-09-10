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

@Entity
@Table(name = "USUARIOS")
public class UsuariosEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6706414787219729018L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	private int edad;

	@ManyToOne
	@JoinColumn(name = "tipo_cargo")
	private TipoCargoEntity tipoCargo;

	@Column(name = "fecha_ingreso")
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

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public TipoCargoEntity getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(TipoCargoEntity tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

}
