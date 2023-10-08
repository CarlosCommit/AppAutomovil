package ar.com.automar.catalogo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.automar.catalogo.dtos.UserDTO;
import ar.com.automar.catalogo.exceptions.ResourceExist;
import ar.com.automar.catalogo.exceptions.ResourceNotExist;
import ar.com.automar.catalogo.model.Rol;
import ar.com.automar.catalogo.model.Usuario;
import ar.com.automar.catalogo.repository.RolDAO;
import ar.com.automar.catalogo.repository.UsuarioDAO;
import ar.com.automar.catalogo.service.SignUpService;
import ar.com.automar.catalogo.utils.Mapper;
@Service
public class SingUpServiceImpl implements SignUpService{

    @Autowired
    UsuarioDAO usuarioRepository; 
    @Autowired
    RolDAO rolRepository; 
    @Autowired
    Mapper mapper; 


    @Override
    public UserDTO signUp(UserDTO usuario) {
        
        //username ocupado DONE 
       Optional<Usuario> usuarioBd = usuarioRepository.findByUsername(usuario.getUsername()); 
       if(usuarioBd.isPresent())
       throw new ResourceExist("El usuario que intentas Registrar ya Existe"); 
        //rol inexistente 
           Rol rol = rolRepository.findByNombre(usuario.getRol()).orElseThrow( () ->  new ResourceNotExist("El rol que solicitas no existe") );
           Usuario user = mapper.convertUserToEntity(usuario); 
           user.setRol(rol);
           usuarioRepository.save(user); 
           return usuario; 
    }
    
}
