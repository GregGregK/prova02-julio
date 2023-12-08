package org.senai.eventos.service;

import org.senai.eventos.entity.Participante;
import org.senai.eventos.entity.dto.Participante.ParticipanteRequestDTO;
import org.senai.eventos.entity.dto.Participante.ParticipanteResponseDTO;
import org.senai.eventos.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteService {

private final ParticipanteRepository participanteRepository;

@Autowired
    public ParticipanteService(ParticipanteRepository participanteRepository) {
    this.participanteRepository = participanteRepository;
}

    //Adicionar Participante
    public Participante adicionarParticipante(ParticipanteRequestDTO data){
        Participante participante = new Participante();
        participante.setNome(data.getNome());
        participante.setEmail(data.getEmail());
        participanteRepository.save(participante);
        return participante;
    }

    //Deleta participante por id
    public void removerParticipante(Long participanteID) {
        participanteRepository.deleteById(participanteID);
    }


    //Atualizar Participante
    public ResponseEntity<Participante> atualizarParticipante(Long participanteID, ParticipanteResponseDTO participanteResponseDTO) {
        Participante participante = participanteRepository.findById(participanteID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Participante não encontrado com o id: " + participanteID));

        if (participanteResponseDTO.getNome() != null) {
            participanteResponseDTO.setNome(participanteResponseDTO.getNome());
        }
        if (participanteResponseDTO.getEmail() != null) {
            participanteResponseDTO.setEmail(participanteResponseDTO.getEmail());
        }
            participanteRepository.save(participante);
            return ResponseEntity.ok(participante);

    }


    //Lista participante
        public List<Participante> participanteList() {
            return participanteRepository.findAll();
        }

    //Encontrar por id
    public Participante participantePorID(Long participanteID) {
        Optional<Participante> participanteOptional = participanteRepository.findById(participanteID);
        if (participanteOptional.isPresent()) {
            Participante participante = participanteOptional.get();
            return participante;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Participante não encontrado com o id" + participanteID);
        }
    }

    //Encontrar por nome
    public Optional<Participante> encontrarPorNome(String nome) {
        Optional<Participante> participante = participanteRepository.encontrarParticipantePorNomeIgnoreCaseContains(nome);
        return participante;
    }

}

