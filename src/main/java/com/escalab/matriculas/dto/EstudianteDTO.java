package com.escalab.matriculas.dto;

import org.springframework.hateoas.ResourceSupport;

import com.escalab.matriculas.model.CarreraModel;

public class EstudianteDTO extends ResourceSupport {
	
	private Integer idEstudiante;
	private CarreraModel carreraModel;
	
	
	
	public Integer getIdEstudiante() {
		return idEstudiante;
	}
	public void setIdEstudiante(Integer idEstudiante) {
		this.idEstudiante = idEstudiante;
	}
	public CarreraModel getCarreraModel() {
		return carreraModel;
	}
	public void setCarreraModel(CarreraModel carreraModel) {
		this.carreraModel = carreraModel;
	}
	

}
