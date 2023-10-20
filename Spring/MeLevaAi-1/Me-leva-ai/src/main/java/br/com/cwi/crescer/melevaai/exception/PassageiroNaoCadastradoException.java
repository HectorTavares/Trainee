package br.com.cwi.crescer.melevaai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PassageiroNaoCadastradoException extends Exception {

    public PassageiroNaoCadastradoException(final String message) {
        super(message);
    }
}
