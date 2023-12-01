package com.example.demo.App.Entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
@Document(collection="coordinador")
public class Coordinador {

	
	@Id
	private String id;
	
	private Long coorid; 
	
	@NotEmpty
	private String Password;	
	
	@NotEmpty
	private String Nombre;
	
	@NotEmpty
	private String Apellido;
	
	@NotEmpty
	private String Rol;

	public Coordinador() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCoorid() {
		return coorid;
	}

	public void setCoorid(Long coorid) {
		this.coorid = coorid;
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
	
	

}
