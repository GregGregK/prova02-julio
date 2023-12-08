package org.senai.eventos.entity.dto.Evento;


import lombok.Data;

@Data
public class EventoRequestDTO {
    private String nome;
    private String descricao;
    private Double custo;

}
