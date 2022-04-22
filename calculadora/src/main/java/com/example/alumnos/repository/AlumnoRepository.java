package com.example.alumnos.repository;
import org.springframework.data.repository.CrudRepository;

import com.example.alumnos.entity.Alumno;

import java.util.List;
//import org.springframework.transaction.annotation.Transactional;

public interface AlumnoRepository extends CrudRepository<Alumno,Integer>{
	//@Transactional(readOnly=true)					//Se indica solo sera de lectura
	List<Alumno> findByApellido(String apellido);
	List<Alumno> findByNombre(String nombre);

	
}
