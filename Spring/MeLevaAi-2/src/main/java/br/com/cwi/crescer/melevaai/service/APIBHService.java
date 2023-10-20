package br.com.cwi.crescer.melevaai.service;


import br.com.cwi.crescer.melevaai.repository.ExemploAcessoApiBHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APIBHService {

    @Autowired
    ExemploAcessoApiBHRepository repository;

    public String cadastrarPassageiro(){



        return "retorno";
    }



}
