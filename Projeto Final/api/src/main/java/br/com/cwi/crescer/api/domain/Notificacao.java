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
@Table(name = "notificacao")
@SequenceGenerator(name = "seq_notificacao", sequenceName = "seq_notificacao", allocationSize = 1)
public class Notificacao {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_notificacao")
    Long id;

    @ManyToOne
    Usuario destinatario;

    @Column(name = "mensagem")
    String mensagem;

    @ManyToOne
    Pergunta pergunta;

    @ManyToOne
    Resposta resposta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoNotificacao")
    TipoNotificacao tipoNotificao;

}
