package ar.com.automar.catalogo.controller;

import ar.com.automar.catalogo.dtos.UserDTO;
import ar.com.automar.catalogo.model.Usuario;
import ar.com.automar.catalogo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    UserService userService;

    @DeleteMapping(path = "/delete/{usuarioId}")
    public ResponseEntity<Map<String,Object>> deleteUser(@PathVariable(name = "usuarioId") Long id)
    {
        Map<String,Object> response = new HashMap<>();
        UserDTO usuarioEliminado =  userService.deleteUser(id);
        response.put("Mensaje:", "Usuario Eliminado Correctamente");
        response.put("Usuario:",usuarioEliminado );
        return new ResponseEntity<Map<String,Object>>( response, HttpStatus.OK);
    }

    @PutMapping(path="/update/{usuarioId}")
    public ResponseEntity<Map<String,Object>> updateUsuario(@PathVariable(name="usuarioId") Long id,@RequestBody UserDTO usuario)
    {
        Map<String,Object> response = new HashMap<String,Object>();
        UserDTO usuarioEditado = userService.updateUser(usuario,id);
        response.put("Mensaje:","Usuario Editado Correctamente");
        response.put("Objeto:", usuarioEditado);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

}
