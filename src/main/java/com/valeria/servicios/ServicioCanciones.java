package com.valeria.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valeria.modelos.Cancion;
import com.valeria.repositorios.RepositorioCanciones;

@Service
public class ServicioCanciones {

	@Autowired
	private RepositorioCanciones repoCanciones;
	
	public List<Cancion> obtenerTodasLasCanciones(){
		return this.repoCanciones.findAll();
	}
	
	public Cancion obtenerCancionPorId(Long id) {
		return repoCanciones.findById(id).orElse(null);
	}
}
