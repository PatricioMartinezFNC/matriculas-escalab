package com.escalab.matriculas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.matriculas.model.EstudianteModel;

public interface IEstudianteRepo extends JpaRepository<EstudianteModel, Integer> {

}
