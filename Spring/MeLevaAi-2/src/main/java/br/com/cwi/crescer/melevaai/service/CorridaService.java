package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.domain.Corrida;
import br.com.cwi.crescer.melevaai.domain.Motorista;
import br.com.cwi.crescer.melevaai.domain.Passageiro;
import br.com.cwi.crescer.melevaai.domain.Veiculo;
import br.com.cwi.crescer.melevaai.exception.CorridaInexistenteException;
import br.com.cwi.crescer.melevaai.exception.PassageiroNaoCadastradoException;
import br.com.cwi.crescer.melevaai.exception.PassageiroSemSaldoSuficienteException;
import br.com.cwi.crescer.melevaai.exception.ValidacaoNegocioException;
import br.com.cwi.crescer.melevaai.mapper.AvaliacaoMapper;
import br.com.cwi.crescer.melevaai.mapper.CorridaMapper;
import br.com.cwi.crescer.melevaai.repository.CorridaRepository;
import br.com.cwi.crescer.melevaai.representation.request.AvaliacaoNotaRequest;
import br.com.cwi.crescer.melevaai.representation.request.ChamarCorridaRequest;
import br.com.cwi.crescer.melevaai.representation.response.ChamarCorridaResponse;
import br.com.cwi.crescer.melevaai.representation.response.CorridaResponse;
import br.com.cwi.crescer.melevaai.representation.response.IniciarCorridaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CorridaService {

    @Autowired
    private CorridaRepository repository;

    @Autowired
    private PassageiroService passageiroService;

    @Autowired
    private VeiculoService veiculoService;

    private final CorridaMapper corridaMapper = new CorridaMapper();

    private final AvaliacaoMapper avaliacaoMapper = new AvaliacaoMapper();

    public static final int TRINTA_KM_POR_HORA = 30;
    public static final int HORA_EM_SEGUNDOS = 3600;
    public static final double VALOR_CORRIDA = 0.20;

    public ChamarCorridaResponse chamarCorrida(ChamarCorridaRequest request, String cpfPassageiro) throws PassageiroNaoCadastradoException {

        Corrida corrida = CorridaMapper.toDomain(request);



        if (passageiroService.consultarPassageiroPorCpf(cpfPassageiro) == null) {
            throw new PassageiroNaoCadastradoException("Passageiro não cadastrado");
        }

        Veiculo veiculo = veiculoService.pegarVeiculoDisponivel();
        corrida.setVeiculo(veiculo);

        corrida.setPassageiro(passageiroService.consultarPassageiroPorCpf(cpfPassageiro));

        int tempoEmMilisegundos = (int) Math.round((Math.random() * 300000) + 300000);

        corrida.setId(repository.findAll().size()+1);

        repository.save(corrida);

        return CorridaMapper.corridaToChamarCorridaResponse(corrida,tempoEmMilisegundos);

    }

    public IniciarCorridaResponse iniciarCorrida(int idCorrida) throws CorridaInexistenteException {
        Corrida corrida = repository.findCorridaById(idCorrida);

        validarCorrida(idCorrida);

        LocalDateTime horaInicioCorrida = LocalDateTime.now();
        corrida.setTempoInicioCorrida(horaInicioCorrida);

        double distanciaTotalEmKM = Math.sqrt(Math.pow((corrida.getFinais().getX() - corrida.getIniciais().getX()), 2) +
                Math.pow((corrida.getFinais().getY() - corrida.getIniciais().getY()), 2));

        double tempoEstimadoDoPercursoEmSegundos = Math.round((distanciaTotalEmKM / TRINTA_KM_POR_HORA) * HORA_EM_SEGUNDOS);
        double valorEstimadoCorrida = Math.round(tempoEstimadoDoPercursoEmSegundos * VALOR_CORRIDA);


        corrida.getVeiculo().getProprietario().setEmCorrida(true);

        repository.save(corrida);

        return new IniciarCorridaResponse(tempoEstimadoDoPercursoEmSegundos, valorEstimadoCorrida);

    }

    public void terminarCorrida(int idCorrida) throws PassageiroNaoCadastradoException, CorridaInexistenteException {

        Corrida corrida = repository.findCorridaById(idCorrida);
        Passageiro passageiro = corrida.getPassageiro();
        Motorista motorista = corrida.getVeiculo().getProprietario();

        validarCorrida(idCorrida);

        LocalDateTime horaFimCorrida = LocalDateTime.now();
        double duracaoCorrida = ChronoUnit.SECONDS.between(corrida.getTempoInicioCorrida(), horaFimCorrida);

        double valorCorrida = duracaoCorrida * VALOR_CORRIDA;

        pagarCorrida(passageiro,motorista,valorCorrida);

        corrida.getVeiculo().getProprietario().setEmCorrida(false);

        repository.save(corrida);
    }

    public void pagarCorrida(Passageiro passageiro, Motorista motorista, double valorCorrida) {

        if (passageiro.getConta() >= valorCorrida) {
            passageiro.setConta(passageiro.getConta()-valorCorrida);
            motorista.setConta(motorista.getConta() + valorCorrida);
        }else{
            throw new PassageiroSemSaldoSuficienteException("Passageiro Sem saldo Suficiente");
        }

    }

    public void avaliarMotorista(AvaliacaoNotaRequest avaliacao, Integer idCorrida) throws CorridaInexistenteException {
        Integer nota = AvaliacaoMapper.toDomain(avaliacao);

        validarAvalicao(idCorrida,nota);

        Corrida corrida = repository.findCorridaById(idCorrida);
        corrida.setNotaMotorista(nota);

        repository.save(corrida);
    }

    public void avaliarPassageiro(AvaliacaoNotaRequest avaliacao, Integer idCorrida) throws CorridaInexistenteException {
        Integer nota = AvaliacaoMapper.toDomain(avaliacao);

        validarAvalicao(idCorrida,nota);

        Corrida corrida = repository.findCorridaById(idCorrida);
        corrida.setNotaPassageiro(nota);

        repository.save(corrida);
    }

    public void validarCorrida(Integer idCorrida) throws CorridaInexistenteException {
        if (!repository.existsById(idCorrida)) {
            throw new CorridaInexistenteException("corrida inexistente");
        }
    }

    public void validarAvalicao(Integer idCorrida, Integer nota) throws CorridaInexistenteException {

        validarCorrida(idCorrida);

        if (nota < 1 || nota > 5) {
            throw new ValidacaoNegocioException("Nota invalida");
        }


    }


    public List<CorridaResponse> listarCorridasDoPassageiro(String cpf) throws PassageiroNaoCadastradoException {

        Passageiro passageiro = passageiroService.consultarPassageiroPorCpf(cpf);

        return repository
                .findAll()
                .stream()
                .filter(corrida -> corrida.getPassageiro().equals(passageiro))
                .map(CorridaMapper::toCorridaResponse)
                .collect(Collectors.toList());

    }
}
