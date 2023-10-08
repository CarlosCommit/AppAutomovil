package ar.com.automar.catalogo.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseErrorException{
    
    private String mensaje; 
    private String codigo; 
}
