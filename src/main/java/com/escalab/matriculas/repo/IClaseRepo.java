package com.escalab.matriculas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.matriculas.model.ClaseModel;

public interface IClaseRepo extends JpaRepository<ClaseModel, Integer> {

}
