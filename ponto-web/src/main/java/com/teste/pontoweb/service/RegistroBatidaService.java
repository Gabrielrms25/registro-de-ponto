package com.teste.pontoweb.service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.teste.pontoweb.model.RegistroBatida;
import com.teste.pontoweb.model.Usuario;
import com.teste.pontoweb.repository.RegistroBatidaRepository;
import com.teste.pontoweb.repository.UsuarioRepository;

@Service
public class RegistroBatidaService {

	@Autowired
	RegistroBatidaRepository registroBatidaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	UsuarioService usuarioService;

	public void registrar(Long id) {

		Usuario usuario = usuarioService.getUsuarioById(id);

		LocalDate dataHoje = LocalDate.now();
		if (checarDiaFinalDeSemana(dataHoje.getDayOfWeek())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Sábado e domingo não são permitidos como dia de trabalho");
		}

		registroBatidaRepository.findByRegistroBatidaUsuarioId(usuario.getId(), dataHoje)
				.ifPresentOrElse(x -> verificaRegistros(dataHoje, x), () -> criarRegistro(usuario, dataHoje));
	}

	private void criarRegistro(Usuario usuario, LocalDate dataHoje) {
		System.out.println("registro do dia vazio" + dataHoje);
		RegistroBatida registro = new RegistroBatida();
		registro.setHoraEntradaManha(LocalTime.now());
		registro.setDataDiaRegsitro(dataHoje);
		registro.setUsuario(usuario);
		registroBatidaRepository.save(registro);
	}

	private void verificaRegistros(LocalDate dataHoje, RegistroBatida registro) {
		System.out.println("registro do dia presente" + dataHoje);
		if (registro.getHoraSaidaManha() == null) {
			registro.setHoraSaidaManha(LocalTime.now());
			System.out.println("hora saida manha nula");
			registroBatidaRepository.save(registro);
			return;
		}

		if (registro.getHoraEntradaTarde() == null) {
			LocalTime saidaManha = registro.getHoraSaidaManha();
			LocalTime horaAtual = LocalTime.now();
			long hours = Duration.between(saidaManha, horaAtual).toHours();
			if (hours < 1) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Deve haver no mínimo 1 hora de almoço");
			}
			registro.setHoraEntradaTarde(LocalTime.now());
			System.out.println("hora entrada tarde nula");
			registroBatidaRepository.save(registro);
			return;
		}
		if (registro.getHoraSaidaTarde() == null) {
			registro.setHoraSaidaTarde(LocalTime.now());
			System.out.println("hora saida tarde nula");
			registroBatidaRepository.save(registro);
			return;
		}
		
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas 4 horários podem ser registrados por dia");

	}

	public boolean checarDiaFinalDeSemana(DayOfWeek dataAux) {
		if (dataAux.equals(DayOfWeek.SATURDAY) || dataAux.equals(DayOfWeek.SUNDAY)) {
			return true;
		}
		return false;
	}
}
