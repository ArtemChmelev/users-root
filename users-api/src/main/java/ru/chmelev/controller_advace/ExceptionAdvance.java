package ru.chmelev.controller_advace;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.chmelev.dto.errors.ErrorDto;
import ru.chmelev.exception.NoFoundException;

import java.util.List;

//Перехватчик ошибок - перехватывает определенные исключения, и обрабатывает их так, как это необходимо по логике
//Моя реализация возвращает ErrorDto со статусом.
@RestControllerAdvice
public class ExceptionAdvance {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleDataUnprocessableEntityException(MethodArgumentNotValidException ex) {
        List<ErrorDto> errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> new ErrorDto(error.getDefaultMessage()))
                .toList();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoFoundException.class)
    public ResponseEntity<?> handleDataNotFoundException(NoFoundException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> defaultHandleException(RuntimeException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}