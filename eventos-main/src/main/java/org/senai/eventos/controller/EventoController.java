package org.senai.eventos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.senai.eventos.entity.Evento;
import org.senai.eventos.entity.dto.Evento.EventoRequestDTO;
import org.senai.eventos.entity.dto.Evento.EventoResponseDTO;
import org.senai.eventos.service.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {

private final EventoService eventoService;

@Tag(name = "Busca eventos")
@Operation(summary = "Retorna todos eventos")
@PreAuthorize("hasRole('PRODUCT_SELECT')")
@GetMapping("/encontrar-todos")
public ResponseEntity<?> eventoList(){
    try {
        List<Evento> eventos = eventoService.eventoList();
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    } catch (Exception ex) {
        return new ResponseEntity<>("Erro ao processar dados", HttpStatus.BAD_GATEWAY);
    }
}


@Tag(name = "Buscar por nome")
@Operation(summary = "Retorna por nome")
@PreAuthorize("hasRole('PRODUCT_SELECT')")
@GetMapping("/nome-evento")
public ResponseEntity<?> encontrarEventoPorNome(@RequestParam String nome) {
    try {
        Optional<Evento> evento = eventoService.encontrarPorNome(nome);
        return new ResponseEntity<>(evento, HttpStatus.OK);
    } catch (Exception ex) {
        return new ResponseEntity<>("Erro ao processar dados", HttpStatus.BAD_GATEWAY);
    }
}

    @Tag(name = "Busca por id")
    @Operation(summary = "Busca Eventos pelo seu id")
    @PreAuthorize("hasRole('PRODUCT_SELECT')")
    @GetMapping("/id/{id}")
    public ResponseEntity<?> encontrarPorId(@PathVariable Long id) {
        try {
            Evento evento = eventoService.eventoPorID(id);
            return ResponseEntity.ok(evento);
        } catch (Exception ex) {
            return new ResponseEntity<>("Erro ao processar dados", HttpStatus.BAD_REQUEST);
        }
    }

    @Tag(name = "Adiciona Evento")
    @Operation(summary = "Adiciona eventos no banco de dados")
    @PreAuthorize("hasRole('PRODUCT_INSERT')")
    @PostMapping
    public ResponseEntity<?> adicionarEvento(@RequestBody EventoRequestDTO eventoRequestDTO) {
        try {
            Evento evento = eventoService.adicionarEvento(eventoRequestDTO);
            return new ResponseEntity<>(evento, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Erro ao processar dados", HttpStatus.BAD_REQUEST);
        }
    }

    @Tag(name = "Atualiza Evento")
    @Operation(summary = "Atualiza eventos pelo seu id")
    @PreAuthorize("hasRole('PRODUCT_UPDATE')")
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEvento(
            @PathVariable Long id,
            @RequestBody EventoResponseDTO eventoResponseDTO) {
        try {
            var evento = eventoService.atualizarEvento(id, eventoResponseDTO);
            return new ResponseEntity<>(evento, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao processar dados", HttpStatus.BAD_REQUEST);
        }
    }


    @Tag(name = "Deleta Evento")
    @Operation(summary = "Deleta evento pelo seu id")
    @PreAuthorize("hasRole('PRODUCT_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaEvento(@PathVariable Long id) {
        try {
            eventoService.removerEvento(id);
            return new ResponseEntity<>("Evento removido",  HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao processar dados", HttpStatus.BAD_REQUEST);
        }
    }


    @Tag(name = "Altera descricao")
    @Operation(summary = "Altera apenas a descrição")
    @PreAuthorize("hasRole('PRODUCT_UPDATE')")
    @PatchMapping("/alterar-descricao/{id}")
    public ResponseEntity<EventoResponseDTO> alterarEvento(@RequestBody EventoResponseDTO eventoResponseDTO,
                                                          @PathVariable("id") Long id){
        return new ResponseEntity<>(eventoResponseDTO, HttpStatus.OK);
    }













}

