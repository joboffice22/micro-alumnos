package com.example.alumnos.controller;
import org.springframework.web.bind.annotation.GetMapping;	
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.alumnos.error.BadRequestException;
import com.example.alumnos.model.AlumnoModel;
import com.example.alumnos.service.AlumnosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

@RequestMapping("/alumnos")					//Se crea BASE PATH para acceder desde un enpoint en especifico
@RestController								//Se crea Controller
public class AlumnosController {			//Se crea clase Controller
	@Autowired								//TAG para inyectar una clase
	private AlumnosService alumnos;			//Inyectando clase AlumnosService, esto es igual a AlumnosService alumnos=new alumnosServices();
	
	//TAG para realizar una llamada de consulta con el end point alumnos
	@GetMapping("/alumnos")//Consultar Alumnos					
	public ResponseEntity<List<AlumnoModel>> consultarAlumnos(){
		return alumnos.obtenerAlumno();
	}
	
	//TAG para realizar una llamada de consulta con el end point alumnos
	@GetMapping("/consultar/{apellido}")//Consultar por Apellido Alumno				
	public ResponseEntity<List<AlumnoModel>>consultarNombreAlumno(@PathVariable String apellido) {
		return ResponseEntity.ok(alumnos.obtenerAlumnoApellido(apellido));
	}
	
	// TAG para actualizar un registro con el endpoint /{nombre}
	@PutMapping("/actualizar/{idAlumno}")//Actualizar por idAlumno						
	//Se buscara por nombre ya que no tenemos un id
	//Tipo dde retorno "ResponseEntity<AlumnoModel>" de un metodo, entros casos String, Integer, etc.
	//La TAG @RequestBody sirve para indicar que llegara un cuerpo en la petición y traera los datos del alumno.
	public ResponseEntity<AlumnoModel>actualizarAlumno(@PathVariable Integer idAlumno,@RequestBody @Valid AlumnoModel alumno){
		return ResponseEntity.ok(alumnos.actualizarAlumno(idAlumno, alumno));
	}
	// TAG para insertar un registro 
	@PostMapping("/crear")				
	public ResponseEntity<AlumnoModel>crearAlumno(@RequestBody @Valid AlumnoModel alumno) throws Exception{
		return ResponseEntity.ok(alumnos.crearAlumno(alumno));//crearAlumno
	}
	//
	// TAG para eliminar un registro 
	@DeleteMapping("/eliminar/{idAlumno}")				
	public ResponseEntity<AlumnoModel>eliminarAlumno(@PathVariable Integer idAlumno) throws Exception {
		return ResponseEntity.ok(alumnos.eliminarAlumno(idAlumno));
	}
	// TAG para consular alumno por id
	@GetMapping("/consulta-alumno/{id}")
	public ResponseEntity<AlumnoModel>buscarAlumnoId(@PathVariable (name="id") Integer idAlumno){
		return ResponseEntity.ok(alumnos.buscarIdAlumno(idAlumno));
	}
	// TAG para consular listado de alumnos
	@GetMapping("/consulta-id-alumnos")
	public ResponseEntity<List<AlumnoModel>> obtenerIdAlumnos(@RequestParam List<Integer> idsAlumnos){
		return ResponseEntity.ok(alumnos.obtenerIdsAlumnos(idsAlumnos));
	}
	//******************************************CONSULTAR LISTA DE ALUMNOS POR ID*************************
	@GetMapping("/consulta-alumnos-id")
	public ResponseEntity<List<AlumnoModel>> obtenerAlumnosPorId(@RequestParam List<Integer> idsAlumnos){
		return ResponseEntity.ok(alumnos.obtenerIdsAlumnos(idsAlumnos));
	}
	
	
	/*@GetMapping("/consulta-alumnos-id")
	public ResponseEntity<List<AlumnoModel>> obtenerAlumnosPorId(@RequestBody List<AlumnoModel> idsAlumnos){
		List<Integer> ids = new ArrayList<>(); 
		idsAlumnos.forEach(alumno -> { 
			System.out.println(alumno.toString());
			ids.add(alumno.getIdAlumno());
		});
		return ResponseEntity.ok(alumnos.obtenerIdsAlumnos(ids));
	}*/
	
	
}
