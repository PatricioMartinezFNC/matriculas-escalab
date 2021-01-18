package com.escalab.matriculas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.matriculas.model.MaestroModel;

public interface IMaestroRepo extends JpaRepository<MaestroModel, Integer> {

}
