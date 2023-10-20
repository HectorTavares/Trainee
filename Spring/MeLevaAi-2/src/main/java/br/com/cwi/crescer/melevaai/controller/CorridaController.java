package br.com.cwi.crescer.melevaai.controller;

import br.com.cwi.crescer.melevaai.exception.CorridaInexistenteException;
import br.com.cwi.crescer.melevaai.exception.PassageiroNaoCadastradoException;
import br.com.cwi.crescer.melevaai.representation.request.AvaliacaoNotaRequest;
import br.com.cwi.crescer.melevaai.representation.request.ChamarCorridaRequest;
import br.com.cwi.crescer.melevaai.representation.response.ChamarCorridaResponse;
import br.com.cwi.crescer.melevaai.representation.response.CorridaResponse;
import br.com.cwi.crescer.melevaai.representation.response.IniciarCorridaResponse;
import br.com.cwi.crescer.melevaai.service.CorridaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/privado/corridas")
public class CorridaController {

    @Autowired
    CorridaService corridaService;

    @PostMapping("/passageiros/{cpfPassageiro}")
    @ResponseStatus(HttpStatus.OK)
    public ChamarCorridaResponse chamarCorrida(@RequestBody ChamarCorridaRequest request, @PathVariable String cpfPassageiro) throws PassageiroNaoCadastradoException {

        return corridaService.chamarCorrida(request,cpfPassageiro);
    }

    @PostMapping("/{idCorrida}")
    @ResponseStatus(HttpStatus.OK)
    public IniciarCorridaResponse iniciarCorrida(@PathVariable int idCorrida) throws CorridaInexistenteException {
     return corridaService.iniciarCorrida(idCorrida);
    }

    @GetMapping("/passageiros/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public List<CorridaResponse> ListarCorridasPassageiro(@PathVariable String cpf) throws PassageiroNaoCadastradoException {



        return corridaService.listarCorridasDoPassageiro(cpf);
    }

    @PutMapping("/corridas/{idCorrida}")
    @ResponseStatus(HttpStatus.OK)
    public void terminarCorrida(@PathVariable int idCorrida) throws PassageiroNaoCadastradoException, CorridaInexistenteException {
       corridaService.terminarCorrida(idCorrida);
    }

    @PostMapping("/{idCorrida}/motoristas/avaliacao")
    @ResponseStatus(HttpStatus.OK)
    public void avaliarMotorista(@RequestBody AvaliacaoNotaRequest avaliacao, @PathVariable Integer idCorrida) throws CorridaInexistenteException {

        corridaService.avaliarMotorista(avaliacao,idCorrida);
    }

    @PostMapping("/{idCorrida}/passageiros/avaliacao")
    @ResponseStatus(HttpStatus.OK)
    public void avaliarPassageiro(@RequestBody AvaliacaoNotaRequest avaliacao, @PathVariable Integer idCorrida) throws CorridaInexistenteException {

      corridaService.avaliarPassageiro(avaliacao,idCorrida);

    }


}
