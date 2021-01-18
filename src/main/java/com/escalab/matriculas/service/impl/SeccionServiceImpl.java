package com.escalab.matriculas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.matriculas.model.SeccionModel;
import com.escalab.matriculas.repo.ISeccionRepo;
import com.escalab.matriculas.service.ISeccionService;

@Service
public class SeccionServiceImpl implements ISeccionService {
	
	@Autowired
	private ISeccionRepo repo;
	
	@Override
	public SeccionModel registrar(SeccionModel obj) {
		return repo.save(obj);
	}

	@Override
	public SeccionModel modificar(SeccionModel obj) {
		return repo.save(obj);
	}

	@Override
	public List<SeccionModel> listar() {
		return repo.findAll();
	}

	@Override
	public SeccionModel leerPorId(Integer id) {
		Optional<SeccionModel> op = repo.findById(id);
		return op.isPresent() ? op.get() : new SeccionModel(); 
	}

	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}

}
