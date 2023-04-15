package br.com.cardapioDigital.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ApiErrors {

    private List<String> errors;

    public ApiErrors(String message) {
        this.errors = Arrays.asList(message);
    }
}
