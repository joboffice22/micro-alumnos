package com.example.alumnos.model;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//TAGS para integrar de forma automatica metodos Get y Set 
//@Getter
//@Setter

//public class AlumnoModel implements Serializable{
public class AlumnoModel {
	

	private Integer idAlumno;
	@NotEmpty(message="El nombre de usuario no puede estar vacío")
	@NotNull(message="El nombre es requerido")
	@Size(min=2,max = 10, message="Debe contener al menos 2 caracteres y maximo 4")
	//*@NotBlank(message="El nombre de usuario no puede estar vacío ó con espacios en blanco")
	private String nombre;
	private String apellido;
	private int edad;
	@Pattern(regexp="^[f|mF|M]{1}$")
	private String sexo;
	
	public Integer getIdAlumno() {
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
	@Override
	public String toString() {
		return "AlumnoModel [idAlumno=" + idAlumno + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	
	
	
}














