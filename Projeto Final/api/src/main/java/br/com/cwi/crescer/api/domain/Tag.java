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
@Table(name = "tag")
@SequenceGenerator(name = "seq_tag", sequenceName = "seq_tag", allocationSize = 1)
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tag")
    private Long id;

    @Column(name = "nome")
    private String nome;

}
