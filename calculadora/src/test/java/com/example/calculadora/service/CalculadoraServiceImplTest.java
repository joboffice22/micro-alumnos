package com.example.calculadora.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.example.alumnos.error.ValidDivisorException;
import com.example.alumnos.service.CalculadoraServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness=Strictness.LENIENT)
public class CalculadoraServiceImplTest {
	
	//TAG para inyectar clase a testear
	@InjectMocks
	private CalculadoraServiceImpl calculadoraService;
		
		
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);//???????
			
	}
	
	//Mochito.when	 -> Lo que se va simular
	//thenReturn	 -> La respuesta que va devolver
	
	@Test
	void sumaNumerosTest() {
		assertNotNull(calculadoraService.sumaNumeros(20,10));
	}
	
	/*@Test
	void restaNumerosTest() {
		Integer valor = calculadoraService.restaNumeros(20,10);
		Integer valorEsperado = 10;
		assertEquals(valorEsperado,valor);
	}*/
	
	@Test
	void multiplicacionNumerosTest() {
		assertNotNull(calculadoraService.multiplicacionNumeros(20,10));
	}
	

	@Test
	void divisionNumerosTest() {
		//Mockito.when(CalculadoraService.divisionNumeros(21f,3f)).thenReturn("3");
		assertNotNull(calculadoraService.divisionNumeros(7f,21f));
	}
	
	@Test
	void divisionExceptionTest() throws Exception{
		assertThrows( ValidDivisorException.class,()-> calculadoraService.divisionNumeros(21f,0f));
	}
	
	/*@ParameterizedTest
	@CsvSource(value= {",","1"})
	void obtenerAlumnoTest(String test) {
		if(Objects.nonNull(test)) {
			Mockito.when(calculadoraService.divisionNumeros(Mockito.anyFloat(),Mockito.anyFloat())).thenReturn("3");
			assertNotNull(calculadoraService.divisionNumeros(21f,0f));
		}else {
			Mockito.when(calculadoraService.divisionNumeros(Mockito.anyFloat(),Mockito.anyFloat())).thenReturn("3");
			assertThrows( ValidDivisorException.class,()-> calculadoraService.divisionNumeros(21f,0f));
		}
	}*/
}
