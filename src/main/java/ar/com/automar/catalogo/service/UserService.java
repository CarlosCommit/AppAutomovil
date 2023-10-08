package ar.com.automar.catalogo.service;

import ar.com.automar.catalogo.dtos.UserDTO;

public interface UserService {

 public UserDTO deleteUser(Long id);
 public UserDTO updateUser(UserDTO update, Long id);
}
