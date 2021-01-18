package com.escalab.matriculas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.matriculas.model.SeccionModel;

public interface ISeccionRepo extends JpaRepository<SeccionModel, Integer> {

}
