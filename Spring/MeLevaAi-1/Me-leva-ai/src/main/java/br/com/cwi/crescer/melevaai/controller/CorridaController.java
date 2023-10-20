package br.com.cwi.crescer.melevaai.controller;

import br.com.cwi.crescer.melevaai.domain.*;
import br.com.cwi.crescer.melevaai.exception.CorridaInexistenteException;
import br.com.cwi.crescer.melevaai.exception.PassageiroNaoCadastradoException;
import br.com.cwi.crescer.melevaai.exception.ValidacaoNegocioException;
import br.com.cwi.crescer.melevaai.mapper.AvaliacaoMapper;
import br.com.cwi.crescer.melevaai.mapper.CorridaMapper;
import br.com.cwi.crescer.melevaai.representation.avaliacao.AvaliacaoNotaRequest;
import br.com.cwi.crescer.melevaai.representation.chamarCorrida.ChamarCorridaRequest;
import br.com.cwi.crescer.melevaai.representation.chamarCorrida.ChamarCorridaResponse;
import br.com.cwi.crescer.melevaai.representation.chamarCorrida.VeiculoChamarCorridaResponse;
import br.com.cwi.crescer.melevaai.representation.iniciarCorrida.IniciarCorridaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static br.com.cwi.crescer.melevaai.controller.PassageiroController.buscarPassageiroPorCpf;

@RestController
@RequestMapping("/corridas")
public class CorridaController {

    public static final int TRINTA_KM_POR_HORA = 30;
    public static final int HORA_EM_SEGUNDOS = 3600;
    public static final double VALOR_CORRIDA = 0.20;

    public List<Corrida> corridas = new ArrayList<>();
    CorridaMapper corridaMapper = new CorridaMapper();
    private static final List<Veiculo> veiculos = VeiculoController.veiculos;
    AvaliacaoMapper avaliacaoMapper = new AvaliacaoMapper();

    @PostMapping("/passageiros/{cpfPassageiro}")
    @ResponseStatus(HttpStatus.OK)
    public ChamarCorridaResponse chamarCorrida(@RequestBody ChamarCorridaRequest request, @PathVariable String cpfPassageiro) throws PassageiroNaoCadastradoException {
        Corrida corrida = corridaMapper.toDomain(request);

        if (buscarPassageiroPorCpf(cpfPassageiro) == null) {
            throw new PassageiroNaoCadastradoException("Passageiro não cadastrado");
        }

        Veiculo veiculo = veiculos.get((int) (Math.random() * veiculos.size() - 1));
        corrida.setVeiculo(veiculo);

        corrida.setIdCorrida(corridas.size() + 1);
        corrida.setCpfPassageiro(cpfPassageiro);

        int tempoEmMilisegundos = (int)Math.round((Math.random() * 300000) + 300000);
        corridas.add(corrida);

        return new ChamarCorridaResponse(
                        corrida.getIdCorrida(),
                        new VeiculoChamarCorridaResponse(
                                veiculo.getPlaca(),
                                veiculo.getMarca(),
                                veiculo.getModelo(),
                                veiculo.getCor(),
                                veiculo.getFoto(),
                                veiculo.getProprietario().getNome()),
                        tempoEmMilisegundos);
    }

    @PostMapping("/{idCorrida}")
    @ResponseStatus(HttpStatus.OK)
    public IniciarCorridaResponse iniciarCorrida(@PathVariable int idCorrida) {
        Corrida corrida = buscarCorridaPeloId(idCorrida);

        if (corrida == null) {
            throw new ValidacaoNegocioException("Corrida Inválida!");
        }

        LocalDateTime horaInicioCorrida = LocalDateTime.now();
        corrida.setTempoInicioCorrida(horaInicioCorrida);

        double distanciaTotalEmKM = Math.sqrt(Math.pow((corrida.getFinais()[0] - corrida.getIniciais()[0]), 2)+Math.pow((corrida.getFinais()[1] - corrida.getIniciais()[1]), 2));

        double tempoEstimadoDoPercursoEmSegundos = Math.round((distanciaTotalEmKM/TRINTA_KM_POR_HORA) * HORA_EM_SEGUNDOS);
        double valorEstimadoCorrida = Math.round(tempoEstimadoDoPercursoEmSegundos * VALOR_CORRIDA);

        return new IniciarCorridaResponse(tempoEstimadoDoPercursoEmSegundos, valorEstimadoCorrida);
    }

    @PutMapping("/corridas/{idCorrida}")
    @ResponseStatus(HttpStatus.OK)
    public void terminarCorrida(@PathVariable int idCorrida) {
        Corrida corrida = buscarCorridaPeloId(idCorrida);
        Passageiro passageiro = buscarPassageiroPorCpf(corrida.getCpfPassageiro());
        Motorista motorista = corrida.getVeiculo().getProprietario();

        if (corrida == null) {
            throw new ValidacaoNegocioException("Corrida Inválida!");
        }

        LocalDateTime horaFimCorrida = LocalDateTime.now();
        double duracaoCorrida = ChronoUnit.SECONDS.between(corrida.getTempoInicioCorrida(), horaFimCorrida);

        double valorCorrida = duracaoCorrida * VALOR_CORRIDA;

        if(passageiro.getConta() >= valorCorrida) {
            passageiro.pagarCorrida(motorista, valorCorrida);
        }
    }

    @PostMapping("/{idCorrida}/motoristas/avaliacao")
    @ResponseStatus(HttpStatus.OK)
    public void avaliarMotorista(@RequestBody AvaliacaoNotaRequest avaliacao, @PathVariable Integer idCorrida) throws CorridaInexistenteException {

        Integer nota = avaliacaoMapper.toDomain(avaliacao);
        Corrida corrida = buscarCorridaPeloId(idCorrida);

        if (corrida == null) {
            throw new CorridaInexistenteException("Corrida inexistente");
        }

        if (nota < 1 || nota > 5) {
            throw new ValidacaoNegocioException("Nota invalida");
        }

        corrida.setNotaMotorista(nota);
    }

    @PostMapping("/{idCorrida}/passageiros/avaliacao")
    @ResponseStatus(HttpStatus.OK)
    public void avaliarPassageiro(@RequestBody AvaliacaoNotaRequest avaliacao, @PathVariable Integer idCorrida) throws CorridaInexistenteException {

        Integer nota = avaliacaoMapper.toDomain(avaliacao);
        Corrida corrida = buscarCorridaPeloId(idCorrida);

        if (corrida == null) {
            throw new CorridaInexistenteException("corrida inexistente");
        }

        if (nota < 1 || nota > 5) {
            throw new ValidacaoNegocioException("Nota invalida");
        }

        corrida.setNotaPassageiro(nota);

    }

    public Corrida buscarCorridaPeloId(Integer id) {
        for (Corrida c : corridas) {
            if (c.getIdCorrida() == id) {
                return c;
            }
        }
        return null;
    }

}
