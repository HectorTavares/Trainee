package br.com.cwi.crescer.api.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    Long id;

    @Column(name = "username")
    String username;

    @Column(name = "email")
    String email;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "reputacao")
    Double reputacao;

    @Column(name = "isMonitor")
    Boolean isMonitor;

    @OneToMany(mappedBy = "autor")
    List<Pergunta> perguntas;

    @OneToMany(mappedBy = "autor")
    List<Resposta> respostas;

    @OneToMany(mappedBy = "autor")
    List<InteracaoPergunta> interacoesPerguntas;

    @OneToMany(mappedBy = "autor")
    List<InteracaoResposta> interacoesRespostas;


    public Usuario() {
        this.reputacao = 0.00;
        this.isMonitor = false;
        this.perguntas = new ArrayList<>();
        this.respostas = new ArrayList<>();
        this.interacoesPerguntas = new ArrayList<>();
        this.interacoesRespostas = new ArrayList<>();
    }

}




