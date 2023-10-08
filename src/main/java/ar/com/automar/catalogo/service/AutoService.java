package ar.com.automar.catalogo.service;

import java.util.List;

import ar.com.automar.catalogo.model.Auto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import ar.com.automar.catalogo.dtos.AutoDTO;

public interface AutoService {

	public AutoDTO guardarAuto(AutoDTO auto,List<MultipartFile> imagenes);
	public Page<AutoDTO> getAutoPages(Pageable pageable);
}
