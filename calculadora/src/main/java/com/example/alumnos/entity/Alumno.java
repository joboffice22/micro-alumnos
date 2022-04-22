package com.example.alumnos.entity;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity												//Declara la clase como una entidad o tabla. Esto significa que esta es una clase de entidad asignada a una tabla de base de datos
@Table(name="alumno")								//Declara el nombre de la tabla.

public class Alumno {
	@Id												//TAG para indicar la llave primaria para que JPA la reconozca como el ID del objeto
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//Especifica cómo se puede inicializar el atributo, como automático, manual o el valor obtenido de la tabla de secuencia.
	//@Column(name="idAlumnos")						//TAG para especificar los atributos de la columna de atributo persistente.
	private Integer idAlumno;
	private String nombre;
	private String apellido;
	private int edad;
	private String sexo;
	
	//Constructor
	public Alumno() {
		super();
	}
	
	
	public  Integer getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(Integer idAlumno) {
		this.idAlumno = idAlumno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	
	

}
