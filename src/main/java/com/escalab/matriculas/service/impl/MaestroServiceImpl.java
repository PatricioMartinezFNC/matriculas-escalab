package com.escalab.matriculas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.matriculas.model.MaestroModel;
import com.escalab.matriculas.repo.IMaestroRepo;
import com.escalab.matriculas.service.IMaestroService;

@Service
public class MaestroServiceImpl implements IMaestroService {
	
	@Autowired
	private IMaestroRepo repo;
	
	@Override
	public MaestroModel registrar(MaestroModel obj) {
		return repo.save(obj);
	}

	@Override
	public MaestroModel modificar(MaestroModel obj) {
		return repo.save(obj);
	}

	@Override
	public List<MaestroModel> listar() {
		return repo.findAll();
	}

	@Override
	public MaestroModel leerPorId(Integer id) {
		Optional<MaestroModel> op = repo.findById(id);
		return op.isPresent() ? op.get() : new MaestroModel(); 
	}

	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}

}
