package org.senai.eventos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Table(name = "Evento")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Evento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Double custo;

    @Column(name = "data")
    private LocalDateTime data;

    @PrePersist
    public void prePersist() {data = LocalDateTime.now(); }

}
