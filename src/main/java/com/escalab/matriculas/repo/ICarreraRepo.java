package com.escalab.matriculas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.matriculas.model.CarreraModel;

public interface ICarreraRepo extends JpaRepository<CarreraModel, Integer> {

}
