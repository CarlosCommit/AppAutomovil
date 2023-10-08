package ar.com.automar.catalogo.service.impl;

import ar.com.automar.catalogo.dtos.UserDTO;
import ar.com.automar.catalogo.exceptions.ResourceNotExist;
import ar.com.automar.catalogo.model.Usuario;
import ar.com.automar.catalogo.repository.UsuarioDAO;
import ar.com.automar.catalogo.service.UserService;
import ar.com.automar.catalogo.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsuarioDAO userRepository;
    @Autowired
    Mapper mapper;
    @Override
    public UserDTO deleteUser(Long id) {
        Usuario usuarioBuscado = userRepository.findById(id).orElseThrow( () -> new ResourceNotExist("El usuario que intentas eliminar no existe"));
        userRepository.delete(usuarioBuscado);
        return mapper.convertUserToDto(usuarioBuscado);
    }

    @Override
    public UserDTO updateUser(UserDTO update, Long id) {
         //TO DO verificar la ocupacion del nombre de usuario si es que se cambia
        Usuario usuario = userRepository.findById(id).orElseThrow( () -> new ResourceNotExist("El usuario que intentas actualizar no existe"));
        usuario = mapper.updateUsuario(usuario, update);
        userRepository.save(usuario);
     return update;
    }
}
