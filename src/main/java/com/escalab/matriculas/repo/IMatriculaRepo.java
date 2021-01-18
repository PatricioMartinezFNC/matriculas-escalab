package com.escalab.matriculas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.matriculas.model.MatriculaModel;

public interface IMatriculaRepo extends JpaRepository<MatriculaModel, Integer> {

}
