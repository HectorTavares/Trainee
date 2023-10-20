package br.com.cwi.crescer.melevaai.domain;

import br.com.cwi.crescer.melevaai.exception.CategoriaVeiculoCnhException;
import br.com.cwi.crescer.melevaai.exception.ValidacaoNegocioException;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "veiculo")
@SequenceGenerator(name = "seq_veiculo", sequenceName = "seq_veiculo", allocationSize = 1)
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_veiculo")
    private Integer id;

    @Column(name = "placa")
    private String placa;
    @Column(name = "marca")
    private Marca marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "cor")
    private Cor cor;
    @Column(name = "ano")
    private int ano;
    @Column(name = "foto")
    private String foto;
    @Column(name = "categoria")
    private Categoria categoria;
    @Column(name = "quantidadeLugares")
    private int quantidadeLugares;
    @ManyToOne()
    private Motorista proprietario;


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