package com.escalab.matriculas;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.escalab.matriculas.model.CarreraModel;
import com.escalab.matriculas.model.ClaseModel;
import com.escalab.matriculas.model.EstudianteModel;
import com.escalab.matriculas.model.MaestroModel;
import com.escalab.matriculas.model.MatriculaModel;
import com.escalab.matriculas.model.SeccionModel;
import com.escalab.matriculas.repo.ICarreraRepo;
import com.escalab.matriculas.repo.IClaseRepo;
import com.escalab.matriculas.repo.IEstudianteRepo;
import com.escalab.matriculas.repo.IMaestroRepo;
import com.escalab.matriculas.repo.IMatriculaRepo;
import com.escalab.matriculas.repo.ISeccionRepo;
import com.escalab.matriculas.service.ICarreraService;
import com.escalab.matriculas.service.IClaseService;
import com.escalab.matriculas.service.IEstudianteService;
import com.escalab.matriculas.service.IMaestroService;
import com.escalab.matriculas.service.IMatriculaService;
import com.escalab.matriculas.service.ISeccionService;
import com.escalab.matriculas.service.impl.CarreraServiceImpl;
import com.escalab.matriculas.service.impl.ClaseServiceImpl;
import com.escalab.matriculas.service.impl.EstudianteServiceImpl;
import com.escalab.matriculas.service.impl.MaestroServiceImpl;
import com.escalab.matriculas.service.impl.MatriculaServiceImpl;
import com.escalab.matriculas.service.impl.SeccionServiceImpl;

@RunWith(MockitoJUnitRunner .class)
@SpringBootTest
public class MatriculaTest {

	@Mock
	ICarreraRepo carreraRepo;
	
	@InjectMocks
	ICarreraService carreraService = new CarreraServiceImpl();
	
	@Mock
	IClaseRepo claseRepo;
	
	@InjectMocks
	IClaseService claseService = new ClaseServiceImpl();
	
	@Mock
	IEstudianteRepo estudianteRepo;
	
	@InjectMocks
	IEstudianteService estudianteService = new EstudianteServiceImpl();
	
	@Mock
	IMaestroRepo maestroRepo;
	
	@InjectMocks
	IMaestroService maestroService = new MaestroServiceImpl();
	
	@Mock
	IMatriculaRepo matriculaRepo;
	
	@InjectMocks
	IMatriculaService matriculaService = new MatriculaServiceImpl();
	
	@Mock
	ISeccionRepo seccionRepo;
	
	@InjectMocks
	ISeccionService seccionService = new SeccionServiceImpl();
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	
	
	
	@Test
	public void getCarerraById() {
		Integer carreraId = null;
		when(carreraRepo.findById(carreraId)).thenReturn(Optional.of(new CarreraModel()));
		CarreraModel carrera = carreraService.leerPorId(carreraId);
		assertEquals(carrera.getIdCarrera(), carreraId);
	}
	
	
	@Test
	public void getClaseById() {
		Integer claseId = null;
		when(claseRepo.findById(claseId)).thenReturn(Optional.of(new ClaseModel()));
		ClaseModel clase = claseService.leerPorId(claseId);
		assertEquals(clase.getIdClase(), claseId);
	}
	
	@Test
	public void getEstudianteById() {
		Integer estudianteId = null;
		when(estudianteRepo.findById(estudianteId)).thenReturn(Optional.of(new EstudianteModel()));
		EstudianteModel estudiante = estudianteService.leerPorId(estudianteId);
		assertEquals(estudiante.getIdEstudiante(), estudianteId);
	}
	
	@Test
	public void getMaestroById() {
		Integer maestroId = null;
		when(maestroRepo.findById(maestroId)).thenReturn(Optional.of(new MaestroModel()));
		MaestroModel maestro = maestroService.leerPorId(maestroId);
		assertEquals(maestro.getIdMaestro(), maestroId);
	}
	
	
	@Test
	public void getMatriculaById() {
		Integer matriculaId = null;
		when(matriculaRepo.findById(matriculaId)).thenReturn(Optional.of(new MatriculaModel()));
		MatriculaModel matricula = matriculaService.leerPorId(matriculaId);
		assertEquals(matricula.getIdMatricula(), matriculaId);
	}
	
	
	@Test
	public void getSeccionById() {
		Integer seccionId = null;
		when(seccionRepo.findById(seccionId)).thenReturn(Optional.of(new SeccionModel()));
		SeccionModel seccion = seccionService.leerPorId(seccionId);
		assertEquals(seccion.getIdSeccion(), seccionId);
	}
	
	
}
