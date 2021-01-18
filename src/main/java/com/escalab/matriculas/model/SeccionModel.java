package com.escalab.matriculas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "secciones")
public class SeccionModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "Id de la seccion", required = true)
	private Integer idSeccion;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLASE", nullable = false, foreignKey = @ForeignKey(name = "FK_CLASE_SECCION"))
	@ApiModelProperty(value = "Fk de la clase a la que esta asociada", required = true)
	private ClaseModel claseModel;
	
	@Column(name = "hora", nullable = false, length = 20)
	@ApiModelProperty(value = "Hora en la cual se imparte la clase", required = true)
	private String hora;

	@Column(name = "aula", nullable = false, length = 150)
	@ApiModelProperty(value = "Aula en la que se imparte la clase", required = true)
	private String aulas;
	
	@Column(name = "cupos", nullable = false, length = 30)
	@ApiModelProperty(value = "Cupos disponibles para la clase", required = true)
	private String cupos;
	
	@ManyToOne
	@JoinColumn(name = "ID_MAESTRO", nullable = false, foreignKey = @ForeignKey(name = "FK_MAESTRO_SECCION"))
	@ApiModelProperty(value = "Fk del maestro que imparte la clase", required = true)
	private MaestroModel maestroModel;

	public SeccionModel() {
		super();
	}

	public SeccionModel(Integer idSeccion, ClaseModel claseModel, String hora, String aulas, String cupos,
			MaestroModel maestroModel) {
		super();
		this.idSeccion = idSeccion;
		this.claseModel = claseModel;
		this.hora = hora;
		this.aulas = aulas;
		this.cupos = cupos;
		this.maestroModel = maestroModel;
	}

	public Integer getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(Integer idSeccion) {
		this.idSeccion = idSeccion;
	}

	public ClaseModel getClaseModel() {
		return claseModel;
	}

	public void setClaseModel(ClaseModel claseModel) {
		this.claseModel = claseModel;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getAulas() {
		return aulas;
	}

	public void setAulas(String aulas) {
		this.aulas = aulas;
	}

	public String getCupos() {
		return cupos;
	}

	public void setCupos(String cupos) {
		this.cupos = cupos;
	}

	public MaestroModel getMaestroModel() {
		return maestroModel;
	}

	public void setMaestroModel(MaestroModel maestroModel) {
		this.maestroModel = maestroModel;
	}
	
	

}
