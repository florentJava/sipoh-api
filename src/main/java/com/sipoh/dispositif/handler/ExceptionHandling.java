package com.sipoh.dispositif.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sipoh.dispositif.exception.GeneralException;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class ExceptionHandling {


    /*
     * Validation des dtos entrants
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    /*
     * Erreur lors de la Deserialisation en dtos
     * des JSON entrants
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Map<String, String> handleInvalidTypeException(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Invalid data type provided");
        errors.put("message", ex.getMessage());
        return errors;
    }


    /*
     * Erreur lors de la recherche d'une entite dans la BD
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public Map<String, String> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Entity not found");
        errorResponse.put("message", ex.getMessage());
        return errorResponse;
    }


    /*
     * Erreur generale a gerer
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(GeneralException.class)
    @ResponseBody
    public Map<String, String> handleGeneralException(GeneralException ex){

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return errorResponse;

    }



}
