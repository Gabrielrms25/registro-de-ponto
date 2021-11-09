package com.teste.pontoweb.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RegistroBatida implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalTime horaEntradaManha;
	
	private LocalTime horaSaidaManha;
	
	private LocalTime horaEntradaTarde;
	
	private LocalTime horaSaidaTarde;
	
	private LocalDate dataDiaRegsitro;
	
	@ManyToOne
	private Usuario usuario;
	
	
}
