package com.example.alumnos.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.alumnos.entity.Alumno;
import com.example.alumnos.error.BadRequestException;
import com.example.alumnos.error.EmptyRequestException;
import com.example.alumnos.model.AlumnoModel;
import com.example.alumnos.repository.AlumnoRepository;

import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
@Service				//TAG para indicar que es un servicio
//Se crea una clase para implementar la interface
public class AlumnosServiceImpl implements AlumnosService {	
	@Autowired
	private AlumnoRepository alumnoRepository;
	private static ModelMapper mapper = new ModelMapper();
	
//Se sobreescriben los metodos de la interface
	@Override
	@Transactional(readOnly=true)
	//Tipo de retorno "ResponseEntity<List<AlumnoModel>>" del metodo 
	public ResponseEntity<List<AlumnoModel>>obtenerAlumno(){//**************************************METODO
		//Se crea objeto de tipo lista de AlumnoModel
		List<AlumnoModel> lstResultado=new ArrayList<>();				
		Iterable<Alumno>iAlumno=alumnoRepository.findAll();
		
		//iAlumno.iterator().
		//Validar que iAlumno venga vacio para enviar excepción
		if(iAlumno.iterator().hasNext()==false) {
			//throw new Exception("Sin resultados tabla vacia ");
			throw new EmptyRequestException("Tabla Vacia - No se encontraron datos en la tabla");
		}
		for (Alumno index:iAlumno) {
			lstResultado.add(mapper.map(index, AlumnoModel.class));
		}
		//Se retorna un ok con contenido obj lstResultado(tipo lista)
		return ResponseEntity.ok(lstResultado);						
	}
	@Transactional(readOnly=true)
	@Override
	public List<AlumnoModel> obtenerAlumnoApellido(String apellido) {//*************METODO
		//AlumnoModel lstResultado= new AlumnoModel();			
		List<AlumnoModel> lstResultado=new ArrayList<>();	
		Iterable<Alumno>iAlumno=alumnoRepository.findByApellido(apellido);
		for (Alumno index:iAlumno) {
			lstResultado.add(mapper.map(index, AlumnoModel.class));
		}
		if(lstResultado.isEmpty()) {
			throw new BadRequestException("No hubo resultados");
		}
		//Se retorna un ok con contenido obj lstResultado(tipo lista)
		return lstResultado;		
	}
	@Transactional
	@Override
	//alumno tiene los datos que se va en la DB 
	//nombre es por el campo que se va a buscar en la base de datos
	//insertar es el OBJETO que trae los datos del alumno de la DB y es el que se va actualizar
	public AlumnoModel actualizarAlumno(Integer idAlumno, AlumnoModel alumno) {//*******************METODO
		Optional insertar=alumnoRepository.findById(idAlumno);		//Busqueda del alumno por ID
		AlumnoModel resultado=new AlumnoModel();				//Inicializar una variable es crear un objeto
		if (insertar.isPresent()) {								//Verifica si existe el alumno
			Alumno actualizar=(Alumno) insertar.get();
			actualizar.setSexo(alumno.getSexo());						//Valor actualizado
			actualizar.setEdad(alumno.getEdad());
			actualizar.setApellido(alumno.getApellido());
			actualizar=alumnoRepository.save(actualizar);
			resultado=mapper.map(actualizar, AlumnoModel.class);
		}
		else {
			throw new BadRequestException("No se actualizo " + idAlumno);
		}
		return resultado;
	}

	@Transactional	//TAG mantener la persistencía de los datos
	@Override
	public AlumnoModel eliminarAlumno(Integer idAlumno) throws Exception {//***********************METODO
		Optional buscarId=alumnoRepository.findById(idAlumno);		
		AlumnoModel resultado=new AlumnoModel();				
		if (buscarId.isPresent()) {								
			//Alumno eliminar=(Alumno) buscarId.get();		//()casteando-convertir                      Regresa un objet y se convierte a alumno
			alumnoRepository.deleteById(idAlumno);//(((Alumno)buscarId.get()).getIdAlumno());            (Alumno)buscarId.get()
			resultado=mapper.map((Alumno)buscarId.get(), AlumnoModel.class);
		}else {
			throw new BadRequestException("No se puede eliminar, ID " + idAlumno + " no se  encuentra en la tabla");
		}
		
		return resultado;
	}
	
	@Transactional///
	@Override
	public AlumnoModel crearAlumno(AlumnoModel alumno) {//****************************************METODO
		Alumno registrar=mapper.map(alumno, Alumno.class);//Mapeo de Alumno Model a Alumno
		registrar= alumnoRepository.save(registrar);//Registro del Alumno, se guarda en la variable de arriba "registrar"
		//registrar= alumnoRepository.deleteById(null);
		return mapper.map(registrar, AlumnoModel.class);//Mapeo de Alumno a AlumnoModel
	}
	
	//*********************************************************************************************
	@Transactional(readOnly=true)
	@Override
	public AlumnoModel buscarIdAlumno(Integer idAlumno) {
		Optional busquedaId=alumnoRepository.findById(idAlumno);
		AlumnoModel resultadoIdAlumno = new AlumnoModel();
		if(busquedaId.isPresent()) {
			resultadoIdAlumno= mapper.map((Alumno)busquedaId.get(), AlumnoModel.class);
		}
		else {
			throw new BadRequestException("No existe alumno");
		}
		
		return resultadoIdAlumno;
	}
	//**********************************OBTENER LISTADO ALUMNOS POR ID*********************************
	@Transactional(readOnly=true)
	@Override
	public List<AlumnoModel> obtenerIdsAlumnos(List<Integer> idsAlumnos) {//*************METODO
		List<AlumnoModel> lstResultado = new ArrayList();
		Iterable<Alumno>iAlumno=alumnoRepository.findAllById(idsAlumnos);
	
		for (Alumno index : iAlumno) {
			lstResultado.add(mapper.map(index, AlumnoModel.class));
		}
		
		if(lstResultado.isEmpty()) {
			throw new BadRequestException("No hubo resultados");
		}
		
		return lstResultado;		
	}

}
