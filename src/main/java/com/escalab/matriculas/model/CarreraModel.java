package com.escalab.matriculas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carreras")
public class CarreraModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCarrera;
	
	@Column(name = "nombre", nullable = false, length = 200)
	private String nombre;

	public CarreraModel() {
		super();
	}

	public CarreraModel(Integer idCarrera, String nombre) {
		super();
		this.idCarrera = idCarrera;
		this.nombre = nombre;
	}

	public Integer getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(Integer idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	

}
