package com.example.ap1.errorHandler;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse { // agrupador das mensagens de erro
    public String message = "Existem erros na sua requisição";
    
    public List<Validation> validationErrors = new ArrayList<Validation>(); // lista de erros <validation>
    
    public List<BusinessError> businessErrors = new ArrayList<BusinessError>();

    public List<Validation> getErrors() {
        return validationErrors;
    }

    public void setErrors(List<Validation> errors) {
        this.validationErrors = errors;
    }

    public String getMessage() {
        return message;
    }

    public List<BusinessError> getBusinessErrors() {
        return businessErrors;
    }

    public void setBusinessErrors(List<BusinessError> businessErrors) {
        this.businessErrors = businessErrors;
    }

}
