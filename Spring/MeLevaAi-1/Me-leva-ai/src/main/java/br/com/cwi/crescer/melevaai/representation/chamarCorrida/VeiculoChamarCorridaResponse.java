package br.com.cwi.crescer.melevaai.representation.chamarCorrida;

import br.com.cwi.crescer.melevaai.domain.Cor;
import br.com.cwi.crescer.melevaai.domain.Marca;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class VeiculoChamarCorridaResponse {

    public VeiculoChamarCorridaResponse(String placa, Marca marca, String modelo, Cor cor, String foto, String nomeMotorista) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.foto = foto;
        this.nomeMotorista = nomeMotorista;
    }

    @NotBlank
    @NotEmpty
    private String placa;
    @NotBlank
    @NotEmpty
    private Marca marca;
    @NotBlank
    @NotEmpty
    private String modelo;
    @NotBlank
    @NotEmpty
    private Cor cor;
    @NotBlank
    @NotEmpty
    private String foto;
    @NotBlank
    @NotEmpty
    private String nomeMotorista;
}
