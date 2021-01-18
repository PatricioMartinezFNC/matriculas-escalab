package com.escalab.matriculas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.matriculas.model.CarreraModel;
import com.escalab.matriculas.repo.ICarreraRepo;
import com.escalab.matriculas.service.ICarreraService;

@Service
public class CarreraServiceImpl implements ICarreraService {
	
	@Autowired
	private ICarreraRepo repo;
	
	@Override
	public CarreraModel registrar(CarreraModel obj) {
		return repo.save(obj);
	}

	@Override
	public CarreraModel modificar(CarreraModel obj) {
		return repo.save(obj);
	}

	@Override
	public List<CarreraModel> listar() {
		return repo.findAll();
	}

	@Override
	public CarreraModel leerPorId(Integer id) {
		Optional<CarreraModel> op = repo.findById(id);
		return op.isPresent() ? op.get() : new CarreraModel(); 
	}

	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}

}
