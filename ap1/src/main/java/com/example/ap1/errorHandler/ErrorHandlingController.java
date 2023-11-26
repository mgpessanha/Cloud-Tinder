package com.example.ap1.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.ap1.exception.BusinessException;

@ControllerAdvice // indica que é uma classe de erro
public class ErrorHandlingController { // classe que captura e trata os erros, mandando uma msg tratada para o usuário, ou seja, uma msg mais organizada

    @ExceptionHandler(MethodArgumentNotValidException.class) // método que aparece para o usuário, mostrando a msg de erro
    @ResponseStatus(HttpStatus.BAD_REQUEST) 
    @ResponseBody
    public ValidationErrorResponse validationHandler(MethodArgumentNotValidException e) {
        ValidationErrorResponse errors = new ValidationErrorResponse();
        for (FieldError item : e.getBindingResult().getFieldErrors()) {
            errors.getErrors().add(new Validation(item.getField(), item.getDefaultMessage())); // para cada erro, pega a msg e o campo dele
        }

        return errors;
    }

    @ExceptionHandler(BusinessException.class) 
    @ResponseStatus(HttpStatus.BAD_REQUEST) 
    @ResponseBody
    public ValidationErrorResponse businessExceptionHandler(BusinessException e) {
        ValidationErrorResponse errors = new ValidationErrorResponse();
        errors.getBusinessErrors().add(new BusinessError(e.getClass().getSimpleName(), e.getMessage())); // pega a classe, seu nome e a mensagem de erro que ela gerou
        
        return errors;
    }
}