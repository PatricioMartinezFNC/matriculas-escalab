package com.escalab.matriculas.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.escalab.matriculas.exception.ModeloNotFoundException;
import com.escalab.matriculas.model.EstudianteModel;
import com.escalab.matriculas.model.MatriculaModel;
import com.escalab.matriculas.service.IMatriculaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

	@Autowired
	private IMatriculaService service;
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Listar matriculas por id", notes = "Listar matriculas por id")
	public ResponseEntity<MatriculaModel> listarPorId(@PathVariable("id") Integer id){
		MatriculaModel cm = service.leerPorId(id);
		if(cm.getIdMatricula() == null) {
			return new ResponseEntity<MatriculaModel>(new MatriculaModel(), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<MatriculaModel>(cm, HttpStatus.OK);
		}
	}

	
	
	@GetMapping("/lista")
	@ApiOperation(value = "Listar todas las matriculas", notes = "Listar todas las matriculas")
	public ResponseEntity<List<MatriculaModel>> listar() {
		List<MatriculaModel> lista = service.listar();
		return new ResponseEntity<List<MatriculaModel>>(lista, HttpStatus.OK);
	}

	
	

	@PostMapping
	@ApiOperation(value = "Registrar una matricula", notes = "Registrar una matricula")
	public ResponseEntity<Object> registrar(@Valid @RequestBody MatriculaModel matricula) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			MatriculaModel cm = service.registrar(matricula);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(matricula.getIdMatricula()).toUri();
			return ResponseEntity.created(location).build();
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}


	
	
	@PutMapping
	@ApiOperation(value = "Editar una matricula", notes = "Editar una matricula ")
	public ResponseEntity<MatriculaModel> modificar(@Valid @RequestBody MatriculaModel matricula) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			MatriculaModel cm = service.modificar(matricula);
			return new ResponseEntity<MatriculaModel>(cm, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}


	
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Borrar una matricula por id", notes = "Borrar una matricula por id")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			MatriculaModel cm = service.leerPorId(id);
			if (cm.getIdMatricula() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADA" + id);
			}
			service.eliminar(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

}
