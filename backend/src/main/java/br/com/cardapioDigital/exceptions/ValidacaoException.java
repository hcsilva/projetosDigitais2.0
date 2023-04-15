package br.com.cardapioDigital.exceptions;

import br.com.cardapioDigital.utils.MessageUtils;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String message) {
        super(MessageUtils.getMessage(message));
    }
}