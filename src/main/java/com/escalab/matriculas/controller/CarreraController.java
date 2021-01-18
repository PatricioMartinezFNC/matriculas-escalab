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
import com.escalab.matriculas.service.ICarreraService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

	@Autowired
	private ICarreraService service;

	@GetMapping("/{id}")
	@ApiOperation(value = "Listar carreras por id", notes = "Lista carreras por id")
	public ResponseEntity<CarreraModel> listarPorId(@PathVariable("id") Integer id) {
		CarreraModel cm = service.leerPorId(id);
		if (cm.getIdCarrera() == null) {
			return new ResponseEntity<CarreraModel>(new CarreraModel(), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<CarreraModel>(cm, HttpStatus.OK);
		}
	}

	@GetMapping("/lista")
	@ApiOperation(value = "Lista todos las carreras", notes = "Lista todas las carreras")
	public ResponseEntity<List<CarreraModel>> listar() {
		List<CarreraModel> lista = service.listar();
		return new ResponseEntity<List<CarreraModel>>(lista, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Registra una carrera", notes = "Registra una carrera")
	public ResponseEntity<Object> registrar(@Valid @RequestBody CarreraModel carrera) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			CarreraModel cm = service.registrar(carrera);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(carrera.getIdCarrera()).toUri();
			return ResponseEntity.created(location).build();
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping
	@ApiOperation(value = "Edita una carrera", notes = "Edita una carrera")
	public ResponseEntity<CarreraModel> modificar(@Valid @RequestBody CarreraModel carrera) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			CarreraModel cm = service.modificar(carrera);
			return new ResponseEntity<CarreraModel>(cm, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Borra una carrera por id ", notes = "Borra una carrera por id")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			CarreraModel cm = service.leerPorId(id);
			if (cm.getIdCarrera() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADA" + id);
			}
			service.eliminar(id);
			return new ResponseEntity<Object>(HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

}
