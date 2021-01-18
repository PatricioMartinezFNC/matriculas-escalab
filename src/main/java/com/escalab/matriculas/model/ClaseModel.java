package com.escalab.matriculas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clases")
public class ClaseModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idClase;
	
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	public ClaseModel() {
		super();
	}

	public ClaseModel(Integer idClase, String nombre) {
		super();
		this.idClase = idClase;
		this.nombre = nombre;
	}

	public Integer getIdClase() {
		return idClase;
	}

	public void setIdClase(Integer idClase) {
		this.idClase = idClase;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	


}
