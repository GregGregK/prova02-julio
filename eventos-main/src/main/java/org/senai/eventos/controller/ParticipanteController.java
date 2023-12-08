package org.senai.eventos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.senai.eventos.entity.Participante;
import org.senai.eventos.entity.dto.Participante.ParticipanteRequestDTO;
import org.senai.eventos.entity.dto.Participante.ParticipanteResponseDTO;
import org.senai.eventos.service.ParticipanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/participantes")
@RequiredArgsConstructor
public class ParticipanteController {

    private final ParticipanteService participanteService;
    @Tag(name = "Busca Participantes")
    @Operation(summary = "Retorna todos participantes")
    @PreAuthorize("hasRole('PRODUCT_SELECT')")
    @GetMapping("/encontrar-todos")
    public ResponseEntity<?> participanteList(){
        try {
            List<Participante> participantes = participanteService.participanteList();
            return new ResponseEntity<>(participantes, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Erro ao processar dados", HttpStatus.BAD_GATEWAY);
        }
    }


    @Tag(name = "Buscar por nome")
    @Operation(summary = "Retorna por nome")
    @PreAuthorize("hasRole('PRODUCT_SELECT')")
    @GetMapping("/nome-participante")
    public ResponseEntity<?> encontrarParticipantePorNome(@RequestParam String nome) {
        try {
            Optional<Participante> participante = participanteService.encontrarPorNome(nome);
            return new ResponseEntity<>(participante, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Erro ao processar dados", HttpStatus.BAD_GATEWAY);
        }
    }

    @Tag(name = "Busca por id")
    @Operation(summary = "Busca Participantes pelo seu id")
    @PreAuthorize("hasRole('PRODUCT_SELECT')")
    @GetMapping("/id/{id}")
    public ResponseEntity<?> encontrarPorId(@PathVariable Long id) {
        try {
            Participante participante = participanteService.participantePorID(id);
            return ResponseEntity.ok(participante);
        } catch (Exception ex) {
            return new ResponseEntity<>("Erro ao processar dados", HttpStatus.BAD_REQUEST);
        }
    }

    @Tag(name = "Adiciona Participante")
    @Operation(summary = "Adiciona participantes no banco de dados")
    @PreAuthorize("hasRole('PRODUCT_INSERT')")
    @PostMapping
    public ResponseEntity<?> adicionarParticipante(@RequestBody ParticipanteRequestDTO participanteRequestDTO) {
        try {
            Participante participante = participanteService.adicionarParticipante(participanteRequestDTO);
            return new ResponseEntity<>(participante, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Erro ao processar dados", HttpStatus.BAD_REQUEST);
        }
    }

    @Tag(name = "Atualiza Participante")
    @Operation(summary = "Atualiza participantes pelo seu id")
    @PreAuthorize("hasRole('PRODUCT_UPDATE')")
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarParticipante(
            @PathVariable Long id,
            @RequestBody ParticipanteResponseDTO participanteResponseDTO) {
        try {
            var participante = participanteService.atualizarParticipante(id, participanteResponseDTO);
            return new ResponseEntity<>(participante, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao processar dados", HttpStatus.BAD_REQUEST);
        }
    }


    @Tag(name = "Deleta Participante")
    @Operation(summary = "Deleta participante pelo seu id")
    @PreAuthorize("hasRole('PRODUCT_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaParticipante(@PathVariable Long id) {
        try {
            participanteService.removerParticipante(id);
            return new ResponseEntity<>("Participante removido",  HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao processar dados", HttpStatus.BAD_REQUEST);
        }
    }


    @Tag(name = "Altera email")
    @Operation(summary = "Altera apenas o email")
    @PreAuthorize("hasRole('PRODUCT_UPDATE')")
    @PatchMapping("/alterar-descricao/{id}")
    public ResponseEntity<ParticipanteResponseDTO> alterarEmail(@RequestBody ParticipanteResponseDTO participanteResponseDTO,
                                                           @PathVariable("id") Long id){
        return new ResponseEntity<>(participanteResponseDTO, HttpStatus.OK);
    }
}
