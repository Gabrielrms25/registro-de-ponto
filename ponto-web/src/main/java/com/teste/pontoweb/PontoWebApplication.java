package com.teste.pontoweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.teste.pontoweb.model.Usuario;
import com.teste.pontoweb.repository.UsuarioRepository;

@SpringBootApplication
public class PontoWebApplication implements CommandLineRunner {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(PontoWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setCpf("03710361273");
		usuario.setNome("Gabriel Ramos");
		usuarioRepository.save(usuario);
	}
	
	
	

}
