package com.teste.pontoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.pontoweb.model.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{

}
