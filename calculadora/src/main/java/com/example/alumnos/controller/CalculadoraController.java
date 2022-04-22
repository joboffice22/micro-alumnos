package com.example.alumnos.controller;

import org.springframework.web.bind.annotation.GetMapping;		
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.alumnos.service.CalculadoraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/api")						//Se crea BASE PATH para acceder desde un enpoint en especifico
@RestController								//Se crea el Controller
public class CalculadoraController {		//Se crea clase Controler
	
	@Autowired								//TAG para inyectar una clase
	private CalculadoraService calculadora;	//Se inyecta clase
	//Suma
	@GetMapping("/suma/{numero1}/{num2}")
	public String operacionSuma(@PathVariable (name="numero1") Integer num1, @PathVariable Integer num2) {
		return calculadora.sumaNumeros(num1, num2).toString();
	}
	//Resta - Recibir y retornar numeros enteros
	@GetMapping("/resta/{num1}/{num2}")
	public Integer operacionResta(@PathVariable Integer num1, @PathVariable Integer num2) {
		return calculadora.restaNumeros(num1, num2);
	}
	//Multiplicación - Recibir numeros enteros pero regresar junto con un String
	@GetMapping("/multiplicacion/{num1}/{num2}")
	public String operacionMultiplicacion(@PathVariable Integer num1, @PathVariable Integer num2) {
		return calculadora.multiplicacionNumeros(num1, num2);
	}
	//Division - Recibir numeros enteros o flotantes y retornar un flotante
	@GetMapping("/division/{num1}/{num2}")
	public String operaciónDivision(@PathVariable Float num1, @PathVariable Float num2) throws Exception {
		return calculadora.divisionNumeros(num1,num2).toString();
	}
	
}
