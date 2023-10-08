package ar.com.automar.catalogo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.automar.catalogo.dtos.UserDTO;
import ar.com.automar.catalogo.service.SignUpService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/api/v1")
public class SignUpController {
    
    @Autowired
    SignUpService signUpService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserDTO usuario)
    {
        Map<String,Object> response = new HashMap<String, Object>(); 
        usuario =  signUpService.signUp(usuario); 
        response.put("mensaje", "guardado Correctamente"); 
        response.put("Object", usuario);
       return new ResponseEntity<Object>(response, HttpStatus.CREATED); 
    }

}
