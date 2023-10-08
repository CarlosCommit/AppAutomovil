package ar.com.automar.catalogo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalHandlerExceptions extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, Object> response = new HashMap<String, Object>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> response.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ResourceExist.class)
    public ResponseEntity<Object> handleResourceExist(ResourceExist ex) {

        ResponseErrorException response = ResponseErrorException.builder().mensaje(ex.getMensaje()).codigo("400")
                .build();
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }
     @ExceptionHandler(value = ResourceNotExist.class)
    public ResponseEntity<Object> handleResourceNotExist(ResourceNotExist ex) {

        ResponseErrorException response = ResponseErrorException.builder().mensaje(ex.getMensaje()).codigo("404")
                .build();
        return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex)
    {
        ResponseErrorException response = ResponseErrorException.builder().mensaje( ex.getMessage()).codigo("1001")
                .build();
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }

}
