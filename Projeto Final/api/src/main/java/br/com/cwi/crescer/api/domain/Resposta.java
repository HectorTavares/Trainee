package br.com.cwi.crescer.api.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "resposta")
@SequenceGenerator(name = "seq_resposta", sequenceName = "seq_resposta", allocationSize = 1)
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_resposta")
    private Long id;

    @ManyToOne()
    private Usuario autor;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "foto")
    private String foto;

    @ManyToOne()
    private Pergunta pergunta;

    @Column(name = "isAprovado")
    private Boolean isAprovado;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @OneToMany(mappedBy = "resposta", cascade = CascadeType.PERSIST)
    private List<InteracaoResposta> interacaos;

    public Resposta() {
        this.isAprovado = false;
        this.dataHora = LocalDateTime.now();
    }

}
