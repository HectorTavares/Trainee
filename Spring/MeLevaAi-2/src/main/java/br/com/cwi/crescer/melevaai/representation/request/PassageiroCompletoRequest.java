package br.com.cwi.crescer.melevaai.representation.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class PassageiroCompletoRequest {
    @NotBlank
    @NotEmpty
    private String nome;
    @NotBlank
    @NotEmpty
    @Email(message = "O e-mail deve estar em um formato válido")
    private String email;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @Pattern(regexp = "(^\\d{3}\\.\\d{3}\\.\\d{3}\\x2D\\d{2}$)")
    private String cpf;
}
