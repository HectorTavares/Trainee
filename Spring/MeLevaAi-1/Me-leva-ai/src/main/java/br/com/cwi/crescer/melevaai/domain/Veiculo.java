package br.com.cwi.crescer.melevaai.domain;

import br.com.cwi.crescer.melevaai.exception.CategoriaVeiculoCnhException;
import br.com.cwi.crescer.melevaai.exception.ValidacaoNegocioException;
import lombok.*;

import javax.validation.Valid;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Veiculo {

    private String placa;
    private Marca marca;
    private String modelo;
    private Cor cor;
    private int ano;
    private String foto;
    private Categoria categoria;
    private int quantidadeLugares;
    @Valid
    private Motorista proprietario;
    private Integer id;

    public Veiculo(String placa, Marca marca, String modelo, int ano, Cor cor, String foto,
                   Categoria categoria, int quantidadeLugares, Motorista proprietario) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.foto = foto;
        this.categoria = categoria;
        this.quantidadeLugares = quantidadeLugares;
        this.proprietario = proprietario;
    }

    public Veiculo(String placa, Marca marca, String modelo, Cor cor, int ano, String foto, Categoria categoria, int quantidadeLugares) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.foto = foto;
        this.categoria = categoria;
        this.quantidadeLugares = quantidadeLugares;
    }

    public void validaCategoriaVeiculoCnh() throws CategoriaVeiculoCnhException {
        if (this.getCategoria() != this.getProprietario().getCnh().getCategoria()) {
            throw new CategoriaVeiculoCnhException("CNH Incompativel");
        }
    }
}