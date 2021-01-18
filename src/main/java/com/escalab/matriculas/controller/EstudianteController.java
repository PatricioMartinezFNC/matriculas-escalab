package com.escalab.matriculas.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.escalab.matriculas.dto.EstudianteDTO;
import com.escalab.matriculas.exception.ModeloNotFoundException;
import com.escalab.matriculas.model.CarreraModel;
import com.escalab.matriculas.model.EstudianteModel;
import com.escalab.matriculas.service.IEstudianteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

	@Autowired
	private IEstudianteService service;

	@GetMapping("/{id}")
	@ApiOperation(value = "Listar estudiantes por id", notes = "Listar estudiantes por id")
	public ResponseEntity<EstudianteModel> listarPorId(@PathVariable("id") Integer id) {
		EstudianteModel cm = service.leerPorId(id);
		if (cm.getIdEstudiante() == null) {
			return new ResponseEntity<EstudianteModel>(new EstudianteModel(), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<EstudianteModel>(cm, HttpStatus.OK);
		}
	}

	
	
	@GetMapping("/lista")
	@ApiOperation(value = "Listar todos los estudiantes", notes = "Listar todos los estudiantes")
	public ResponseEntity<List<EstudianteModel>> listar() {
		List<EstudianteModel> lista = service.listar();
		return new ResponseEntity<List<EstudianteModel>>(lista, HttpStatus.OK);
	}

	
	
	@PostMapping
	@ApiOperation(value = "Registra un nuevo estudiante", notes = "Registra un nuevo estudiante")
	public ResponseEntity<Object> registrar(@Valid @RequestBody EstudianteModel estudiante) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			EstudianteModel cm = service.registrar(estudiante);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(estudiante.getIdEstudiante()).toUri();
			return ResponseEntity.created(location).build();
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	
	
	@PutMapping
	@ApiOperation(value = "Editar la información de un estudiante", notes = "Editar la información de un estudiante")
	public ResponseEntity<EstudianteModel> modificar(@Valid @RequestBody EstudianteModel estudiante) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			EstudianteModel cm = service.modificar(estudiante);
			return new ResponseEntity<EstudianteModel>(cm, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Borrar un estudiante por id", notes = "Borrar un estudiante por id")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			EstudianteModel cm = service.leerPorId(id);
			if (cm.getIdEstudiante() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADA" + id);
			}
			service.eliminar(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
	}

	
	
	@GetMapping(value = "/hateoas-estudiante", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Estudiante - Hateoas", notes = "Estudiante - Hateoas")
	public List<EstudianteDTO> listarHateoas() {

		List<EstudianteModel> estudiante = new ArrayList<>();
		List<EstudianteDTO> dto = new ArrayList<>();
		estudiante = service.listar();

		for (EstudianteModel mdl : estudiante) {
			EstudianteDTO edto = new EstudianteDTO();
			edto.setIdEstudiante(mdl.getIdEstudiante());
			edto.setCarreraModel(mdl.getCarreraModel());

			ControllerLinkBuilder linkTo = linkTo(
					methodOn(EstudianteController.class).listarPorId((mdl.getIdEstudiante())));
			edto.add(linkTo.withSelfRel());

			ControllerLinkBuilder linkTo1 = linkTo(
					methodOn(CarreraController.class).listarPorId((mdl.getCarreraModel().getIdCarrera())));
			edto.add(linkTo1.withSelfRel());

			dto.add(edto);
		}
		return dto;
	}

}
