package br.com.cwi.crescer.melevaai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class IdadeMinimaException extends Exception{
    public IdadeMinimaException(final String message) {
        super(message);
    }
}
