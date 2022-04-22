package com.example.calculadora.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.alumnos.controller.AlumnosController;
import com.example.alumnos.model.AlumnoModel;
import com.example.alumnos.service.AlumnosService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.quality.Strictness;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness=Strictness.LENIENT)
class AlumnosControllerTest {
	//TAG para inyectar clase a testear
	@InjectMocks
	private AlumnosController alumno;
	//TAG para  inyectar dependencias del controlador @autowired
	@Mock
	private AlumnosService service;
	private ResponseEntity<List<AlumnoModel>> resultadoEntity;
	private List<AlumnoModel> lstResultado;
	private AlumnoModel alumnoModel = new AlumnoModel();
	private ObjectMapper mapper=new ObjectMapper();
	
	
	
	//MockMvc Solo se utiliza para testear controladores
	private MockMvc mockMvc;
	//TAG para indicar el metodo de inicio
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(alumno).build();
		lstResultado =new ArrayList<>();
		alumnoModel.setNombre("Jose");
		alumnoModel.setApellido("Perez");
		alumnoModel.setEdad(19);
		alumnoModel.setSexo("M");
		lstResultado.add(alumnoModel);
		resultadoEntity=ResponseEntity.ok(lstResultado);
	}
	
	
	//TAG para indicar que el metodo es un Test
	@Test
	void consultarAlumnosTest() throws Exception {
		//Se utiliza para simular la respuesta del servicio
		Mockito.when(service.obtenerAlumno()).thenReturn(resultadoEntity);
		mockMvc.perform(MockMvcRequestBuilders.get("/escuela/alumnos")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
	@Test
	void consultarNombreAlumnoTest() throws Exception {
		Mockito.when(service.obtenerAlumnoApellido(Mockito.anyString())).thenReturn(lstResultado);
		mockMvc.perform(MockMvcRequestBuilders.get("/escuela/consultar/jose")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
		
	}
	@Test
	void actualizarAlumnoTest()throws Exception {
		Mockito.when(service.actualizarAlumno(Mockito.anyInt(), Mockito.any())).thenReturn(alumnoModel);
		mockMvc.perform(MockMvcRequestBuilders.put("/escuela/actualizar/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(alumnoModel)))//Lo que se recibe en el @RequestBody
				.andExpect(MockMvcResultMatchers.status().isOk())//Valor esperado
				.andDo(MockMvcResultHandlers.print());//Imprimir en consola
	}
	@Test
	void crearAlumnoTest() throws Exception{
		Mockito.when(service.crearAlumno(Mockito.any())).thenReturn(alumnoModel);
		mockMvc.perform(MockMvcRequestBuilders.post("/escuela/crear")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(alumnoModel)))//Lo que se recibe en el @RequestBody
				.andExpect(MockMvcResultMatchers.status().isOk())//Valor esperado
				.andDo(MockMvcResultHandlers.print());//Imprimir en consola
		
	}
	@Test
	void eliminarAlumnoTest() throws Exception{
		Mockito.when(service.eliminarAlumno(Mockito.anyInt())).thenReturn(alumnoModel);
		mockMvc.perform(MockMvcRequestBuilders.delete("/escuela/eliminar/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
	

}
