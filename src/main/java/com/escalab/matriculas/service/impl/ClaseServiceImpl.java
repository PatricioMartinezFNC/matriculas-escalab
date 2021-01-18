package com.escalab.matriculas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.matriculas.model.ClaseModel;
import com.escalab.matriculas.repo.IClaseRepo;
import com.escalab.matriculas.service.IClaseService;

@Service
public class ClaseServiceImpl implements IClaseService {
	
	@Autowired
	private IClaseRepo repo;
	
	@Override
	public ClaseModel registrar(ClaseModel obj) {
		return repo.save(obj);
	}

	@Override
	public ClaseModel modificar(ClaseModel obj) {
		return repo.save(obj);
	}

	@Override
	public List<ClaseModel> listar() {
		return repo.findAll();
	}

	@Override
	public ClaseModel leerPorId(Integer id) {
		Optional<ClaseModel> op = repo.findById(id);
		return op.isPresent() ? op.get() : new ClaseModel(); 
	}

	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}

}
