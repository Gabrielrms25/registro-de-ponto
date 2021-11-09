package com.teste.pontoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.pontoweb.model.Usuario;
import com.teste.pontoweb.service.RegistroBatidaService;

@RestController
@RequestMapping("/registro-ponto")
public class ResgistroBatidaRestController {
	
	@Autowired
	RegistroBatidaService registroBatidaService;
	
	@PostMapping
	public ResponseEntity<Void> registrar(@RequestBody Usuario usuario){
		registroBatidaService.registrar(usuario.getId());
		return ResponseEntity.noContent().build();
	}
	
	
}
