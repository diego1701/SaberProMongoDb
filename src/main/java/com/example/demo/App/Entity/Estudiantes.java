package com.example.demo.App.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotEmpty;


@Document(collection="estudiante")
public class Estudiantes {

	
		@Id
		private String id;
				
		private Long estid; 
		
		@NotEmpty
		private String Password;
		
		@NotEmpty
		private String Nombre;
		
		@NotEmpty
		private String Apellido;
		
		@NotEmpty
		private String Rol;
		
		@NotEmpty
		private String Correo;
		
		@NotEmpty
		private String Telefono;
		
		@NotEmpty
		private String IdRegistro;
		
		@NotEmpty
		private String Estado;
		
		
		private Long Puntaje_comunicacion;
		
	
		private Long Puntaje_razonamiento;
		

		private Long Puntaje_lectura;
		
		
		private Long Puntaje_competencias;
		

		private Long Puntaje_ingles;
		
		
		private Long Puntaje_proyectos;
		
		private Long Puntaje_ciencias;
		
		private Long Puntaje_dis;
		
		private Long nivel_ingles;

		public Estudiantes() {
			super();
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Long getEstid() {
			return estid;
		}

		public void setEstid(Long estid) {
			this.estid = estid;
		}

		public String getPassword() {
			return Password;
		}

		public void setPassword(String password) {
			Password = password;
		}

		public String getNombre() {
			return Nombre;
		}

		public void setNombre(String nombre) {
			Nombre = nombre;
		}

		public String getApellido() {
			return Apellido;
		}

		public void setApellido(String apellido) {
			Apellido = apellido;
		}

		public String getRol() {
			return Rol;
		}

		public void setRol(String rol) {
			Rol = rol;
		}

		public String getCorreo() {
			return Correo;
		}

		public void setCorreo(String correo) {
			Correo = correo;
		}

		public String getTelefono() {
			return Telefono;
		}

		public void setTelefono(String telefono) {
			Telefono = telefono;
		}

		public String getIdRegistro() {
			return IdRegistro;
		}

		public void setIdRegistro(String idRegistro) {
			IdRegistro = idRegistro;
		}

		public String getEstado() {
			return Estado;
		}

		public void setEstado(String estado) {
			Estado = estado;
		}

		public Long getPuntaje_comunicacion() {
			return Puntaje_comunicacion;
		}

		public void setPuntaje_comunicacion(Long puntaje_comunicacion) {
			Puntaje_comunicacion = puntaje_comunicacion;
		}

		public Long getPuntaje_razonamiento() {
			return Puntaje_razonamiento;
		}

		public void setPuntaje_razonamiento(Long puntaje_razonamiento) {
			Puntaje_razonamiento = puntaje_razonamiento;
		}

		public Long getPuntaje_lectura() {
			return Puntaje_lectura;
		}

		public void setPuntaje_lectura(Long puntaje_lectura) {
			Puntaje_lectura = puntaje_lectura;
		}

		public Long getPuntaje_competencias() {
			return Puntaje_competencias;
		}

		public void setPuntaje_competencias(Long puntaje_competencias) {
			Puntaje_competencias = puntaje_competencias;
		}

		public Long getPuntaje_ingles() {
			return Puntaje_ingles;
		}

		public void setPuntaje_ingles(Long puntaje_ingles) {
			Puntaje_ingles = puntaje_ingles;
		}

		public Long getPuntaje_proyectos() {
			return Puntaje_proyectos;
		}

		public void setPuntaje_proyectos(Long puntaje_proyectos) {
			Puntaje_proyectos = puntaje_proyectos;
		}

		public Long getPuntaje_ciencias() {
			return Puntaje_ciencias;
		}

		public void setPuntaje_ciencias(Long puntaje_ciencias) {
			Puntaje_ciencias = puntaje_ciencias;
		}

		public Long getPuntaje_dis() {
			return Puntaje_dis;
		}

		public void setPuntaje_dis(Long puntaje_dis) {
			Puntaje_dis = puntaje_dis;
		}

		public Long getNivel_ingles() {
			return nivel_ingles;
		}

		public void setNivel_ingles(Long nivel_ingles) {
			this.nivel_ingles = nivel_ingles;
		}
		
		
}
