package br.com.cwi.crescer.melevaai.domain;

import lombok.*;

import javax.persistence.Embeddable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class Coordenadas {

    private double x;
    private double y;
}
