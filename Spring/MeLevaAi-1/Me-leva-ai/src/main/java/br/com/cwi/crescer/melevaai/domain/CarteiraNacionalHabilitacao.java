package br.com.cwi.crescer.melevaai.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarteiraNacionalHabilitacao {

    private String numero;
    private Categoria categoria;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;

    @JsonIgnore
    public boolean isVencida()  {
        return dataVencimento.isBefore(LocalDate.now());
    }
}
