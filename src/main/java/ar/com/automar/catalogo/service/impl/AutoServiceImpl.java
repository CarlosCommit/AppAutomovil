package ar.com.automar.catalogo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ar.com.automar.catalogo.dtos.AutoDTO;
import ar.com.automar.catalogo.model.Auto;
import ar.com.automar.catalogo.repository.AutoDAO;
import ar.com.automar.catalogo.service.AutoService;
import ar.com.automar.catalogo.service.MultimediaService;
import ar.com.automar.catalogo.utils.Mapper;

@Service
public class AutoServiceImpl implements AutoService{

	@Autowired
	AutoDAO autoRepository; 
	@Autowired
	Mapper mapper; 
	@Autowired
	MultimediaService multimediaService; 
	
	@Override
	@Transactional
	public AutoDTO guardarAuto(AutoDTO auto, List<MultipartFile> imagenes) {
	
		Auto autoEntity = mapper.convertAutoToEntity(auto, imagenes);
		imagenes.forEach(imagen->{
			multimediaService.guardarEnDirectorio(imagen);
		});
		autoRepository.save(autoEntity);
	   return mapper.convertAutoToDTO(autoEntity);
	//return null; 
	}

	@Override
	public Page<AutoDTO> getAutoPages(Pageable pageable) {
		//Obtenemos un page de autoEntidad
	    Page<Auto> autos = autoRepository.findAll(pageable);
		//Obtenemos un page solo con contenido (perdemos informacion extra)
		return autos.map( auto -> {
			return mapper.convertAutoToDTO(auto);
		} );

	}

}
