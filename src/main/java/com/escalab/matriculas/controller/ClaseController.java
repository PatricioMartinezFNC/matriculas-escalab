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
import com.escalab.matriculas.model.ClaseModel;
import com.escalab.matriculas.model.SeccionModel;
import com.escalab.matriculas.service.IClaseService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clase")
public class ClaseController {

	@Autowired
	private IClaseService service;


	@GetMapping("/{id}")
	@ApiOperation(value = "Lista un usuario por id", notes = "Lista un usuario")
	public ResponseEntity<ClaseModel> listarPorId(@PathVariable("id") Integer id){
		ClaseModel cm = service.leerPorId(id);
		if(cm.getIdClase() == null) {
			return new ResponseEntity<ClaseModel>(new ClaseModel(), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ClaseModel>(cm, HttpStatus.OK);
		}
	}
	
	
	
	@GetMapping("/lista")
	@ApiOperation(value = "Lista todos los usuarios", notes = "Lista todos los usuarios")
	public ResponseEntity<List<ClaseModel>> listar() {
		List<ClaseModel> lista = service.listar();
		return new ResponseEntity<List<ClaseModel>>(lista, HttpStatus.OK);
	}


	
	@PostMapping
	@ApiOperation(value = "Crea una asignatura nueva", notes = "Crea una asignatura nueva")
	public ResponseEntity<Object> registrar(@Valid @RequestBody ClaseModel clase) {
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			ClaseModel cm = service.registrar(clase);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(clase.getIdClase()).toUri();
			return ResponseEntity.created(location).build();
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}


	
	
	@PutMapping
	@ApiOperation(value = "Edita una asignatura", notes = "Edita una asignatura")
	public ResponseEntity<ClaseModel> modificar(@Valid @RequestBody ClaseModel clase) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			ClaseModel cm = service.modificar(clase);
			return new ResponseEntity<ClaseModel>(cm, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}


	
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Elimina un usuario", notes = "Elimina un usuario por id")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			ClaseModel cm = service.leerPorId(id);
			if (cm.getIdClase() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADA" + id);
			}
			service.eliminar(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

}
