package com.example.demo.App.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.App.Entity.Coordinador;

public interface CoordinadorRepository extends MongoRepository <Coordinador, String> {
	Coordinador findBycoorid(Long Coorid);

}
