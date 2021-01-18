package com.escalab.matriculas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "maestros")
public class MaestroModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "Id del maestro autoincrementable", required = true)
	private Integer idMaestro;
	
	@Column(name = "nombre", nullable = false, length = 100)
	@ApiModelProperty(value = "Nombre completo del maestro", required = true)
	private String nombre;

	@Column(name = "apellido", nullable = false, length = 100)
	@ApiModelProperty(value = "Apellido completo del profesor", required = true)
	private String apellido;

	public MaestroModel() {
		super();
	}

	public MaestroModel(Integer idMaestro, String nombre, String apellido) {
		super();
		this.idMaestro = idMaestro;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Integer getIdMaestro() {
		return idMaestro;
	}

	public void setIdMaestro(Integer idMaestro) {
		this.idMaestro = idMaestro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	

}
