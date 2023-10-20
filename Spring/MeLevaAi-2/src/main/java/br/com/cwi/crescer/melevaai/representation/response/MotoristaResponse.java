package br.com.cwi.crescer.melevaai.representation.response;

import br.com.cwi.crescer.melevaai.domain.CPF;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
public class MotoristaResponse {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Pattern(regexp = "(^\\d{3}\\.\\d{3}\\.\\d{3}\\x2D\\d{2}$)")
    private String cpf;



}
