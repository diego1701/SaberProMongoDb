package com.example.demo.App.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.App.Entity.Estudiantes;


public interface EstudianteRepository extends MongoRepository<Estudiantes, String>{
	Estudiantes findByestid(Long Estid);
}





