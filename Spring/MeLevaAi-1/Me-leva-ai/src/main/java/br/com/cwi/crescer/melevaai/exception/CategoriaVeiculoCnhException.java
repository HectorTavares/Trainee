package br.com.cwi.crescer.melevaai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class CategoriaVeiculoCnhException extends Exception{
    public CategoriaVeiculoCnhException(String message) {
        super(message);
    }
}
