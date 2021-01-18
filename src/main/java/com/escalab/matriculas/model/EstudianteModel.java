package com.escalab.matriculas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "estudiantes")
public class EstudianteModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstudiante;
	
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;
	
	@Column(name = "apellido", nullable = false, length = 100)
	private String apellido;
	
	@Column(name = "fechaNac", nullable = false, length = 30)
	private Date fechaNac;
	
	@ManyToOne
	@JoinColumn(name = "ID_CARRERA", nullable = false, foreignKey = @ForeignKey(name = "FK_CARRERA_ESTUDIANTE"))
	private CarreraModel carreraModel;

	public EstudianteModel() {
		super();
	}

	public EstudianteModel(Integer idEstudiante, String nombre, String apellido, Date fechaNac,
			CarreraModel carreraModel) {
		super();
		this.idEstudiante = idEstudiante;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.carreraModel = carreraModel;
	}

	public Integer getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(Integer idEstudiante) {
		this.idEstudiante = idEstudiante;
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

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public CarreraModel getCarreraModel() {
		return carreraModel;
	}

	public void setCarreraModel(CarreraModel carreraModel) {
		this.carreraModel = carreraModel;
	}
	
	
	

}
