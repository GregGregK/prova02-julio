package org.senai.eventos.repository;

import org.senai.eventos.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    Optional<Evento> encontrarPorNomeIgnoreCaseContains (String nome);

}
