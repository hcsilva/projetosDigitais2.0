package br.com.cardapioDigital.exceptions;

import br.com.cardapioDigital.utils.MessageUtils;

public class DataIntegrityViolationException extends RuntimeException {
    public DataIntegrityViolationException(String message) {
        super(MessageUtils.getMessage(message));
    }
}
