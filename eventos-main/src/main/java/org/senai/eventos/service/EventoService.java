package org.senai.eventos.service;
import org.senai.eventos.entity.Evento;
import org.senai.eventos.entity.dto.Evento.EventoRequestDTO;
import org.senai.eventos.entity.dto.Evento.EventoResponseDTO;
import org.senai.eventos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    @Autowired
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    //Adicionar Evento
    public Evento adicionarEvento(EventoRequestDTO data){
        Evento evento = new Evento();
        evento.setNome(data.getNome());
        evento.setCusto(data.getCusto());
        evento.setDescricao(data.getDescricao());
        eventoRepository.save(evento);
        return evento;
    }

    //Deleta evento por id
    public void removerEvento(Long eventoID) {
        eventoRepository.deleteById(eventoID);
    }


    //Atualizar Evento
    public ResponseEntity<Evento> atualizarEvento(Long eventoID, EventoResponseDTO eventoResponseDTO) {
        Evento evento = eventoRepository.findById(eventoID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado com o id: " + eventoID));

        if (eventoResponseDTO.getNome() != null) {
            eventoResponseDTO.setNome(eventoResponseDTO.getNome());
        }
        if (eventoResponseDTO.getCusto() != null) {
            eventoResponseDTO.setCusto(eventoResponseDTO.getCusto());
        }
        if (eventoResponseDTO.getDescricao() != null) {
            eventoResponseDTO.setDescricao(eventoResponseDTO.getDescricao());
        }

        eventoRepository.save(evento);
        return ResponseEntity.ok(evento);
    }

    //Lista evento
    public List<Evento> eventoList() {
        return eventoRepository.findAll();
    }

    //Encontrar por id
    public Evento eventoPorID(Long eventoID) {
        Optional<Evento> eventoOptional = eventoRepository.findById(eventoID);
        if (eventoOptional.isPresent()) {
            Evento evento = eventoOptional.get();
            return evento;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado com o id" + eventoID);
        }
    }

    //Encontrar por nome
    public Optional<Evento> encontrarPorNome(String nome) {
        Optional<Evento> evento = eventoRepository.encontrarPorNomeIgnoreCaseContains(nome);
        return evento;
    }








}
