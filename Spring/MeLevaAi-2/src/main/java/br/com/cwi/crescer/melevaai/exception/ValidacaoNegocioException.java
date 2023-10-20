package br.com.cwi.crescer.melevaai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidacaoNegocioException extends RuntimeException {

    public ValidacaoNegocioException(final String message) {
        super(message);
    }
}
