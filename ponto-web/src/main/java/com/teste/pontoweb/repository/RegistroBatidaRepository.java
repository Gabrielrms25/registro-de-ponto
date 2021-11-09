package com.teste.pontoweb.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teste.pontoweb.model.RegistroBatida;

@Repository
public interface RegistroBatidaRepository extends JpaRepository<RegistroBatida, Long> {
	
	@Query("select rb from RegistroBatida rb where rb.usuario.id =:id and rb.dataDiaRegsitro =:data")
	Optional<RegistroBatida> findByRegistroBatidaUsuarioId(@Param("id") Long id, @Param("data") LocalDate data);

}
