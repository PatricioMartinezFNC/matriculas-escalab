package com.escalab.matriculas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.matriculas.model.MatriculaModel;
import com.escalab.matriculas.repo.IMatriculaRepo;
import com.escalab.matriculas.service.IMatriculaService;

@Service
public class MatriculaServiceImpl implements IMatriculaService {
	
	@Autowired
	private IMatriculaRepo repo;
	
	@Override
	public MatriculaModel registrar(MatriculaModel obj) {
		return repo.save(obj);
	}

	@Override
	public MatriculaModel modificar(MatriculaModel obj) {
		return repo.save(obj);
	}

	@Override
	public List<MatriculaModel> listar() {
		return repo.findAll();
	}

	@Override
	public MatriculaModel leerPorId(Integer id) {
		Optional<MatriculaModel> op = repo.findById(id);
		return op.isPresent() ? op.get() : new MatriculaModel(); 
	}

	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}

}
