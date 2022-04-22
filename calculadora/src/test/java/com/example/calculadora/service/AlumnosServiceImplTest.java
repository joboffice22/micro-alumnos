package com.example.calculadora.service;



import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.example.alumnos.entity.Alumno;
import com.example.alumnos.error.BadRequestException;
import com.example.alumnos.error.EmptyRequestException;
import com.example.alumnos.model.AlumnoModel;
import com.example.alumnos.repository.AlumnoRepository;
import com.example.alumnos.service.AlumnosServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness=Strictness.LENIENT)
public class AlumnosServiceImplTest {
	
	//TAG para inyectar clase a testear
	@InjectMocks
	private AlumnosServiceImpl alumnoService;	
	@Mock
	private AlumnoRepository alumnoRepository;
	private List<Alumno> lstResultado;
	private List<Alumno> lstResultadoEmpty;
	private Alumno alumno = new Alumno();
	private AlumnoModel alumnoModel = new AlumnoModel();
	private Iterable<Alumno> iterableAlumnos;
	private Iterable<Alumno> iterableEmpty;
	private Optional<Alumno> optionalAlumno;
	private Optional<Alumno> optionalAlumnoId;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		lstResultado =new ArrayList<>();
		alumno.setNombre("Jose");
		alumno.setApellido("Perez");
		alumno.setEdad(19);
		alumno.setSexo("M");
		lstResultado.add(alumno);
		iterableAlumnos= lstResultado;
		iterableEmpty= new ArrayList<>(); 
		optionalAlumno  = Optional.empty();
		optionalAlumnoId = Optional.of(alumno);
		lstResultadoEmpty = new ArrayList<>();
		
		
	}
	
	//***************************************OBTENER ALUMNO**********************************
	@Test
	void obtenerAlumnoTest() {
		Mockito.when(alumnoRepository.findAll()).thenReturn(iterableAlumnos);
		assertNotNull(alumnoService.obtenerAlumno());
	}
	
	@Test
	void obtenerAlumnoExceptionTest() {
		Mockito.when(alumnoRepository.findAll()).thenReturn(iterableEmpty);
		assertThrows( EmptyRequestException.class,()-> alumnoService.obtenerAlumno());
	}
	/* @ParameterizedTest
	@CsvSource(value= {",","1"})
	void obtenerAlumnoTest(String test) {
		if(Objects.nonNull(test)) {
			Mockito.when(alumnoRepository.findAll()).thenReturn(iterableAlumnos);
			assertNotNull(alumnoService.obtenerAlumno());
		}else {
			Mockito.when(alumnoRepository.findAll()).thenReturn(iterableEmpty);
			assertThrows( EmptyRequestException.class,()-> alumnoService.obtenerAlumno());
		}
	}*/

	//***************************************CREAR ALUMNO**********************************
	@Test
	void crearAlumnoTest() {
		//Se debe mandar un Mockito any
		Mockito.when(alumnoRepository.save(Mockito.any())).thenReturn(alumno);
		//Se manda el objeto que pide el metodo como tal
		assertNotNull(alumnoService.crearAlumno(alumnoModel));
	}
	
	//***************************************ELIMINAR ALUMNO*********************************
	/*	
	@Test
	void eliminarAlumnobyIdTest()  throws Exception {
		//Solo se simulan los metodos que retornan algo (when), nunca void
		Mockito.when(alumnoRepository.findById(Mockito.anyInt())).thenReturn(optionalAlumnoId);
		assertNotNull(alumnoService.eliminarAlumno(1));
	}
	
	@Test
	void eliminarAlumnoExceptionTest() throws Exception {
		Mockito.when(alumnoRepository.findById(Mockito.any())).thenReturn(optionalAlumno);
		assertThrows( BadRequestException.class,()-> alumnoService.eliminarAlumno(1));
	}
	*/
	
	@ParameterizedTest
	@CsvSource(value= {",","1"})
	void eliminarAlumnoTest(String test) throws Exception {
		if(Objects.nonNull(test)) {
			Mockito.when(alumnoRepository.findById(Mockito.anyInt())).thenReturn(optionalAlumnoId);
			assertNotNull(alumnoService.eliminarAlumno(1));			
		}else {			
			Mockito.when(alumnoRepository.findById(Mockito.any())).thenReturn(optionalAlumno);
			assertThrows( BadRequestException.class,()-> alumnoService.eliminarAlumno(1));
		}
	}
	//***************************************OBTENER ALUMNO  POR APELLIDO*****************************
	
	@Test
	void obtenerAlumnoApellidoTest()  throws Exception {
		//Solo se simulan los metodos que retornan algo (when), nunca void
		Mockito.when(alumnoRepository.findByApellido(Mockito.anyString())).thenReturn(lstResultado);
		assertNotNull(alumnoService.obtenerAlumnoApellido("Perez"));
	}

	@Test
	void obtenerAlumnoApellidoExceptionTest() throws Exception {
		Mockito.when(alumnoRepository.findByApellido(Mockito.any())).thenReturn(lstResultadoEmpty);
		assertThrows( BadRequestException.class,()-> alumnoService.obtenerAlumnoApellido("Pedro"));
	}
	
	/*@ParameterizedTest
	@CsvSource(value= {",","1"})
	void obtenerAlumnoApellidoTest(String test) throws Exception {
		if(Objects.nonNull(test)) {
			Mockito.when(alumnoRepository.findByApellido(Mockito.anyString())).thenReturn(lstResultado);
			assertNotNull(alumnoService.obtenerAlumnoApellido("Perez"));
		}else {			
			Mockito.when(alumnoRepository.findByApellido(Mockito.any())).thenReturn(lstResultadoEmpty);
			assertThrows( BadRequestException.class,()-> alumnoService.obtenerAlumnoApellido("Pedro"));
		}
	}*/
	//***************************************   ACTUALIZAR ALUMNO  *************************************

	@Test
	void actualizarAlumnoTest() {
		Mockito.when(alumnoRepository.findById(Mockito.anyInt())).thenReturn(optionalAlumnoId);
		Mockito.when(alumnoRepository.save(Mockito.any())).thenReturn(alumno);
		assertNotNull(alumnoService.actualizarAlumno(1, alumnoModel));
	}
	
	
	@Test
	void actualizarAlumnoExceptionTest() throws Exception {
		Mockito.when(alumnoRepository.findById(Mockito.any())).thenReturn(optionalAlumno);
		assertThrows( BadRequestException.class,()-> alumnoService.actualizarAlumno(1, alumnoModel));
	}
	
	
	
	/*@ParameterizedTest
	@CsvSource(value= {",","1"})
	void actualizarAlumnoTest(String test) throws Exception {
		if(Objects.nonNull(test)) {
			Mockito.when(alumnoRepository.findById(Mockito.anyInt())).thenReturn(optionalAlumnoId);
			Mockito.when(alumnoRepository.save(Mockito.any())).thenReturn(alumno);
			assertNotNull(alumnoService.actualizarAlumno(1, alumnoModel));
		}else {			
			Mockito.when(alumnoRepository.findById(Mockito.any())).thenReturn(optionalAlumno);
			assertThrows( BadRequestException.class,()-> alumnoService.actualizarAlumno(1, alumnoModel));
		}
	}*/
}
