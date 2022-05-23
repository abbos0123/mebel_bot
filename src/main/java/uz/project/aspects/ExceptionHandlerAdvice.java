package uz.project.aspects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uz.project.models.CustomResponse;


import javax.ws.rs.NotFoundException;
import java.util.InputMismatchException;

@ControllerAdvice
@RestController
public class ExceptionHandlerAdvice {

    @ExceptionHandler(InputMismatchException.class)
    @ResponseBody
    public ResponseEntity<CustomResponse> handleInputMismatchException(InputMismatchException inException){
        var customResponse = new CustomResponse(HttpStatus.NOT_ACCEPTABLE.value(), inException.getMessage());
        return new ResponseEntity<>(customResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<CustomResponse> handleNotFoundException(NotFoundException inException){
        var customResponse = new CustomResponse(HttpStatus.NOT_FOUND.value(),  inException.getMessage());
        return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<CustomResponse> handleException(Exception inException){
        var customResponse = new CustomResponse(HttpStatus.BAD_REQUEST.value(), inException.getMessage());
        return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
    }
}
