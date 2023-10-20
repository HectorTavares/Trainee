package br.com.cwi.crescer.melevaai.representation;

import br.com.cwi.crescer.melevaai.domain.Categoria;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class CNHCompletaRequestResponse {
    @NotBlank
    private String numero;

    @NotNull
    private Categoria categoria;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;
}
