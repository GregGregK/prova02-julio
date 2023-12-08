package org.senai.eventos.repository;

import org.senai.eventos.entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Optional<Participante> encontrarParticipantePorNomeIgnoreCaseContains(String nome);
}
