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
import com.escalab.matriculas.model.CarreraModel;
import com.escalab.matriculas.model.SeccionModel;
import com.escalab.matriculas.service.ISeccionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/seccion")
public class SeccionController {

	@Autowired
	private ISeccionService service;

	@GetMapping("/{id}")
	@ApiOperation(value = "Listar secciones por id", notes = "Listar secciones por id")
	public ResponseEntity<SeccionModel> listarPorId(@PathVariable("id") Integer id) {
		SeccionModel cm = service.leerPorId(id);
		if (cm.getIdSeccion() == null) {
			return new ResponseEntity<SeccionModel>(new SeccionModel(), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<SeccionModel>(cm, HttpStatus.OK);
		}
	}

	@GetMapping("/lista")
	@ApiOperation(value = "Listar todas las secciones", notes = "Listar todas las secciones")
	public ResponseEntity<List<SeccionModel>> listar() {
		List<SeccionModel> lista = service.listar();
		return new ResponseEntity<List<SeccionModel>>(lista, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Registrar una nueva seccion", notes = "Registrar una nueva seccion")
	public ResponseEntity<Object> registrar(@Valid @RequestBody SeccionModel seccion) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			SeccionModel cm = service.registrar(seccion);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(seccion.getIdSeccion()).toUri();
			return ResponseEntity.created(location).build();
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@PutMapping
	@ApiOperation(value = "Modificar una seccion ", notes = "Modificar una seccion")
	public ResponseEntity<SeccionModel> modificar(@Valid @RequestBody SeccionModel seccion) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			SeccionModel cm = service.modificar(seccion);
			return new ResponseEntity<SeccionModel>(cm, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Borrar una seccion por id", notes = "Borrar una seccion por id")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			SeccionModel cm = service.leerPorId(id);
			if (cm.getIdSeccion() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADA" + id);
			}
			service.eliminar(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
	}

}
