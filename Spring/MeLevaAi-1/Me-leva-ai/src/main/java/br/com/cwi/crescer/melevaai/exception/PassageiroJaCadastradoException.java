package br.com.cwi.crescer.melevaai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PassageiroJaCadastradoException extends Exception {

    public PassageiroJaCadastradoException(final String message) {
        super(message);
    }
}
