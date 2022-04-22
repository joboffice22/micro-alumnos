package com.example.alumnos.service;

import org.springframework.http.ResponseEntity;

import com.example.alumnos.error.BadRequestException;
import com.example.alumnos.model.AlumnoModel;

import java.util.ArrayList;
import java.util.List;
//Una interface es una plantilla donde se van creando metodos sin contenido ni cuerpo.
public interface AlumnosService {
	public ResponseEntity<List<AlumnoModel>>obtenerAlumno();
	public List<AlumnoModel> obtenerAlumnoApellido(String apellido);
	public AlumnoModel crearAlumno(AlumnoModel alumno);
	public AlumnoModel actualizarAlumno(Integer idAlumno, AlumnoModel alumno);
	public AlumnoModel eliminarAlumno(Integer idAlumno) throws Exception;
	//public List<AlumnoModel> eliminarAlumno(Integer idAlumno)throws Exception;
	public AlumnoModel buscarIdAlumno(Integer idAlumno);
	public List<AlumnoModel> obtenerIdsAlumnos(List<Integer> idsAlumnos);



}
