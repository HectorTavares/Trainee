package br.com.cwi.crescer.api.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "interacao_pergunta")
@SequenceGenerator(name = "seq_interacao_pergunta", sequenceName = "seq_interacao_pergunta", allocationSize = 1)
public class InteracaoPergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_interacao_pergunta")
    private Long id;

    @Column(name = "tipoInteracao")
    private TipoInteracao tipoInteracao;

    @ManyToOne()
    private Usuario autor;

    @ManyToOne()
    private Pergunta pergunta;

}
