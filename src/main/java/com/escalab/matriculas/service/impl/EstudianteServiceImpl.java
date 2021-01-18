package com.escalab.matriculas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.matriculas.model.EstudianteModel;
import com.escalab.matriculas.repo.IEstudianteRepo;
import com.escalab.matriculas.service.IEstudianteService;

@Service
public class EstudianteServiceImpl implements IEstudianteService {
	
	@Autowired
	private IEstudianteRepo repo;
	
	@Override
	public EstudianteModel registrar(EstudianteModel obj) {
		return repo.save(obj);
	}

	@Override
	public EstudianteModel modificar(EstudianteModel obj) {
		return repo.save(obj);
	}

	@Override
	public List<EstudianteModel> listar() {
		return repo.findAll();
	}

	@Override
	public EstudianteModel leerPorId(Integer id) {
		Optional<EstudianteModel> op = repo.findById(id);
		return op.isPresent() ? op.get() : new EstudianteModel(); 
	}

	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}

}
