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
@Table(name = "interacao_resposta")
@SequenceGenerator(name = "seq_interacao_resposta", sequenceName = "seq_interacao_resposta", allocationSize = 1)
public class InteracaoResposta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_interacao_resposta")
    private Long id;

    @Column(name = "tipoInteracao")
    private TipoInteracao tipoInteracao;

    @ManyToOne()
    private Usuario autor;

    @ManyToOne()
    private Resposta resposta;
}