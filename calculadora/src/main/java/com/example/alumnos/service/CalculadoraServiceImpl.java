package com.example.alumnos.service;
import org.springframework.stereotype.Service;

import com.example.alumnos.error.BadRequestException;
import com.example.alumnos.error.EmptyRequestException;
import com.example.alumnos.error.ValidDivisorException;
@Service																//TAG para indicar que es un servicio
//Se crea una clase para implementar la interface
public class CalculadoraServiceImpl implements CalculadoraService {
//Se sobreescriben los metodos de la interface
		@Override
		public Integer sumaNumeros(Integer num1, Integer num2) {
			// TODO Auto-generated method stub
			return num1+num2;
		}

		@Override
		public Integer restaNumeros(Integer num1, Integer num2) {
			// TODO Auto-generated method stub
			return num1-num2;
		}

		@Override
		public String multiplicacionNumeros(Integer num1, Integer num2) {
			// TODO Auto-generated method stub
			return "El resultado es: " + (num1*num2);
		}

		@Override
		public String divisionNumeros(Float num1, Float num2){
			// TODO Auto-generated method stub
			//Modificar para que no se pueda divivir entre 0
			if (num2!=0) {
				
				return "El resultado es: " +(num1/num2);
			}
			else {
				//return "El divisor no puede ser 0";
				throw new ValidDivisorException("El divisor no puede ser 0");
			}
			
		}


}
