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
import com.escalab.matriculas.model.MaestroModel;
import com.escalab.matriculas.model.MatriculaModel;
import com.escalab.matriculas.service.IMaestroService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/maestro")
public class MaestroController {

	@Autowired
	private IMaestroService service;
	
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Listar maestros por id", notes = "Listar maestros por id")
	public ResponseEntity<MaestroModel> listarPorId(@PathVariable("id") Integer id){
		MaestroModel cm = service.leerPorId(id);
		if(cm.getIdMaestro() == null) {
			return new ResponseEntity<MaestroModel>(new MaestroModel(), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<MaestroModel>(cm, HttpStatus.OK);
		}
	}
	
	
	
	@GetMapping("/lista")
	@ApiOperation(value = "Listar maestros", notes = "Listar maestros")
	public ResponseEntity<List<MaestroModel>> listar() {
		List<MaestroModel> lista = service.listar();
		return new ResponseEntity<List<MaestroModel>>(lista, HttpStatus.OK);
	}


	
	
	@PostMapping
	@ApiOperation(value = "Registrar un maestro", notes = "Registrar un maestro")
	public ResponseEntity<Object> registrar(@Valid @RequestBody MaestroModel maestro) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			MaestroModel cm = service.registrar(maestro);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(maestro.getIdMaestro()).toUri();
			return ResponseEntity.created(location).build();
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
	}


	
	
	@PutMapping
	@ApiOperation(value = "Modificar un maestro", notes = "Modificar un maestro")
	public ResponseEntity<MaestroModel> modificar(@Valid @RequestBody MaestroModel maestro) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			MaestroModel cm = service.modificar(maestro);
			return new ResponseEntity<MaestroModel>(cm, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
	}

	

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Borrar un maestro por id", notes = "Borrar un maestro por id")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			MaestroModel cm = service.leerPorId(id);
			if (cm.getIdMaestro() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADA" + id);
			}
			service.eliminar(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

}
