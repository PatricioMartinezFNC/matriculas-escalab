package com.escalab.matriculas.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "matriculas")
public class MatriculaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMatricula;
	
	
	@ManyToOne
	@JoinColumn(name = "ID_ESTUDIANTE", nullable = false, foreignKey = @ForeignKey(name = "FK_ESTUDIANTE_MATRICULA"))
	private EstudianteModel estudianteModel;
	
	
	@ManyToOne
	@JoinColumn(name = "ID_SECCION", nullable = false, foreignKey = @ForeignKey(name = "FK_SECCION_MATRICULA"))
	private SeccionModel seccionModel;


	public MatriculaModel() {
		super();
	}


	public MatriculaModel(Integer idMatricula, EstudianteModel estudianteModel, SeccionModel seccionModel) {
		super();
		this.idMatricula = idMatricula;
		this.estudianteModel = estudianteModel;
		this.seccionModel = seccionModel;
	}


	public Integer getIdMatricula() {
		return idMatricula;
	}


	public void setIdMatricula(Integer idMatricula) {
		this.idMatricula = idMatricula;
	}


	public EstudianteModel getEstudianteModel() {
		return estudianteModel;
	}


	public void setEstudianteModel(EstudianteModel estudianteModel) {
		this.estudianteModel = estudianteModel;
	}


	public SeccionModel getSeccionModel() {
		return seccionModel;
	}


	public void setSeccionModel(SeccionModel seccionModel) {
		this.seccionModel = seccionModel;
	}
	
	
}
