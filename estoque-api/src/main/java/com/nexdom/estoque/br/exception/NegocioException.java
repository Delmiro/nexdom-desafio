package com.nexdom.estoque.br.exception;

public class NegocioException extends RuntimeException {

    public NegocioException() {
        super();
    }

    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegocioException(Throwable cause) {
        super(cause);
    }

}
