package ar.com.automar.catalogo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ar.com.automar.catalogo.exceptions.ResourceExist;
import ar.com.automar.catalogo.exceptions.ResourceNotExist;
import ar.com.automar.catalogo.model.Rol;
import ar.com.automar.catalogo.repository.RolDAO;
import ar.com.automar.catalogo.repository.UsuarioDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import ar.com.automar.catalogo.dtos.AutoDTO;
import ar.com.automar.catalogo.dtos.UserDTO;
import ar.com.automar.catalogo.model.Auto;
import ar.com.automar.catalogo.model.Imagen;
import ar.com.automar.catalogo.model.Usuario;

import javax.swing.text.html.parser.Entity;

@Component
public class Mapper {
	@Autowired
	RolDAO rolRepository;
	@Autowired
	UsuarioDAO userRepository;
	@Value("${endpoint.images}")
	private String endpointImages;
	private ModelMapper mapper = new ModelMapper();

	public Usuario convertUserToEntity(UserDTO usuario) {
		Usuario usuarioEntity = new Usuario();
		mapper.map(usuario, usuarioEntity);
		return usuarioEntity;
	}

	public UserDTO convertUserToDto(Usuario usuario)
	{
     UserDTO usuarioDto = new UserDTO();
	 mapper.map(usuario,usuarioDto);
	 usuarioDto.setRol(usuario.getRol().getNombre());
	 return  usuarioDto;
	}
	public Auto convertAutoToEntity(AutoDTO auto, List<MultipartFile> imagenes) {
		List<Imagen> imagenesEntity = new ArrayList<Imagen>();
		Auto autoEntity = new Auto();
		mapper.map(auto, autoEntity);
		imagenes.forEach(imagen -> {
			Imagen img = new Imagen();
			img.setNombre(imagen.getOriginalFilename());
			img.setAuto(autoEntity);
			imagenesEntity.add(img);
			
		});
		autoEntity.setImagenes(imagenesEntity);
		return autoEntity;
	}

	public AutoDTO convertAutoToDTO(Auto auto) {
		List<String> imagenesString = new ArrayList<String>();
		AutoDTO autoDTO = new AutoDTO();
		mapper.map(auto, autoDTO);
		auto.getImagenes().forEach(imagen -> {
			
				imagenesString.add(endpointImages+ reemplazarEspaciosPorPorcentaje(imagen.getNombre()));
			
		});
		autoDTO.setImagenesURI(imagenesString);
		return autoDTO;
	}

	public Usuario updateUsuario(Usuario userEntity, UserDTO userUpdated){

		userEntity.setApellido(userUpdated.getApellido());
		userEntity.setNombre(userUpdated.getNombre());

		if(!userEntity.getUsername().equals(userUpdated.getUsername()))
			userRepository.findByUsername(userUpdated.getUsername())
					.ifPresentOrElse(
							(user) -> { throw new ResourceExist("Username que intentas actualizar ya existe");} ,
							() -> { userEntity.setUsername(userUpdated.getUsername());});

		userEntity.setPassword(userUpdated.getPassword());

		if(!userEntity.getRol().getNombre().equals(userUpdated.getRol())) {
			Rol role = rolRepository.findByNombre(userUpdated.getRol()).orElseThrow(() -> new ResourceNotExist("El rol que intentas asignar en la actualizacion no existe"));
			userEntity.setRol(role);
		}

		return userEntity;

}

    private static String reemplazarEspaciosPorPorcentaje(String texto) {
        return texto.replaceAll(" ", "%20");
    }

}
