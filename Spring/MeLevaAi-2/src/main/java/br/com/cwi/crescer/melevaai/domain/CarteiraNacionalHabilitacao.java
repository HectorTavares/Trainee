package br.com.cwi.crescer.melevaai.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "cnh")
@SequenceGenerator(name = "seq_cnh", sequenceName = "seq_cnh", allocationSize = 1)
public class CarteiraNacionalHabilitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cnh")
    private Integer id;

    @Column(name = "numero")
    private String numero;
    @Column(name = "categoria")
    private Categoria categoria;
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @JsonIgnore
    public boolean isVencida()  {
        return dataVencimento.isBefore(LocalDate.now());
    }
}
