package br.com.cwi.crescer.melevaai.representation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CadastraMotoristaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @Email(message = "O e-mail deve estar em um formato válido")
    private String email;


    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Pattern(regexp = "(^\\d{3}\\.\\d{3}\\.\\d{3}\\x2D\\d{2}$)")
    private String cpf;

    @Valid
    private CNHCompletaRequestResponse cnh;
}
