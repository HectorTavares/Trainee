package br.com.cwi.crescer.api.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pergunta")
@SequenceGenerator(name = "seq_pergunta", sequenceName = "seq_pergunta", allocationSize = 1)
public class Pergunta {

    public Pergunta(){
        this.dataHora = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pergunta")
    private Long id;

    @ManyToOne
    private Usuario autor;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "foto")
    private String foto;

    @ManyToMany()
    @JoinColumn
    private List<Tag> tags;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.PERSIST)
    private List<Resposta> respostas;

    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.PERSIST)
    private List<InteracaoPergunta> interacaoPerguntas;

}