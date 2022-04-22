package com.example.calculadora.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.alumnos.controller.CalculadoraController;
import com.example.alumnos.service.CalculadoraService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness=Strictness.LENIENT)
public class CalculadoraControllerTest {
	//TAG para inyectar clase a testear
	@InjectMocks
	private CalculadoraController calculadora;
	//TAG para  inyectar dependencias del controlador @autowired
	@Mock
	private CalculadoraService service;
	
	//MockMvc Solo se utiliza para testear controladores
	private MockMvc mockMvc;
	//TAG para indicar el metodo de inicio
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(calculadora).build();//????
	}
	
	
	@Test
	void operacionSumaTest() throws Exception {
		Mockito.when(service.sumaNumeros(Mockito.anyInt(),Mockito.anyInt())).thenReturn(10);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/suma/1/2")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
	
	
	@Test
	void operacionRestaTest() throws Exception {
		Mockito.when(service.restaNumeros(Mockito.anyInt(), Mockito.anyInt())).thenReturn(3);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/resta/10/2")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	void operacionMultiplicacionTest() throws Exception {
		Mockito.when(service.multiplicacionNumeros(Mockito.anyInt(), Mockito.anyInt())).thenReturn("15");
		mockMvc.perform(MockMvcRequestBuilders.get("/api/multiplicacion/5/3")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	void operaciónDivisionTest() throws Exception {
		Mockito.when(service.divisionNumeros(Mockito.anyFloat(),Mockito.anyFloat())).thenReturn("3.0");
		mockMvc.perform(MockMvcRequestBuilders.get("/api/division/15/5")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}


}
