package com.example.ap1.errorHandler;

public class Validation {

    private String field;

    public String message;

    public Validation(String field, String message) { // construtor para mostrar:
        this.field = field; // o campo que deu erro
        this.message = message; // e a msg desse campo
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }

}

