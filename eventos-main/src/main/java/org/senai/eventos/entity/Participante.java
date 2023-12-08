package org.senai.eventos.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Participante")
@Table(name = "Participante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;


}
